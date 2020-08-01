package classAndObjects.classes
/**
 * Classes / Constructors
 */

fun main(args: Array<String>) {
    // Creando instancias de clases
    val motocycle = Motorcycle(1, "Green")
    val bus = Bus("Mercedes")
    val bicycle = Bicycle(1, "BH", 2.3)
}

/** Constructors */
// Constructor primario
class Motorcycle (id: Int, color: String) {

    // initializer blocks
    // Los parámetros del constructor primario se pueden usar en los bloques inicializadores. El bloque de inicio siempre se llama después del constructor primario
    init {
        println("Color: $color")
    }

    // Los parámetros también se pueden usar en inicializadores de propiedades declarados en el cuerpo de la clase
    private val idProperty = id
}

// Constructores secundarios
class Bus {
    var brands: MutableList<String> = mutableListOf()

    constructor(brand: String) {
        brands.add(brand)
        println("Constructor secundario")
    }

    constructor(brand: String, color: String) {
        brands.add(brand)
        println("Constructor secundario")
    }

    init {
        println("Bloque de inicio se ejecuta antes que el cuerpo del constructor secundario")
    }
}

// Constructor primario y secundario
class Bicycle(id: Int, brand: String) {
    var weightProperty: Double = 0.0

    val firstProperty = "Primera propiedad: $id".also(::println)

    init {
        println("Bloque inicio 1, Id: $id Brand: $brand Weight: $weightProperty") // weightProperty vacío
    }

    constructor(id: Int, brand: String, weight: Double) : this(id, brand) {
        weightProperty = weight
        println("Constructor secundario, Id: $id Brand: $brand Weight: $weightProperty")
    }

    val secondProperty = "Segunda propiedad: ${brand.length}".also(::println)

    init { // Una clase puede tener uno o más bloques de inicialización ejecutándose en serie
        println("Bloque inicio 2, Id: $id Brand: $brand Weight: $weightProperty") // weightProperty vacío
    }
}

// Anotaciones o modificadores de visibilidad
// Si el constructor tiene anotaciones o modificadores de visibilidad, se requiere la palabra clave del constructor y los modificadores van antes que ella.
class Customer @Inject constructor(name: String) { /* ... */ }
annotation class Inject

// Delegar al constructor primario
// Si la clase tiene un constructor primario, cada constructor secundario necesita delegar al constructor primario, ya sea directa o indirectamente a través de otro(s) constructor(es) secundario(s).
class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}

// Constructor primario generado sin argumentos
// Si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos.
// La visibilidad del constructor será pública. Si no desea que su clase tenga un constructor público, debe declarar un constructor primario vacío con visibilidad no predeterminada
class DontCreateMe private constructor () { /* ... */ }


/** Classes */
class A {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

// Las clases pueden contener:
//
// Constructores y bloques inicializadores
// Funciones
// Propiedades
// Clases anidadas e internas
// Declaraciones de objeto
class Hero public constructor(val id: Int) {
    var lastName = "Last name" // Propiedades
    var origin = Origin("Spain") // Declaraciones de objeto

    init {
        println("Init block")
    }

    constructor(id: Int, name: String) : this(id) {

    }

    constructor(id: Int, name: String, power: String) : this(id) {

    }

    class Origin(name: String) { } // Clases anidadas e internas

    fun ChangeOrigin(origin: Origin) { // Funciones
        this.origin = origin
    }

}