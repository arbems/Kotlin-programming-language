package classAndObjects.nestedClasses

/**
 * Nested and Inner Classes / Clases anidadas e internas
 * */

fun main() {
    val demo = Outer.Nested().foo() // == 2

    val demo2 = Outer2().Inner().foo() // == 1
}

/** Nested Classes / Clases anidadas */
// Las clases se pueden anidar en otras clases
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

/** Inner classes / Clases internas */
// Una clase anidada marcada como interna puede acceder a los miembros de su clase externa.
// Las clases internas llevan una referencia a un objeto de una clase externa.
class Outer2 {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

/** Qualified this / Calificado this */
/*
  this expression
    Para denotar el receptor actual, usamos estas expresiones:

    En un miembro de una clase, this se refiere al objeto actual de esa clase.
    En una función de extensión o una función literal con receptor, this denota el parámetro del receptor que se pasa en el lado izquierdo de un punto.
    Si this no tiene calificadores, se refiere al alcance de cierre más interno. Para referirse a this en otros ámbitos, se utilizan calificadores de etiquetas.

  Calificado this
    Para acceder a this a partir de un alcance exterior (una clase, o función de extensión, o etiquetados literal función con el receptor)
    escribimos this@labeldonde @labels una etiqueta sobre el alcance this:
*/
class A { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver
            }


            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}

/** Anonymous inner classes / Clases internas anónimas */
// Las instancias anónimas de clase interna se crean utilizando una expresión de objeto
