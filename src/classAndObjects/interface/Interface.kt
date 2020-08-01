package classAndObjects.`interface`
/**
 * Interfaces
 * */

import javax.swing.text.Position

fun main(args: Array<String>) {
    val child: Child = Child()
    child.bar()
}

/** Interface */
// Las interfaces en Kotlin pueden contener declaraciones de métodos abstractos, así como implementaciones de métodos.
// Lo que los hace diferentes de las clases abstractas es que las interfaces no pueden almacenar el estado.
interface MyInterface {
    fun bar()
    fun foo() {
        // optional body
    }
}
interface MyInterface2 {
    fun cup()
}

/** Implementing Interfaces */
// Una clase u objeto puede implementar una o más interfaces
class Child : MyInterface, MyInterface2 {
    override fun bar() {
        // body
    }

    override fun cup() {
        // body
    }
}

/** Properties in Interfaces */
// Propiedades en interfaces
// Puede declarar propiedades en las interfaces, pero deben ser abstractas o proporcionar implementaciones de acceso.
interface MyInterface3 {
    val prop: Int // abstract

    val propertyWithImplementation: String
        get() = "example"

    fun foo() {
        print(prop)
    }
}
class Child2 : MyInterface3 {
    override val prop: Int = 29
}

/** Interfaces Inheritance */
// Herencia de interfaces
// Una interfaz puede derivarse de otras interfaces y, por lo tanto, ambas proporcionan implementaciones para sus miembros y declaran nuevas funciones y propiedades.
// Naturalmente, las clases que implementan dicha interfaz solo son necesarias para definir las implementaciones que faltan
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
        // 'name' no es requerido
        override val firstName: String,
        override val lastName: String,
        val position: Position
) : Person

/** Resolving overriding conflicts */
// resolviendo conflictos
interface A {
    fun foo() { print("A") } // no es abstract, si la función no tiene cuerpo
    fun bar() // es abstract
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}