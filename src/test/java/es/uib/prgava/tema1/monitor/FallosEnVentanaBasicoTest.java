package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas mínimas del ejercicio 1.2.2, para que no te quedes sin comprobación hasta que
 * escribas las tuyas en 1.4.1. No repitas estos casos allí.
 */
class FallosEnVentanaBasicoTest {

    private static final Servicio WEB = Servicio.desde("https://www.uib.es");
    private static final Resultado OK = Resultado.activo(WEB, Duration.ofMillis(100));
    private static final Resultado FALLO = Resultado.caido(WEB);

    @Test
    void elHistorialVacioNoAlerta() {
        assertFalse(new FallosEnVentana(2, 5).debeAlertar(List.of()));
    }

    @Test
    void dosFallosNoSeguidosDentroDeLaVentanaAlertan() {
        assertTrue(new FallosEnVentana(2, 5).debeAlertar(List.of(FALLO, OK, OK, OK, FALLO)));
    }

    @Test
    void unFalloQueSeSalioDeLaVentanaNoCuenta() {
        assertFalse(new FallosEnVentana(2, 5).debeAlertar(List.of(FALLO, OK, OK, OK, OK, OK)));
    }
}
