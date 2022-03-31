package main.java;
import java.util.List;
import java.util.Random;

import main.java.interfaces.EjecucionPartido;
import main.java.interfaces.Jugador;
import main.java.interfaces.Partido;

public class DefaultEjecucionPartido implements EjecucionPartido {

    private Random random;

    public DefaultEjecucionPartido(Random random){
        this.random = random;
    }

    @Override
    public int jugadorSaque(Partido partido) {
        return random.nextInt(2) + 1;

    }

    @Override
    public void jugarUnTanto(Partido partido) {
        int jugador;
        int valor = random.nextInt(10)+1;
        int probJugadorUno = partido.obtenerProbabilidad(1) / 10;
        if(valor <= probJugadorUno){
            jugador = 1;
        }
        else {
            jugador = 2;
        }
        partido.marcarTanto(jugador);
        System.out.println("Tanto para jugador: " + jugador);
        validarJuego(partido);
    }

    @Override
    public void jugarPartido(Partido partido) {
        while (partido.obtenerGanador() == 0){
            jugarUnTanto(partido);
        }
    }

    private void validarJuego(Partido partido) {
        boolean ganadorJugador1 = false;
        boolean ganadorJugador2 = false;
        int jugadorGanador = 0;
        int puntosJugador1 = partido.obtenerPuntosJugador(1);
        int puntosJugador2 = partido.obtenerPuntosJugador(2);

        ganadorJugador1 = (puntosJugador1 >= 4 && puntosJugador1 - 1 > puntosJugador2);
        ganadorJugador2 = (puntosJugador2 >= 4 && puntosJugador2 - 1 > puntosJugador1);
        if(ganadorJugador1){
            jugadorGanador = 1;
        }
        else if (ganadorJugador2) {
            jugadorGanador = 2;
        }
        if (jugadorGanador != 0){
            partido.juegoGanado(jugadorGanador);
            partido.setearPunto(1,0);
            partido.setearPunto(2,0);
            System.out.println("---------------------------");
            System.out.println("Juego ganado por jugador: " + jugadorGanador);
            System.out.println("---------------------------");
        }

        validarSets(partido);
    }

    //TODO: REFACTORIZAR
    private void validarSets(Partido partido) {
        String jugadorGanadorSetNombre = "";
        int jugadorGanadorSet = 0;
        int juegosJugador1 = partido.obtenerJuegosGanadosJugador(1);
        int juegosJugador2 = partido.obtenerJuegosGanadosJugador(2);
        if(juegosJugador1 >=6 && juegosJugador1 -1 > juegosJugador2){
            jugadorGanadorSet = 1;
            jugadorGanadorSetNombre = partido.obtenerJugador(1).toString();
        }
        else if(juegosJugador2 >=6 && juegosJugador2 -1 > juegosJugador1){
            jugadorGanadorSet = 2;
            jugadorGanadorSetNombre = partido.obtenerJugador(2).toString();
        }

        if (jugadorGanadorSet != 0){
            partido.setGanado(jugadorGanadorSet,juegosJugador1,juegosJugador2);
            partido.setearJuego(1,0);
            partido.setearJuego(2,0);
            System.out.println("***************************");
            System.out.println("Ganador del set: " + jugadorGanadorSetNombre);
            System.out.println("***************************");
            mostrarTablero(partido);
            System.out.println();
        }

        validarPartido(partido);
    }

    private void validarPartido(Partido partido) {
        int jugadorGanadorPartido = 0;
        int cantidadSet = partido.obtenerCantidadSet();
        int setJugador1 = partido.obtenerSetGanados(1);
        int setJugador2 = partido.obtenerSetGanados(2);
        if(setJugador1 >= cantidadSet && setJugador1 -1 > setJugador2){
            jugadorGanadorPartido = 1;
        }
        else if (setJugador2 >= cantidadSet && setJugador2 -1 > setJugador1){
            jugadorGanadorPartido = 2;
        }

        if(jugadorGanadorPartido != 0){
            partido.establecerGanador(jugadorGanadorPartido);
            partido.establecerEstadoPartido(true);
            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("Ganador del partido: " + partido.obtenerJugador(jugadorGanadorPartido).toString());
            System.out.println("+++++++++++++++++++++++++++");
        }
    }

    public void mostrarTablero(Partido partido){
        String estadoPartido = partido.obtenerEstadoPartido() ? "Finalizado" : "Jugando";
        String torneoNombre = partido.obtenerNombreTorneo();
        List<Integer> resultadosJugador1 = partido.obtenerSets(1);
        List<Integer> resultadosJugador2 = partido.obtenerSets(2);
        System.out.println(torneoNombre + "\t " + estadoPartido);
        System.out.print(partido.obtenerJugador(1).toString());
        for(Integer elem : resultadosJugador1){
            System.out.print("\t\t\t" + elem);
        }

        System.out.print("\n" + partido.obtenerJugador(2).toString());
        for(Integer elem : resultadosJugador2){
            System.out.print("\t\t\t" + elem);
        }
        System.out.println();
    }

}
