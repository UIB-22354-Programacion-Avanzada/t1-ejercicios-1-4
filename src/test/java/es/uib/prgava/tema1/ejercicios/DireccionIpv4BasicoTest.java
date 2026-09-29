package es.uib.prgava.tema1.ejercicios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas mínimas del ejercicio 1.1.1, para que no te quedes sin comprobación hasta que
 * escribas las tuyas en 1.4.2. No repitas estos casos allí.
 */
class DireccionIpv4BasicoTest {

    @Test
    void losExtremosDelRangoSonValidos() {
        assertEquals(0, new DireccionIpv4(0, 0, 0, 0).primero());
        assertEquals(255, new DireccionIpv4(255, 255, 255, 255).cuarto());
    }

    @Test
    void unOctetoFueraDeRangoEsRechazado() {
        assertThrows(IllegalArgumentException.class, () -> new DireccionIpv4(192, 168, 1, 256));
        assertThrows(IllegalArgumentException.class, () -> new DireccionIpv4(-1, 0, 0, 0));
    }

    @Test
    void elToStringEsLaFormaConPuntos() {
        assertEquals("192.168.1.1", new DireccionIpv4(192, 168, 1, 1).toString());
    }

    @Test
    void dosObjetosDistintosConLosMismosOctetosSonIguales() {
        var una = new DireccionIpv4(10, 0, 0, 1);
        var otra = new DireccionIpv4(10, 0, 0, 1);
        assertNotSame(una, otra);
        assertEquals(una, otra);
        assertEquals(una.hashCode(), otra.hashCode());
    }
}
