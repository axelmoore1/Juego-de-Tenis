package test.java;

import main.java.DefaultJugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
    @Test
    public void testJugador(){
        DefaultJugador jugador= new DefaultJugador("Axel","Moore");

        assertEquals("A. Moore",jugador.toString());
    }


}
