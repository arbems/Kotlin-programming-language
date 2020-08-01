package classAndObjects.inheritance
/**
 * Inheritance / Herencia
 */

fun main(args: Array<String>) {
 val f = F("alberto", "moreno")
}

/** Superclass Any */
// Todas las clases en kotlin heredan de 'Any'
class Teacher(name: String) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}

/** open */
// Por defecto en Kotlin las clases son finales no se pueden heredar, para heredar de una clase hay que indicarlo con open
open class Base(id: Int) {
    public constructor(id: Int, name: String) : this(id) { /* ... */ }
}
// Si la clase derivada no tiene un constructor primario, cada constructor secundario debe inicializar el tipo base usando la palabra clave super
class Derived: Base {
    public constructor(id: Int, name: String, firstName: String) : super(id) { /* ... */ }
}
class Derived2(id: Int): Base(id) {
    public constructor(id: Int, name: String, firstName: String) : this(id) { /* ... */ }
}

/** Overriding methods */
// Sobre-escribiendo métodos
open class Shape {
    open fun draw() { /* ... */ }
    fun fill() { /* ... */ }
}
class Circle() : Shape() {
    override fun draw() { /* ... */ }
}
open class Rectangle() : Shape() {
    final override fun draw() { /* ... */ } // si se indica final ya no se puede sobreescribir en una clase que herede de esta
}

/** Overriding properties */
// Sobre-escribiendo propiedades
open class A {
    open val vertexCount: Int = 0
}
class B : A() {
    override var vertexCount = 4
}
// Puedes sobreescribir propiedad en constructor primario
class C(override var vertexCount: Int) : A() { /* ... */ }

/** Derived class initialization order */
// Clase derivada y orden inicialización
// Al diseñar una clase base, debe evitar el uso de miembros open en los constructores, inicializadores de propiedades y bloques de inicio .
open class E(val name: String) {

    init { println("Inicializando base") }

    open val size: Int = name.length.also { println("Inicializando tamaño en base: $it") }
}

class F(name: String, val lastName: String) : E(name.capitalize().also { println("Argumento para base: $it") }) {

    init { println("Inicializando derivada") }

    override val size: Int = (super.size + lastName.length).also { println("Inicializando tamaño en derivada: $it") }
}

/** Calling the superclass implementation */
// Llamando a la implementación de superclase
// El código en una clase derivada puede llamar a sus funciones de superclase y a implementaciones de accesos de propiedad usando la palabra clave super
open class Triangle {
    open fun draw() { println("Dibujando un rectángulo") }
    val borderColor: String get() = "black"
}

class FilledTriangle : Triangle() {
    override fun draw() {
        super.draw()
        println("Llenando el rectángulo")
    }

    val fillColor: String get() = super.borderColor

    // dentro de una clase interna, el acceso a la superclase de la clase externa se realiza con la palabra clave super calificada con el nombre de la clase externa super@Outer
    inner class Filler {
        fun fill() { /* ... */ }
        fun drawAndFill() {
            super@FilledTriangle.draw() // Llama a la implementación de draw() en Triangle
            fill()
            println("Drawn a filled triangle with color ${super@FilledTriangle.borderColor}") // Llama a la implementación de borderColor en Triangle
        }
    }
}


/** Overriding rules */
// Sobre-escribiendo reglas
open class Octagon {
    open fun draw() { /* ... */ }
}

interface Polygon {
    fun draw() { /* ... */ } // los miembros de la interfaz están 'open' por defecto
}

class Square() : Octagon(), Polygon {
    // El compilador requiere que se sobre-escriba draw()
    override fun draw() {
        super<Octagon>.draw() // llama a Octagon.draw()
        super<Polygon>.draw() // llama a Polygon.draw()
    }
}

/** Abstract classes */
// Clases abstractas
// Una clase y algunos de sus miembros pueden ser declarados abstractos. Un miembro abstracto no tiene una implementación en su clase
open class Vehicle {
    open fun draw() {}
}
abstract class Car : Vehicle() {
    abstract override fun draw()
}

/** Companion objects */
// Si necesita escribir una función a la que se pueda llamar sin tener una instancia de clase pero necesita acceso a las partes internas de una clase,
// puede escribirla como miembro de una declaración de objeto dentro de esa clase.
// Aún más específicamente, si declara un objeto complementario dentro de su clase, puede acceder a sus miembros usando solo el nombre de la clase como calificador.
class MyClass {
    companion object Named { }
}