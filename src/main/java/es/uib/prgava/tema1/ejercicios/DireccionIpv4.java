package es.uib.prgava.tema1.ejercicios;

/**
 * Ejercicios 1.1.1, 1.1.7, 1.1.8 y 1.2.7.
 *
 * <p>Por qué un {@code record} y no una clase: una dirección IP es un <em>valor</em>, no una
 * entidad. Dos direcciones con los mismos cuatro octetos son la misma dirección, no hay estado
 * que cambie con el tiempo y no tiene identidad propia. Ese es exactamente el caso de uso de
 * {@code record}: el compilador genera el constructor canónico, los accesores, {@code equals},
 * {@code hashCode} y {@code toString}.
 *
 * <p>Por eso el enunciado prohíbe escribir {@code equals} y {@code hashCode}: el generado ya
 * compara componente a componente, que es justo la igualdad que queremos, y su {@code hashCode}
 * es coherente con él por construcción. Escribirlos a mano sería repetir trabajo del compilador
 * e introducir la posibilidad de que se descoordinen, que es el defecto del ejercicio 1.1.3.
 *
 * <p>Un {@code record} no puede heredar de una clase, pero sí implementar tantas interfaces como
 * quiera: aquí, {@link Identificable} (1.1.7) y {@code Comparable} (1.1.8).
 */
public record DireccionIpv4(int primero, int segundo, int tercero, int cuarto)
        implements Identificable, Comparable<DireccionIpv4> {

    /**
     * Constructor compacto: se ejecuta antes de asignar los campos y es el lugar donde se
     * establece el <strong>invariante</strong> de la clase —«los cuatro octetos están entre 0 y
     * 255»—. Como los campos de un {@code record} son finales y esta es la única puerta de
     * entrada, es imposible que exista un objeto con un octeto inválido: no hay que volver a
     * comprobarlo en ningún otro sitio, ni en {@code desde}, ni en las clases que lo usen.
     */
    public DireccionIpv4 {
        validarOcteto(primero);
        validarOcteto(segundo);
        validarOcteto(tercero);
        validarOcteto(cuarto);
    }

    /**
     * Un único punto de validación, invocado cuatro veces. La alternativa —cuatro {@code if}
     * copiados— repite la condición y el mensaje, y cuatro copias de una regla son cuatro sitios
     * que alguien tiene que acordarse de cambiar a la vez.
     *
     * <p>Es un método de clase ({@code static}) porque no consulta ningún campo: opera solo sobre
     * su argumento. Y tiene que serlo, además, porque se llama desde el constructor compacto,
     * antes de que el objeto exista.
     */
    private static void validarOcteto(int valor) {
        if (valor < 0 || valor > 255) {
            throw new IllegalArgumentException("Octeto fuera de rango: " + valor);
        }
    }

    /**
     * Ejercicio 1.2.7. Fábrica estática: construye la dirección a partir de su forma textual.
     *
     * <p>Dos ventajas sobre un constructor que recibiera un {@code String}: el nombre dice qué
     * hace, y puede traducir y comprobar el formato antes de llegar al constructor canónico.
     *
     * <p>La decisión que pedía justificar el enunciado: <strong>los tres errores posibles se
     * traducen a {@code IllegalArgumentException}</strong>, porque desde fuera son el mismo
     * problema —«ese texto no es una dirección»— y quien llama no debería tener que capturar tres
     * excepciones distintas. El número de partes se comprueba aquí; que una parte no sea un
     * número lo detecta {@code Integer.parseInt}; y el rango no se comprueba dos veces, porque ya
     * lo hace el constructor canónico.
     *
     * @throws IllegalArgumentException si no hay cuatro partes, si alguna no es un entero o si
     *         alguna se sale del rango
     */
    public static DireccionIpv4 desde(String texto) {
        String[] partes = texto.strip().split("\\.");
        if (partes.length != 4) {
            throw new IllegalArgumentException(
                    "Se esperaban cuatro octetos separados por puntos, y llegó: " + texto);
        }
        try {
            return new DireccionIpv4(
                    Integer.parseInt(partes[0].strip()),
                    Integer.parseInt(partes[1].strip()),
                    Integer.parseInt(partes[2].strip()),
                    Integer.parseInt(partes[3].strip()));
        } catch (NumberFormatException e) {
            // Encadenamos la causa: quien depure verá también el NumberFormatException original.
            // (NumberFormatException ya es una IllegalArgumentException, así que sin este bloque
            // el método seguiría cumpliendo lo prometido; lo traducimos para dar un mensaje que
            // mencione el texto completo, no solo la parte que falló.)
            throw new IllegalArgumentException("Octeto no numérico en: " + texto, e);
        }
    }

    /** Devuelve la forma habitual, por ejemplo {@code 192.168.1.1}. */
    @Override
    public String toString() {
        // Redefinir toString en un record es legítimo: el generado sería
        // "DireccionIpv4[primero=192, ...]", útil para depurar pero no para mostrar.
        return primero + "." + segundo + "." + tercero + "." + cuarto;
    }

    /**
     * Ejercicio 1.1.7. Se apoya en {@code toString}, que ya produce la forma con puntos.
     *
     * <p>Que los dos métodos devuelvan lo mismo hoy no significa que sobre uno: dicen cosas
     * distintas. {@code toString} es «cómo se muestra esto»; {@code identificador} es «con qué
     * nombre aparece en un inventario». Si mañana el inventario quisiera el prefijo {@code ip:},
     * cambiaría uno y no el otro.
     */
    @Override
    public String identificador() {
        return toString();
    }

    /**
     * Ejercicio 1.1.8. Orden natural de una dirección: primero el primer octeto, y a igualdad el
     * siguiente. Es el orden lexicográfico sobre los cuatro números, que coincide con el orden
     * numérico de la dirección de 32 bits que representan.
     *
     * <p>Se usa {@code Integer.compare} y no la resta que parece equivalente: con octetos, de 0 a
     * 255, la resta nunca desborda y funcionaría, pero la costumbre de restar es la que produce el
     * error el día que los valores son enteros cualesquiera. Ver el Error Frecuente del tema.
     *
     * <p>El contrato de {@code Comparable} recomienda que el orden sea coherente con la igualdad,
     * y aquí lo es: {@code compareTo} devuelve cero exactamente cuando los cuatro octetos
     * coinciden, que es cuando {@code equals} —el generado por el {@code record}— dice que son
     * iguales. Esa coherencia es lo que permite meter direcciones en un {@code TreeSet} sin
     * sorpresas.
     */
    @Override
    public int compareTo(DireccionIpv4 otra) {
        int c = Integer.compare(primero, otra.primero());
        if (c != 0) {
            return c;
        }
        c = Integer.compare(segundo, otra.segundo());
        if (c != 0) {
            return c;
        }
        c = Integer.compare(tercero, otra.tercero());
        if (c != 0) {
            return c;
        }
        return Integer.compare(cuarto, otra.cuarto());
    }
}
