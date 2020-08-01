package classAndObjects.extensions

/**
 * Extensions
 * */

// Kotlin proporciona la capacidad de extender una clase con una nueva funcionalidad sin tener que heredar de la clase o usar patrones de diseño como Decorator,
// a través de declaraciones especiales llamadas extensiones.

fun main() {
    // Ahora, podemos llamar a tal función en cualquier MutableList<T>
    // 'this' dentro de 'swap()' mantendrá el valor de 'list'
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2)
    list.alberto(1, 0)
    list.forEach { println(it) }

    printClassName(Rectangle())

    Example().printFunctionType() // imprime "Class method"

    Example2().printFunctionType(1)

    MyClass.printCompanion()

    Connection(Host("kotl.in"), 443).connect()
    //Host("kotl.in").printConnectionString(443)  // error, la función de extensión no está disponible fuera de Connection

    BaseCaller().call(Base())   // "Base extension function in BaseCaller"
    DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
    DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
}

/** Funciones de extensión */
// Para declara una función de extensión necesitamos prefijar su nombre con un receiver type
// swap
// La palabra clave this dentro de una función de extensión corresponde al objeto receptor
/* fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponde a la lista
    this[index1] = this[index2]
    this[index2] = tmp
}*/

// Por supuesto, esta función tiene sentido para cualquier MutableList <T>, y podemos hacerla genérica:
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponde a la lista
    this[index1] = this[index2]
    this[index2] = tmp
}
fun <T> MutableList<T>.alberto(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponde a la lista
    this[index1] = this[index2]
    this[index2] = tmp
}

/** Extensions are resolved statically */
// Las extensiones son resueltas estaticamente
open class Shape

class Rectangle: Shape()

fun Shape.getName() = "Shape"

fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

// Si una clase tiene una función miembro, y se define una función de extensión que tiene el mismo tipo de receptor,
// el mismo nombre y es aplicable a los argumentos dados, función miembro siempre gana
class Example {
    fun printFunctionType() { println("Class method") }
}

fun Example.printFunctionType() { println("Extension function") }
// no ocurre aquí:
class Example2 {
    fun printFunctionType() { println("Class method") }
}

fun Example2.printFunctionType(i: Int) { println("Extension function") }


/** Nullable receiver */
// Tenga en cuenta que las extensiones se pueden definir con un tipo de receptor nullable.
// Dichas extensiones se pueden invocar en una variable de objeto, incluso si su valor es nulo, y pueden verificar si this == null dentro del cuerpo.
fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}

/** Propiedades de extensión */
val <T> List<T>.lastIndex: Int
    get() = size - 1
// Su comportamiento solo se puede definir proporcionando explícitamente getters / setters.
// val House.number = 1 // error: los inicializadores no están permitidos para las propiedades de extensión


/** Companion object extensions */
class MyClass {
    companion object { }  // will be called "Companion"
}

fun MyClass.Companion.printCompanion() { println("companion") }


/** Scope of extensions */
// Alcance de extensiones
// La mayoría de las veces definimos extensiones en el nivel superior, directamente debajo de los paquetes
// package org.example.declarations
fun List<String>.getLongestString() { /*...*/}

// Para usar dicha extensión fuera de su paquete de declaración, debemos importarla en el sitio de la llamada:
/* package org.example.usage

import org.example.declarations.getLongestString

fun main() {
    val list = listOf("red", "green", "blue")
    list.getLongestString()
}*/


/** Declaring extensions as members */
// Declarando extensiones como miembros
// Dentro de una clase, puede declarar extensiones para otra clase.
// Dentro de una extensión de este tipo, hay múltiples receptores implícitos, a los que se puede acceder con miembros de objetos sin un calificador.
// La instancia de la clase en la que se declara la extensión se llama receptor de envío, y la instancia del tipo de receptor del método de extensión se llama receptor de extensión.
class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() { print(port) }

    fun Host.printConnectionString() {
        printHostname()   // llamada a Host.printHostname()
        print(":")
        printPort()   // llamada a Connection.printPort()
    }

    fun connect() {
        /*...*/
        host.printConnectionString()   // llamada a la función de extensión
    }
}

// En caso de conflicto de nombre entre los miembros del receptor de envío y el receptor de extensión, el receptor de extensión tiene prioridad.
// Para referirse al miembro del receptor de despacho puede usar la sintaxis calificada.
class Connection2 {
    fun Host.getConnectionString() {
        toString()         // calls Host.toString()
        this@Connection2.toString()  // calls Connection.toString()
    }
}

// Las extensiones declaradas como miembros se pueden declarar como abiertas y anuladas en subclases.
// Esto significa que el envío de tales funciones es virtual con respecto al tipo de receptor de envío, pero estático con respecto al tipo de receptor de extensión.
open class Base { }

class Derived : Base() { }

open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    fun call(b: Base) {
        b.printFunctionInfo()   // call the extension function
    }
}

class DerivedCaller: BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}