package main.java;

import main.java.interfaces.EjecucionPartido;
import main.java.interfaces.Jugador;

import java.util.Random;
import java.util.Scanner;

public class Torneo{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre del torneo: ");
        String torneo = scanner.nextLine();

        System.out.println("Ingrese nombre jugador numero 1: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese apellido jugador numero 1: ");
        String apellido = scanner.nextLine();
        DefaultJugador jugador1 = new DefaultJugador(nombre,apellido);

        System.out.println("Ingrese probabilidad jugador numero 1: ");
        Integer probabilidad1 = Integer.parseInt(scanner.nextLine());


        System.out.println("Ingrese nombre jugador numero 2: ");
        nombre = scanner.nextLine();
        System.out.println("Ingrese apellido jugador numero 2: ");
        apellido = scanner.nextLine();
        DefaultJugador jugador2 = new DefaultJugador(nombre,apellido);

        System.out.println("Ingrese probabilidad jugador numero 2: ");
        Integer probabilidad2 = Integer.parseInt(scanner.nextLine());


        System.out.println("Ingrese cantidad de sets");
        Integer cantidadSet = Integer.valueOf(scanner.nextLine());

        boolean revancha;
        boolean revanchaJugada = false;
        do
        {
            revancha = false;
            DefaultPartido partido = new DefaultPartido(jugador1,jugador2,probabilidad1,probabilidad2,cantidadSet,torneo);
            DefaultEjecucionPartido ejecucionPartido = new DefaultEjecucionPartido(new Random());
            ejecucionPartido.jugarPartido(partido);
            ejecucionPartido.mostrarTablero(partido);
            if(!revanchaJugada){
                System.out.println("\nDesea la revancha: ? (0 - NO; 1 - SI)");
                int respuesta = scanner.nextInt();
                if (respuesta == 1){
                    revancha = true;
                    revanchaJugada = true;
                }
            }
        }while (revancha);

    }

}
//partido: nombre torneo, jugadores tienen nombre y apellido inical mas apellido