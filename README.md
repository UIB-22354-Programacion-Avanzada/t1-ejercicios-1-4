# t1-ejercicios-1-4 — Tema 1, bloque 1.4: Pruebas y diseño para la prueba

Esqueleto de los ejercicios **1.4.x** de *Programación Avanzada (22354)*, Grado en
Ingeniería Telemática, UIB-EPS. Los enunciados están en la
[página de ejercicios del tema](https://uib-22354-programacion-avanzada.github.io/website/es/ejercicios/tema1.html);
aquí tienes el código sobre el que trabajar.

Es un proyecto **Maven** para **Java 25** con pruebas en **JUnit 5**, preparado para
**GitHub Codespaces**. El esqueleto **compila desde el primer momento**, pero las pruebas
fallan: tu trabajo consiste en ponerlas en verde.

## Cómo empezar

Sea cual sea la vía, lo primero es lo mismo: pulsa **Use this template** →
**Create a new repository** y créalo en **tu cuenta personal**. A partir de ahí tienes dos
caminos, y **los dos valen igual**: es un proyecto Maven corriente.

**En el navegador, con Codespaces.** No hay nada que instalar. En tu copia, **Code** →
**Codespaces** → **Create codespace on main**. Se abre VS Code con el JDK 25 y Maven ya
puestos. En el terminal:

```bash
java -version    # debe empezar por: openjdk version "25
mvn test         # verás fallos: es lo esperado al empezar
```

> **Recuerda detener el Codespace** cuando termines (**Code → Codespaces → ⋯ → Stop
> codespace**): mientras está encendido consume tu cuota mensual gratuita.

**En tu ordenador, con IntelliJ IDEA o con VS Code.** Clona tu copia y abre el proyecto por
su `pom.xml`: en IntelliJ, **File → Open** y selecciona el `pom.xml` (elige *Open as
Project*); en VS Code, abre la carpeta y deja que la extensión de Java la importe. Necesitas
un **JDK 25**; IntelliJ puede descargarlo él mismo desde *File → Project Structure → SDK*.
Maven viene incluido en IntelliJ, así que no hace falta instalarlo aparte.

## Un ejercicio, una clase de prueba

Cada ejercicio se comprueba con una clase de prueba, y solo con esa. Para trabajar en uno,
abre su clase de prueba y pulsa el botón de ejecutar que aparece junto al nombre de la
clase: el ▶ en VS Code, la flecha verde del margen izquierdo en IntelliJ. Se ejecutan solo
sus pruebas, y al lado de cada método aparece si pasó o falló. Para verlas todas en árbol,
el icono de matraz (*Testing*) de la barra lateral en VS Code, o la ventana *Run* en
IntelliJ.

| Ejercicio | Clase de prueba |
|---|---|
| 1.4.1 | `FallosEnVentanaBasicoTest` |
| 1.4.2 | `DireccionIpv4BasicoTest` |

**Al clonar, casi todas estas pruebas fallan. Es lo normal**: son la lista de tareas, no una
avería. Van pasando a verde según resuelves los ejercicios.

## Cómo está organizado

```text
t1-ejercicios-1-4/
├── pom.xml                       ← proyecto Maven (Java 25, JUnit 5)
└── src/
    ├── main/java/es/uib/prgava/tema1/
    │   ├── ejercicios/   ← tus clases
    │   ├── monitor/      ← código del tema, ya escrito
    │   ├── poo/          ← código del tema, ya escrito
    └── test/java/es/uib/prgava/tema1/   ← las pruebas
```

Hay **0 clases** con ejercicios y **23** ya escritas, de las que
dependen. El código del tema es el mismo que hay en
[`t1-ejemplos`](https://github.com/UIB-22354-Programacion-Avanzada/t1-ejemplos): puedes
leerlo, ejecutarlo y, cuando el enunciado lo pida, modificarlo.

## Cómo leer el esqueleto

Cada clase que tienes que escribir trae ya las **declaraciones** que necesita el ejercicio
en el que aparece: las firmas de sus métodos, la documentación y las cláusulas `extends` e
`implements` correspondientes, para que el proyecto compile y las pruebas se puedan ejecutar
desde el primer momento. Lo que falta son
los **cuerpos**, marcados con `// TODO 1.4.k` y un
`throw new UnsupportedOperationException(...)`. **Borra ese `throw`** al implementar el
método; mientras esté, la prueba correspondiente falla con ese mensaje, que además te dice a
qué ejercicio pertenece.

Algunas clases **ya están escritas y son parte del enunciado**: son las que tienes que
diagnosticar, refactorizar o ampliar. Su código aparece también en el enunciado, para que
puedas leerlo sin salir de la página. No las borres.

## Órdenes útiles

```bash
mvn test                              # todas las pruebas
mvn -Dtest=DireccionIpv4BasicoTest test     # solo una clase
mvn -q compile                        # solo compilar
```

## Integridad académica

El uso de asistentes de IA en estos ejercicios se rige por las **condiciones de uso de la IA**
de la [guía docente](https://uib-22354-programacion-avanzada.github.io/website/es/informaciones/guia-docente.html).
En resumen: puedes pedir explicaciones y revisión, pero entregar código que no sabes
explicar, justificar ni modificar se considera uso indebido. Estos ejercicios son la base de
los Talleres y del Examen Parcial, donde no hay asistente que valga.

## Licencia

El material de partida se publica bajo licencia [MIT](LICENSE) — copyright © 2026 Alejandro
Mesejo. **Las soluciones que escribas son tuyas.** Los enunciados y el resto del material
docente están en el
[sitio web de la asignatura](https://uib-22354-programacion-avanzada.github.io/website/),
bajo licencia [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en).
