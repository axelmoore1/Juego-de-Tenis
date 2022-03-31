package test.java;
import main.java.DefaultJugador;
import main.java.DefaultPartido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PartidoTest {
    private DefaultJugador jugadorA;
    private DefaultJugador jugadorB;
    private int probabilidadA;
    private int probabilidadB;
    private int cantidadSet;
    private String torneo;
    @BeforeEach
    public void setUp(){
        jugadorA = new DefaultJugador("Axel","Moore");
        jugadorB = new DefaultJugador("Camila ♥", "Ramallo");
        probabilidadA = 40;
        probabilidadB = 60;
        cantidadSet = 3;
        torneo = "Champions";
    }

    @Test
    public void creacionPartidoFallaConCantidadSetErrronea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DefaultPartido(jugadorA, jugadorB, probabilidadA, probabilidadB, cantidadSet,torneo);
        });
    }

    @Test
    public void creacionPartidoFallaConProbabilidadErronea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DefaultPartido(jugadorA, jugadorB, probabilidadA, probabilidadB, cantidadSet,torneo);
        });
    }

    @Test
    public void creacionPartidoFallaConSumaDeProbabilidadesNoComplementarias() {
        probabilidadA = 50;
        probabilidadB = 30;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DefaultPartido(jugadorA, jugadorB, probabilidadA, probabilidadB, cantidadSet,torneo);
        });
        String expectedMessage = "La suma de ambas probabilidades debe dar 100%";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
