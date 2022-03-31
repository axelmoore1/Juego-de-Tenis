package main.java.interfaces;

import java.util.List;

public interface Partido {
    Jugador obtenerJugador(int jugador);

    int obtenerProbabilidad(int jugador);

    int obtenerPuntosJugador(int jugador);

    void marcarTanto(int jugador);

    void juegoGanado(int jugador);

    int obtenerJuegosGanadosJugador(int jugador);

    void setGanado(int jugador, int juegosJugadorUno, int juegosJugadorDos);

    void setearPunto(int jugador, int puntos);

    void setearJuego(int jugador, int juegos);

    int obtenerSetGanados(int jugador);

    int obtenerCantidadSet();

    int obtenerGanador();

    void establecerGanador(int jugador);

    void setearSet(int jugador, List<Integer> sets, int ganados);

    String obtenerNombreTorneo();

    List<Integer> obtenerSets(int jugador);

    boolean obtenerEstadoPartido();

    void establecerEstadoPartido(boolean estado);

}
