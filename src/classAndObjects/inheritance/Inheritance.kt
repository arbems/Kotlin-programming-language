package classAndObjects.inheritance

/**
 * Herencia
 */

fun main() {}

/**
 * Superclass Any
 */

/* Todas las clases en kotlin heredan de 'Any' por defecto */
class Demo : Any() {
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
open class Base(p1: Int) {
    public constructor(p1: Int, p2: String) : this(p1) {
        /* ... */
    }
}

/* Si la clase derivada no tiene un constructor primario, cada constructor secundario debe inicializar el tipo base usando la palabra clave super */
class Derived01 : Base {
    public constructor(p1: Int, p2: String, p3: String) : super(p1) {
        /* ... */
    }
}

class Derived02(p1: Int) : Base(p1) {
    public constructor(p1: Int, p2: String, p3: String) : this(p1) {
        /* ... */
    }
}

/**
 * Sobrescribiendo métodos
 */

/* Una subclase sobreescribe un método de su super clase cuando define un método con las mismas características (nombre, número y tipo de argumentos) que el método de la super clase.
   Para sobrescribir un método hay que indicar 'open' el método de la super clase. */
open class Demo01 {
    open fun function01() {
        /* ... */
    }

    fun function02() {
        /* ... */
    }
}

class Demo02 : Demo01() {
    override fun function01() {
        /* ... */
    }

    // override fun function02() {}  Error! es final y no puede ser sobrescrito
}

/* si se indica final ya no se puede sobrescribir en una clase que herede de esta */
open class Demo03 : Demo01() {
    final override fun function01() {
        /* ... */
    }
}

/* Si se indica **final override** ya no se puede sobrescribir en una clase que extienda de esta. */
class Demo04 : Demo03() {
    // override fun function01() {}  Error! es final y no puede ser sobrescrito
}


/**
 * Sobrescribiendo propiedades
 */

/* Para sobrescribir una propiedad en una subclase hay que indicar **open** la propiedad de la super clase.
   También puede anular una propiedad val con una propiedad var, pero no al revés. */
open class Demo05 {
    open val vertexCount: Int = 0
}

class Demo06 : Demo05() {
    override var vertexCount = 4
}

/* Puedes sobrescribir propiedad en constructor primario */
class Demo07(override var vertexCount: Int) : Demo05() { /* ... */ }

/**
 * Clase derivada y orden inicialización
 */

/* Al diseñar una clase base, debe evitar el uso de miembros open en los constructores, inicializadores de propiedades y bloques de inicio. */
open class Base01(val name: String) {

    init {
        println("Inicializando base")
    }

    open val size: Int = name.length.also { println("Inicializando tamaño en base: $it") }
}

class Demo08(name: String, val lastName: String) : Base01(name.capitalize().also { println("Argumento para base: $it") }) {

    init {
        println("Inicializando derivada")
    }

    override val size: Int = (super.size + lastName.length).also { println("Inicializando tamaño en derivada: $it") }
}

/**
 * Llamando a la implementación de la super clase
 */

/* El código en una clase derivada puede llamar a sus funciones de super clase y a implementaciones de accesos de propiedad usando la palabra clave super */
open class Demo09 {
    open fun function01() {
        println("print function01 super clase")
    }

    val p1: String get() = "p1 super clase"
}

class Demo10 : Demo09() {
    override fun function01() {
        super.function01()
    }

    val p2: String get() = super.p1

    /* dentro de una clase interna, el acceso a la super clase de la clase externa se realiza con la palabra clave super calificada con el nombre de la clase externa super@Outer */
    inner class Demo11 {
        private fun function01() { /* ... */
        }

        fun function02() {
            super@Demo10.function01()
            function01()
            println("Drawn a filled triangle with color ${super@Demo10.p1}") // Llama a la implementación de borderColor en Triangle
        }
    }
}


/**
 * Sobrescribiendo reglas
 */
open class Demo12 {
    open fun draw() {
        /* ... */
    }
}

/* Los miembros de la interfaz están 'open' por defecto */
interface Interface01 {
    fun draw() {
        /* ... */
    }
}

/* El compilador requiere que se sobreescriba draw() */
class Demo13 : Demo12(), Interface01 {
    override fun draw() {
        super<Demo12>.draw() // llama a class.draw()
        super<Interface01>.draw() // llama a interface.draw()
    }
}