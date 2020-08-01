package classAndObjects.dataClasses

/**
 * Data Classes
 * */

/*
   Una data class es una clase que sólo contiene estado y no realiza ninguna operación.
   La ventaja de utilizar data classes en vez de clases normales es que Kotlin nos aporta una cantidad inmensa de código autogenerado.

   Data classes nos aporta:
   - Las properties declaradas en el constructor
   - equals() / hashCode()
   - Funciones llamadas componentX()
   - Un método copy(), que nos será de mucha utilidad cuando utilicemos objetos inmutables.

   Requisitos:
   - Constructor primario al menos un parámetro
   - Todos los parámetros del constructor primario deben estar marcados con var o val
   - Data classes no pueden ser abstract, open, sealed o inner
*/

fun main() {
    propertiesDeclared()
    copying()
    destructuringDeclarations()
    destructuringMap()
    destructuringPerson()
}

/** data */
data class User(val name: String, val age: Int)

/** Properties Declared in the Class Body */
// Propiedades declaradas en el cuerpo de la clase
// Sólo la propiedad name será utilizada dentro de los toString(), equals(), hashCode(), y copy() y solo name formara parte de componentX().
data class Person(val name: String) {
    var age: Int = 0
}
// Si bien dos Person objetos pueden tener edades diferentes, serán tratados como iguales.
fun propertiesDeclared() {
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20
    println("person1 == person2 = ${person1.equals(person2)}") // true
}

/** Copying */
// A menudo ocurre que necesitamos copiar un objeto que altere algunas de sus propiedades, pero manteniendo el resto sin cambios. Para esto se genera la función copy().
// Cuando se trabaja con inmutabilidad, para cambiar un estado de un objeto, en realidad lo que se hace es copiarlo con el nuevo valor, y para eso la función copy de las data classes.
// La función copy puede recibir tantos parámetros como valores a cambiar.
fun copying() {
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println("jack == olderJack = ${jack.equals(olderJack)}") // false
}

/** Data Classes and Destructuring Declarations */
// Clases de datos y declaraciones de desestructuración
// Para esto sirven las funciones componentX, gracias a ellas puedes descomponer una data class en variables
fun destructuringDeclarations() {
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // prints "Jane, 35 years of age"
}
// Descomponer los pares de un map en un bucle
fun destructuringMap() {
    val map = mapOf(1 to "a", 2 to "b")
    for ((key, value) in map) {
        println("key: $key, value: $value")
    }
}
fun destructuringPerson() {
    val person = Person("Jane")
    person.age = 35
    // val (name, age) = person // error, age no forma parte del constructor
    val(name) = person
}
