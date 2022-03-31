CREATE DATABASE JavaSql
USE JavaSql
GO 

CREATE TABLE [DBO].[RUBRO](
	[id_rubro] [BIGINT] IDENTITY (1,1) NOT NULL, 
	[Rubro] [VARCHAR] (200)
CONSTRAINT [pk_id_rubro] PRIMARY KEY CLUSTERED ([id_rubro] ASC))
GO 

CREATE TABLE [DBO].[PRODUCTO](
	[codigo] [VARCHAR] (250) NOT NULL,
	[nombre] [VARCHAR](25) NULL,
	[fecha_creacion] [DATE] NULL, 
	[id_rubro] [BIGINT]
CONSTRAINT [pk_codigo] PRIMARY KEY CLUSTERED ([codigo] ASC),
CONSTRAINT [fK_id_rubro] FOREIGN KEY (id_rubro) REFERENCES [DBO].[RUBRO] (id_rubro))
GO

 CREATE TABLE [DBO].[CLIENTE](
	[id_cliente] [BIGINT] IDENTITY (1,1) NOT NULL, 
	[razon_social] [VARCHAR] (200) NOT NULL, 
	[cuit] [VARCHAR] (11) NOT NULL
CONSTRAINT [pk_id_cliente] PRIMARY KEY CLUSTERED ([id_cliente] ASC))
GO 

CREATE TABLE [DBO].[VENTA](
	[id_venta] [BIGINT] IDENTITY (1,1) NOT NULL,
	[codigo_producto] [VARCHAR] (250) NOT NULL ,
	[nombre] [VARCHAR] (250) NOT NULL,
	[fecha][DATE] NULL, 
	[cantidad][BIGINT] NULL, 
	[precio][NUMERIC] NULL, 
	[id_cliente][BIGINT] NOT NULL
CONSTRAINT [pk_id_venta] PRIMARY KEY CLUSTERED ([id_venta] ASC),
CONSTRAINT [fk_id_cliente] FOREIGN KEY (id_cliente) REFERENCES [DBO].[CLIENTE] (id_cliente),
CONSTRAINT [fk_codigo_producto] FOREIGN KEY (codigo_producto) REFERENCES [DBO].[PRODUCTO] (codigo))
GO

INSERT INTO [dbo].[VENTA] ([codigo_producto],[nombre],[fecha],[cantidad],[precio],[id_cliente])  VALUES ('1', 'Antonio','31-03-2022',4,5000,1)
INSERT INTO [dbo].[VENTA] ([codigo_producto],[nombre],[fecha],[cantidad],[precio],[id_cliente])  VALUES ('4', 'Roberto','31-03-2022',3,8000,1)
INSERT INTO [dbo].[VENTA] ([codigo_producto],[nombre],[fecha],[cantidad],[precio],[id_cliente])  VALUES ('2', 'Roberto','31-03-2022',3,8000,2)
INSERT INTO [dbo].[VENTA] ([codigo_producto],[nombre],[fecha],[cantidad],[precio],[id_cliente])  VALUES ('7', 'Marcelo','31-03-2022',3,5000,2)
INSERT INTO [dbo].[VENTA] ([codigo_producto],[nombre],[fecha],[cantidad],[precio],[id_cliente])  VALUES ('6', 'Marcela','31-03-2022',7,3000,3)
GO

INSERT INTO [dbo].[RUBRO] ([Rubro])  VALUES ('Libreria')
INSERT INTO [dbo].[RUBRO] ([Rubro]) VALUES ('Bazar')
INSERT INTO [dbo].[RUBRO] ([Rubro]) VALUES ('Perfumeria')
GO

INSERT INTO [dbo].[CLIENTE] ([razon_social],[cuit])  VALUES ('Axel', '20444444445')
INSERT INTO [dbo].[CLIENTE] ([razon_social],[cuit])  VALUES ('Juancito', '20444444425')
INSERT INTO [dbo].[CLIENTE] ([razon_social],[cuit])  VALUES ('Pepito', '20444444435')
GO

INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro])  VALUES ('1','Cartuchera','2022-03-30', 1)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('2','Lapicera','2022-03-30', 1)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('3','Plato','2022-01-10',2)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('4','Cuchara','2022-01-11',2)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro])  VALUES ('5','Goma','2022-03-30', 1)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('6','Lapiz','2022-03-30', 1)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('7','Lapicera','2022-03-30', 3)
INSERT INTO [dbo].[PRODUCTO] ([codigo], [nombre], [fecha_creacion], [id_rubro]) VALUES ('8','Lapicera','2022-01-10', 3)
GO


--QUERYS-- producto creados hoy
SELECT nombre, fecha_creacion,id_rubro FROM PRODUCTO
WHERE DAY(fecha_creacion) = 30 and MONTH(fecha_creacion)=MONTH(GETDATE()) and id_rubro = 1

--2 Monto total vendido por cliente (mostrar nombre del cliente y monto).

SELECT C.razon_social as 'Cliente', SUM(V.precio * V.cantidad) AS Total
FROM CLIENTE C JOIN VENTA V ON C.id_cliente = V.id_cliente
GROUP BY razon_social

--3. Cantidad de ventas por producto.
SELECT nombre, COUNT(cantidad) AS Cantidad FROM VENTA 
GROUP BY nombre

-- 4 .Cantidad de productos comprados por cliente en el mes actual.

SELECT C.razon_social AS Cliente, P.nombre AS Producto ,COUNT(V.cantidad) AS Cantidad, V.fecha as Fecha
FROM CLIENTE C JOIN VENTA V ON C.id_cliente = V.id_cliente JOIN PRODUCTO P ON V.codigo_producto = P.codigo
WHERE MONTH(V.fecha) = MONTH(GETDATE())
GROUP BY C.razon_social, P.nombre, V.fecha

--5. Ventas que tienen al menos un producto del rubro "bazar".
SELECT c.razon_social AS 'Cliente' ,v.codigo_producto AS 'Codigo',p.nombre AS 'Producto',r.Rubro AS Rubro, v.cantidad as 'Cantidad'
FROM RUBRO r JOIN PRODUCTO p ON r.id_rubro = p.id_rubro JOIN VENTA v ON v.codigo_producto = p.codigo JOIN CLIENTE c ON c.id_cliente = v.id_cliente
WHERE r.id_rubro = 2

--6. Rubros que no tienen ventas en los ultimos 2 meses.
SELECT r.Rubro AS Rubro, p.nombre AS Nombre, p.fecha_creacion AS Fecha
FROM RUBRO r JOIN PRODUCTO p ON r.id_rubro = p.id_rubro JOIN VENTA v ON p.codigo = v.codigo_producto
WHERE DATEDIFF(MONTH,p.fecha_creacion,GETDATE())=2 

select * from VENTA
select * from rubro
select * from PRODUCTO