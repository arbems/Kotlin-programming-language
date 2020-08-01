// file name: VisibilityModifiers.kt
// Package
package classAndObjects.visibilityModifiers

import java.util.*

/**
 * Visibility Modifiers
 * */

// Las clases, objetos, interfaces, constructores, funciones, propiedades y sus setters pueden tener modificadores de visibilidad.
// private, protected, internal and public
// Por defecto public
fun baz() { /* ... */ }
class Bar { /* ... */ }

private fun foo() { } // visible en el interior de VisibilityModifiers.kt

public var bar: Int = 5 // property es visible en todas partes
    private set         // setter es visible solo en VisibilityModifiers.kt

internal val baz = 6    // visible dentro del mismo módulo (Un módulo es un conjunto de archivos Kotlin compilados juntos)

/** Classes and Interfaces */
// Para miembros declarados dentro de una clase:
// private - significa visible solo dentro de esta clase (incluidos todos sus miembros).
// protected - igual que private + visible en subclases también.
// internal - cualquier cliente dentro de este módulo que vea la clase declarante ve sus miembros internos.
// public - cualquier cliente que vea la clase declarante ve a sus miembros públicos.
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // public por defecto
    var e = 5 // get public y set private
        private set

    protected class Nested {
        public val f: Int = 5
    }
}

class Subclass : Outer() {
    // a no son visibles
    // b, c y d son visibles
    // e es visible pero no set
    // Nested y f son visibles

    override val b = 5   // 'b' es protected
}

class Unrelated(o: Outer) {
    // a, b no son visibles
    // c y d son visibles (mismo modulo)
    // Outer.Nested no es visible, y Nested::f no es visible tampoco
}


/** Constructors */
class C private constructor(a: Int) { /* ... */ }
// var c = C(1) // error, no puede acceder es privado
class D protected constructor(a: Int) { /* ... */ }
// var d = D(1) // error, protected es private en clase final
class E internal constructor(a: Int) { /* ... */ }
var e = E(1)
class F public constructor(a: Int) { /* ... */ }
var f = F(1)

/** Local declarations */
// Declaraciones locales
// Las variables, funciones y clases locales no pueden tener modificadores de visibilidad.
fun Abc() {
    // private var a = "" // Error, no pueden tener modificadores de visibilidad variable local

    if (Random().nextInt() % 2 == 0) {
        // private fun bar() { } // Error, no pueden tener modificadores de visibilidad función local

        fun bar() {
            println("bar")
        }

        bar() // OK
    } else {
        // bar() // Error: unresolved reference
    }

    // private class Bcd() { } // Error, no pueden tener modificadores de visibilidad clase local
}