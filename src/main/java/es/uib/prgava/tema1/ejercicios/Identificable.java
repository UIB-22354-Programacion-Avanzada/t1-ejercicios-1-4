package es.uib.prgava.tema1.ejercicios;

/**
 * Ejercicio 1.1.7. Una interfaz no exige parentesco.
 *
 * <p>La herencia solo sirve dentro de una familia: {@code PuntoAcceso} puede extender
 * {@code DispositivoRed} porque es uno. Pero {@link Servidor} y {@link DireccionIpv4} no tienen
 * nada en común —una es una clase con estado, el otro un {@code record} inmutable, y un
 * {@code record} no puede heredar de nada— y aun así las dos pueden comprometerse a saber decir
 * cómo se llaman. Eso es lo que declara una interfaz: un compromiso, no un origen.
 */
public interface Identificable {

    /** Cómo se identifica este objeto. Cada clase decide qué devuelve. */
    String identificador();

    /**
     * Método {@code default}: comportamiento <strong>derivado</strong> del método abstracto.
     *
     * <p>Quien implemente la interfaz lo recibe sin escribirlo, y eso es lo que comprueba la
     * prueba {@code laEtiquetaFuncionaEnLasDosSinQueNingunaLaEscriba}. Aquí está la utilidad real
     * de los métodos {@code default}: añadir a una interfaz todo lo que se pueda expresar en
     * función de sus métodos abstractos, sin obligar a nadie a repetirlo. Y sin romper a las clases
     * que ya la implementaban, que es la razón por la que se añadieron al lenguaje.
     */
    default String etiqueta() {
        return "[" + identificador() + "]";
    }
}
