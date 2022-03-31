package main.java;

import main.java.interfaces.Jugador;
import main.java.interfaces.Partido;

import java.util.ArrayList;
import java.util.List;

public class DefaultPartido implements Partido {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final int probabilidad1;
    private final int probabilidad2;
    private final int cantidadSet;
    private int puntosJugador1 = 0;
    private int puntosJugador2 = 0;
    private int juegosJugador1 = 0;
    private int juegosJugador2 = 0;
    private int setsGanadosJugador1 = 0;
    private int setsGanadosJugador2 = 0;
    private int ganador = 0;
    private String nombreTorneo = "";
    private List<Integer> setsJugador1 = new ArrayList<>();
    private List<Integer> setsJugador2 = new ArrayList<>();
    private boolean finalizado = false;

    public DefaultPartido(Jugador jugador1, Jugador jugador2, int probabilidad1, int probabilidad2, int cantidadSet, String nombreTorneo){
        if (nombreTorneo == null){
            throw new IllegalArgumentException("Debe ingresar un nombre para el torneo");
        }
        if(cantidadSet != 3 && cantidadSet != 5){
            throw new IllegalArgumentException("La cantidad de sets tiene que ser 3 o 5");
        }

        if ((probabilidad1 <=1 || probabilidad1 >=100) || (probabilidad2 <=1 || probabilidad2 >=100)){
            throw new IllegalArgumentException("La probabilidades ingresadas no son correctas");
        }
        if (probabilidad1 + probabilidad2 != 100){
            throw  new IllegalArgumentException("La suma de ambas probabilidades debe dar 100%");
        }
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.probabilidad1 = probabilidad1;
        this.probabilidad2 = probabilidad2;
        this.cantidadSet = cantidadSet;
        this.nombreTorneo = nombreTorneo;
    }

    @Override
    public Jugador obtenerJugador(int jugador) {
        if (jugador == 1){
            return this.jugador1;
        }
        else{
            return this.jugador2;
        }
    }

    @Override
    public int obtenerProbabilidad(int probabilidad){
        if (probabilidad == 1){
            return this.probabilidad1;
        }
        else{
            return this.probabilidad2;
        }
    }

    @Override
    public int obtenerPuntosJugador(int jugador) {
        if (jugador == 1){
            return this.puntosJugador1;
        }
        else{
            return this.puntosJugador2;
        }
    }

    @Override
    public void marcarTanto(int jugador) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if(jugador == 1){
            puntosJugador1++;
        }
        else {
            puntosJugador2++;
        }
    }

    @Override
    public void juegoGanado(int jugador) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if(jugador == 1){
            juegosJugador1++;
        }
        else {
            juegosJugador2++;
        }
    }

    @Override
    public int obtenerJuegosGanadosJugador(int jugador) {
        if (jugador == 1){
            return this.juegosJugador1;
        }
        else{
            return this.juegosJugador2;
        }
    }

    @Override
    public void setGanado(int jugador,int juegosJugador1, int juegosJugador2) {
        if(jugador == 1){
            setsGanadosJugador1 ++;
        }
        else {
            setsGanadosJugador2++;
        }

        this.setsJugador1.add(juegosJugador1);
        this.setsJugador2.add(juegosJugador2);
    }

    @Override
    public void setearPunto(int jugador, int puntos) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if (puntos <0 || puntos >5){
            throw new IllegalArgumentException("Puntos fuera de rango");
        }
        if(jugador == 1){
            puntosJugador1 = puntos;
        }
        else {
            puntosJugador2 = puntos;
        }
    }

    @Override
    public void setearJuego(int jugador, int juegos) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if (juegos <0 || juegos >8){
            throw new IllegalArgumentException("Juegos fuera de rango");
        }
        if(jugador == 1){
            juegosJugador1 = juegos;
        }
        else {
            juegosJugador2 = juegos;
        }
    }

    @Override
    public int obtenerSetGanados(int jugador) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if(jugador == 1){
            return setsGanadosJugador1;
        }
        else {
            return setsGanadosJugador2;
        }
    }

    @Override
    public int obtenerCantidadSet() {
        return cantidadSet;
    }

    @Override
    public int obtenerGanador() {
        return this.ganador;
    }

    @Override
    public void establecerGanador(int jugador) {
        this.ganador = jugador;
    }

    @Override
    public void setearSet(int jugador, List<Integer> sets, int ganados) {
        if (jugador != 1 && jugador != 2){
            throw new IllegalArgumentException("No existe jugador");
        }
        if(jugador == 1){
            this.setsJugador1 = sets;
            this.setsGanadosJugador1 = ganados;
        }
        else {
            this.setsJugador2 = sets;
            this.setsGanadosJugador2 = ganados;
        }
    }

    @Override
    public String obtenerNombreTorneo() {
        return nombreTorneo;
    }

    @Override
    public List<Integer> obtenerSets(int jugador) {
        if(jugador == 1){
            return setsJugador1;
        }
        else{
            return setsJugador2;
        }
    }

    @Override
    public boolean obtenerEstadoPartido() {
        return finalizado;
    }

    @Override
    public void establecerEstadoPartido(boolean estado) {
        this.finalizado = estado;
    }

}
