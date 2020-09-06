package classAndObjects.classes

/**
 * Clases
 */

fun main() { }

/**
 * Miembros de clase 
 */

/*
    Constructores y bloques inicializadores
    Funciones
    Propiedades
    Clases anidadas e internas
    Declaraciones de objeto
 */
class Aaa(private var variable: String = "") { // constructor principal

    // Propiedades y variables
    val variable1: String = ""

    var propiedad: String = ""
        get() {
            return field.capitalize()
        }
        set(value) {
            field = value.trim()
        }

    // Declaración de objeto
    val obj = object {
        var x: Int = 0
        var y: Int = 0
    }

    // bloque inicializador
    init {
        variable = "text"
    }

    // constructores secundarios
    constructor(variable: String, variable2: String) : this(variable) {
        //...
    }

    constructor(variable: String, variable2: String, variable3: String) : this(variable) {
        //...
    }

    // bloque inicializador
    init {
        //...
    }

    // Funciones
    fun function01(value: String) {
        variable = value
    }

    fun function02() = variable.toUpperCase()


    // Clase anidada
    class Anidada {
        // fun funcion() = propiedad // error
    }

    // Clase interna
    inner class Interna {
        fun function() = propiedad
    }
}

/**
 * Constructores y bloques inicializadores
 */

/* 
    Constructor principal 
*/
class Aac(id: Int, color: String) {
    private val idProperty = id

    init {
        println("Color: $color")
    }
}

/* Si el constructor principal no tiene anotaciones ni modificadores de visibilidad, se puede omitir la palabra clave `constructor`. */
class Aba constructor(firstName: String) { /*...*/ }
class Abb (firstName: String) { /*...*/ }
class Abc private constructor(firstName: String) { /*...*/ }
class Abd @Inject constructor(firstName: String) { /*...*/ }
class Abe @Inject public constructor(firstName: String) { /*...*/ }

/* Las propiedades declaradas en el constructor primario pueden ser mutables `var` o de solo lectura `val` */
class Acc(val firstName: String, val lastName: String, var age: Int) { /*...*/ }


/* 
    Constructores secundarios 
 */
class Add {
    var list: MutableList<String> = mutableListOf()

    constructor(brand: String) {
        list.add(brand)
        println("Constructor secundario")
    }

    constructor(brand: String, color: String) {
        list.add(brand)
        println("Constructor secundario")
    }

    init {
        println("Bloque de inicio se ejecuta antes que el cuerpo de constructores secundarios")
    }
}

/* Si la clase tiene un constructor primario, cada constructor secundario necesita delegar en el constructor primario. */
class Ada(val name: String) {
    var children = mutableListOf<Ada>()
    constructor(name: String, parent: Ada) : this(name) {
        parent.children.add(this)
    }
}

/*
    Constructor primario y secundario
 */
/* Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario */
class Aee(id: Int, brand: String) {
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

/* Si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos */
class Aff private constructor () { /*...*/ }

/* Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados */
class Agg(val customerName: String = "")

/*
 Anotaciones o modificadores de visibilidad
 */
// Si el constructor tiene anotaciones o modificadores de visibilidad, se requiere la palabra clave del constructor.
class Customer @Inject constructor(name: String) { /* ... */ }
annotation class Inject

/*
 Delegar al constructor primario
 */
// Si la clase tiene un constructor primario, cada constructor secundario necesita delegar al constructor primario, ya sea directa o indirectamente a través de otro(s) constructor(es) secundario(s).
class Person(val name: String, val firstName: String) {
    var children: MutableList<Person> = mutableListOf()

    constructor(name: String, firstName: String, parent: Person) : this(name, firstName) {
        parent.children.add(this)
    }
}

/*
 Constructor primario generado sin argumentos
 */
// Si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos.
// La visibilidad del constructor será pública. Si no desea que su clase tenga un constructor público, debe declarar un constructor primario vacío con visibilidad no predeterminada
class DontCreateMe private constructor() { /* ... */ }


/**
 * Classes
 */
class A {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

/*
 Las clases pueden contener:
 */
// Constructores y bloques inicializadores
// Propiedades
// Declaraciones de objeto
// Funciones
// Clases anidadas e internas

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

    class Origin(name: String) {} // Clases anidadas e internas

    fun ChangeOrigin(origin: Origin) { // Funciones
        this.origin = origin
    }

}

/**
 * Clases abstractas
 */
// Una clase y algunos de sus miembros pueden ser declarados abstractos. Un miembro abstracto no tiene una implementación en su clase
open class Vehicle {
    open fun draw() {}
}

abstract class Car : Vehicle() {
    abstract override fun draw()
}

/**
 * Companion objects
 */
// Si necesita escribir una función a la que se pueda llamar sin tener una instancia de clase pero necesita acceso a las partes internas de una clase,
// puede escribirla como miembro d  e una declaración de objeto dentro de esa clase.
// Aún más específicamente, si declara un objeto complementario dentro de su clase, puede acceder a sus miembros usando solo el nombre de la clase como calificador.
class MyClass {
    companion object Named {}
}