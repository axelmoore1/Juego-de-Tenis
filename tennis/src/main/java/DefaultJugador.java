package main.java;

import main.java.interfaces.Jugador;
import org.junit.platform.commons.util.ToStringBuilder;

public class DefaultJugador implements Jugador {

    private String name;
    private String lastName;


    public DefaultJugador(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return name.charAt(0)+ ". " + lastName;
    }

}
