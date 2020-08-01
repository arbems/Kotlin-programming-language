package classAndObjects.inlineClasses

/**
 * Inline Classes
 * */

fun main() {
    membersInlineClass()
    inheritanceInlineClass()
}

/** Inline Class */
// inline class debe tener una única propiedad inicializada en el constructor primario.
// En tiempo de ejecución, las instancias de inline class se representarán utilizando esta propiedad única.
inline class Password(val value: String)
// No ocurre una instanciación real de la clase 'Contraseña'
// En tiempo de ejecución 'securePassword' contiene solo 'String'
val securePassword = Password("No intentes esto en producción")


/** Members */
// inline class admiten alguna funcionalidad de las clases regulares, como declarar propiedades y funciones
// inline classes no pueden tener init blocks
// inline class las propiedades no pueden tener backing fields (no lateinit/delegated properties)
inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

fun membersInlineClass() {
    val name = Name("Kotlin")
    name.greet() // El método `greet` se llama como método estático
    println(name.length) // getter de la propiedad se llama como un método estático
}


/** Inheritance */
// inline class pueden heredar de interfaces
interface Printable {
    fun prettyPrint(): String
}

inline class Name2(val s: String) : Printable {
    override fun prettyPrint(): String = "Let's $s!"
}

fun inheritanceInlineClass() {
    val name = Name2("Kotlin")
    println(name.prettyPrint()) // Todavía se llama como método estático
}

/** Representation */


/** Inline classes vs type aliases */


/** Experimental status of inline classes */
