package es.uib.prgava.tema1.monitor;

import java.util.List;

/**
 * Ejercicio 1.2.2. Una política de alerta nueva, y ni una línea
 * de {@code Monitor} ni de {@code PoliticaDeAlerta} ha cambiado.
 *
 * <p>Eso es el principio abierto/cerrado, y no es una casualidad: es la consecuencia de que
 * {@code Monitor} programe contra la interfaz. El monitor queda <strong>abierto</strong> a políticas
 * nuevas —se añaden desde fuera— y <strong>cerrado</strong> a modificaciones —no hay que tocarlo para
 * añadirlas—. El patrón que lo permite es Strategy, y aquí se ve que un patrón no es una estructura
 * que se monta: es lo que queda cuando se saca a una interfaz la parte que varía.
 *
 * <p>La diferencia con {@link FallosConsecutivos} es que aquí los fallos no tienen que ser seguidos:
 * cuenta cuántos hay en las últimas {@code tamanoVentana} comprobaciones, sean consecutivos o no. Es
 * la política que responde al «no quiero un aviso por cada microcorte, quiero saber cuándo falla de
 * verdad» del encargo inicial.
 */
public final class FallosEnVentana implements PoliticaDeAlerta {

    private final int minimoFallos;
    private final int tamanoVentana;

    /**
     * Las dos condiciones se comprueban juntas porque están relacionadas: pedir tres fallos en una
     * ventana de dos es una política que nunca puede alertar, y eso no es una configuración
     * improbable, es un error de quien la escribió. Mejor que no llegue a existir.
     *
     * @throws IllegalArgumentException si no se cumple 1 &le; minimoFallos &le; tamanoVentana
     */
    public FallosEnVentana(int minimoFallos, int tamanoVentana) {
        if (minimoFallos < 1 || minimoFallos > tamanoVentana) {
            throw new IllegalArgumentException(
                    "Se esperaba 1 <= minimoFallos <= tamanoVentana, y llegó "
                            + minimoFallos + " y " + tamanoVentana);
        }
        this.minimoFallos = minimoFallos;
        this.tamanoVentana = tamanoVentana;
    }

    @Override
    public boolean debeAlertar(List<Resultado> historial) {
        // Math.max(0, ...) resuelve de una vez los dos casos raros: el historial vacío y el más
        // corto que la ventana. Sin él harían falta dos if, y uno de los dos se olvida siempre.
        int desde = Math.max(0, historial.size() - tamanoVentana);
        int fallos = 0;
        for (int i = desde; i < historial.size(); i++) {
            if (historial.get(i).esFallo()) {
                fallos++;
            }
        }
        // >= y no ==: con más fallos de los necesarios también hay que alertar. Cambiar esto por ==
        // produce una política que deja de avisar cuando la cosa empeora, que es el peor momento.
        return fallos >= minimoFallos;
    }

    /** Lo usa {@code Monitor} al componer el mensaje, así que tiene que leerse como una frase. */
    @Override
    public String toString() {
        return minimoFallos + " fallos en las últimas " + tamanoVentana + " comprobaciones";
    }

    // ----------------------------------------------------------------------------------
    // RESPUESTA al «Para pensar»: ¿hay un historial que distinga FallosConsecutivos(2) de
    // FallosEnVentana(2, 2)?
    //
    // No con dos comprobaciones, y sí con tres. Sobre [FALLO, OK, FALLO] las dos dicen que no:
    // FallosConsecutivos porque el OK reinicia la cuenta, FallosEnVentana(2,2) porque en la ventana
    // de las dos últimas solo hay un fallo. Pero sobre [FALLO, FALLO, OK] FallosConsecutivos dice
    // que no —la racha se rompió— y FallosEnVentana(2,2) también, porque mira [FALLO, OK].
    //
    // La distinción aparece al ampliar la ventana: FallosEnVentana(2, 3) sí alerta con
    // [FALLO, OK, FALLO], y FallosConsecutivos(2) no. Con la ventana igual al mínimo, las dos
    // políticas coinciden siempre; la ventana solo añade algo cuando es más ancha que el mínimo. Esa
    // es la respuesta interesante, y es la clase de cosa que se descubre escribiendo las pruebas de
    // 1.4.1 y no leyendo el código.
    // ----------------------------------------------------------------------------------
}
