package classAndObjects.inheritance

/**
 * Herencia
 */

fun main(args: Array<String>) {
    val f = F("alberto", "moreno")
}

/**
 * Superclass Any
 */
/* Todas las clases en kotlin heredan de 'Any' por defecto */
class Teacher(name: String) : Any() {
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

/**
 * open
 */
/* Por defecto en Kotlin las clases son finales no se pueden heredar, para heredar de una clase hay que indicarlo con open */
open class Base(id: Int) {
    public constructor(id: Int, name: String) : this(id) { /* ... */
    }
}

// Si la clase derivada no tiene un constructor primario, cada constructor secundario debe inicializar el tipo base usando la palabra clave super
class Derived : Base {
    public constructor(id: Int, name: String, firstName: String) : super(id) {
        /* ... */
    }
}

class Derived2(id: Int) : Base(id) {
    public constructor(id: Int, name: String, firstName: String) : this(id) {
        /* ... */
    }
}

/**
 * Sobreescribiendo métodos
 */
/* Una subclase sobreescribe un método de su superclase cuando define un método con las mismas características (nombre, número y tipo de argumentos) que el método de la superclase.
 * Para sobreescribir un método hay que indicar 'open' el método de la superclase. */
open class Shape {
    open fun draw() {
        /* ... */
    }

    fun fill() {
        /* ... */
    }
}

class Circle() : Shape() {
    override fun draw() {
        /* ... */
    }
}

/* si se indica final ya no se puede sobreescribir en una clase que herede de esta */
open class Rectangle() : Shape() {
    final override fun draw() {
        /* ... */
    }
}

/**
 * Sobreescribiendo propiedades
 */
/* Para sobreescribir una propiedad en una subclase hay que indicar **open** la propiedad de la superclase. */
open class A {
    open val vertexCount: Int = 0
}

class B : A() {
    override var vertexCount = 4
}

// Puedes sobreescribir propiedad en constructor primario
class C(override var vertexCount: Int) : A() { /* ... */ }

/**
 * Clase derivada y orden inicialización
 */
/* Al diseñar una clase base, debe evitar el uso de miembros open en los constructores, inicializadores de propiedades y bloques de inicio. */
open class E(val name: String) {

    init {
        println("Inicializando base")
    }

    open val size: Int = name.length.also { println("Inicializando tamaño en base: $it") }
}

class F(name: String, val lastName: String) : E(name.capitalize().also { println("Argumento para base: $it") }) {

    init {
        println("Inicializando derivada")
    }

    override val size: Int = (super.size + lastName.length).also { println("Inicializando tamaño en derivada: $it") }
}

/**
 * Llamando a la implementación de la superclase
 */
/* El código en una clase derivada puede llamar a sus funciones de superclase y a implementaciones de accesos de propiedad usando la palabra clave super */
open class Triangle {
    open fun draw() {
        println("Dibujando un rectángulo")
    }

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
        fun fill() { /* ... */
        }

        fun drawAndFill() {
            super@FilledTriangle.draw() // Llama a la implementación de draw() en Triangle
            fill()
            println("Drawn a filled triangle with color ${super@FilledTriangle.borderColor}") // Llama a la implementación de borderColor en Triangle
        }
    }
}


/**
 * Sobreescribiendo reglas
 */
open class Octagon {
    open fun draw() {
        /* ... */
    }
}

/* Los miembros de la interfaz están 'open' por defecto */
interface Polygon {
    fun draw() {
        /* ... */
    }
}

/* El compilador requiere que se sobreescriba draw() */
class Square() : Octagon(), Polygon {
    override fun draw() {
        super<Octagon>.draw() // llama a Octagon.draw()
        super<Polygon>.draw() // llama a Polygon.draw()
    }
}