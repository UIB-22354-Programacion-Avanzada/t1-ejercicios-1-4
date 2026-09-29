// DetectorDeCaidas.java
package es.uib.prgava.tema1.ejercicios;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import es.uib.prgava.tema1.monitor.Resultado;

public class DetectorDeCaidas {

    private final Duration ventana;

    public DetectorDeCaidas(Duration ventana) {
        this.ventana = ventana;
    }

    /** Fallos ocurridos dentro de la ventana que termina ahora mismo. */
    public int fallosRecientes(List<Resultado> historial) {
        var desde = Instant.now().minus(ventana);
        int fallos = 0;
        for (var resultado : historial) {
            if (resultado.esFallo() && resultado.instante().isAfter(desde)) {
                fallos++;
            }
        }
        return fallos;
    }

    /** ¿Ha pasado ya la ventana desde el último aviso? */
    public boolean tocaAvisar(Instant ultimoAviso) {
        return Duration.between(ultimoAviso, Instant.now()).compareTo(ventana) >= 0;
    }
}
