package test.java;

import main.java.DefaultEjecucionPartido;
import main.java.DefaultJugador;
import main.java.DefaultPartido;
import main.java.interfaces.EjecucionPartido;
import main.java.interfaces.Partido;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EjecucionPartidoTest {
    private DefaultJugador jugador1;
    private DefaultJugador jugador2;
    private DefaultEjecucionPartido defaultEjecucionPartido;
    private DefaultEjecucionPartido defaultEjecucionPartido2;
    private DefaultPartido partido;
    private int probabilidadA;
    private int probabilidadB;
    private int cantidadSet;
    private String nombreTorneo;

    @BeforeEach
    public void setUp(){
        jugador1 = new DefaultJugador("Axel","Moore");
        jugador2 = new DefaultJugador("Camila ♥","Ramallo");
        defaultEjecucionPartido = new DefaultEjecucionPartido(new DeterministicRandom());
        defaultEjecucionPartido2 = new DefaultEjecucionPartido(new DeterministicRandom2());
        probabilidadA = 60;
        probabilidadB = 40;
        cantidadSet = 3;
        nombreTorneo = "";
        partido = new DefaultPartido(jugador1, jugador2,probabilidadA,probabilidadB, cantidadSet,nombreTorneo);
    }
    @Test
    public void seleccionJugadorUnoParaSaque(){
        assertEquals(1, defaultEjecucionPartido.jugadorSaque(partido));
    }

    @Test
    public void JugadorUnoMarcaUnTanto(){
        defaultEjecucionPartido.jugarUnTanto(partido);
        assertEquals(1, partido.obtenerPuntosJugador(1));
    }

    @Test
    public void JugadorDosMarcaUnTanto(){
        defaultEjecucionPartido2.jugarUnTanto(partido);
        assertEquals(1, partido.obtenerPuntosJugador(2));
    }

    @Test
    public void JugadorUnoGanaUnJuego(){
        partido.setearPunto(1,3);
        partido.setearPunto(2,1);
        defaultEjecucionPartido.jugarUnTanto(partido);


        assertEquals(1, partido.obtenerJuegosGanadosJugador(1));
        assertEquals(0, partido.obtenerJuegosGanadosJugador(2));
    }

    @Test
    public void JugadorUnoNoGanaPorDiferenciaDePuntos(){
        partido.setearPunto(1,3);
        partido.setearPunto(2,3);
        defaultEjecucionPartido.jugarUnTanto(partido);


        assertEquals(0, partido.obtenerJuegosGanadosJugador(1));
        assertEquals(0, partido.obtenerJuegosGanadosJugador(2));
    }

    @Test
    public void JugadorUnoGanaPorDiferenciaDePuntos(){
        partido.setearPunto(1,5);
        partido.setearPunto(2,4);
        defaultEjecucionPartido.jugarUnTanto(partido);


        assertEquals(1, partido.obtenerJuegosGanadosJugador(1));
        assertEquals(0, partido.obtenerJuegosGanadosJugador(2));
    }

    @Test
    public void JugadorUnoGanaSet(){
        partido.setearPunto(1,3);
        partido.setearPunto(2,1);
        partido.setearJuego(1,5);
        partido.setearJuego(2,3);
        defaultEjecucionPartido.jugarUnTanto(partido);


        assertEquals(1, partido.obtenerSetGanados(1));
        assertEquals(0, partido.obtenerSetGanados(2));
    }

    @Test
    public void JugadorUnoGanaPartido(){
        partido.setearPunto(1,3);
        partido.setearPunto(2,1);
        partido.setearJuego(1,5);
        partido.setearJuego(2,2);
        List<Integer> setJugador1 = new ArrayList<>();
        setJugador1.add(6);
        setJugador1.add(7);
        partido.setearSet(1,setJugador1,2);
        List<Integer> setJugador2 = new ArrayList<>();
        setJugador1.add(4);
        setJugador1.add(5);
        partido.setearSet(2,setJugador2,0);
        defaultEjecucionPartido.jugarUnTanto(partido);


        assertEquals(1, partido.obtenerGanador());

    }
}

class DeterministicRandom extends Random {

    public int nextInt(int value){
        if (value == 10){
            return 3;
        }
        if (value == 2){
            return 0;
        }
        else
            return value;
    }

}

class DeterministicRandom2 extends Random {

    public int nextInt(int value){
        if (value == 10){
            return 9;
        }
        if (value == 2){
            return 0;
        }
        else
            return value;
    }

}