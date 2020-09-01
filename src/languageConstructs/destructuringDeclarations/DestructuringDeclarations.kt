package languageConstructs.destructuringDeclarations

/**
 * Declaraciones de desestructuración
 *
 * */

/*
 * Una declaración de desestructuración crea múltiples variables a la vez.
 *
 * La desestructuración es una característica poderosa del lenguaje y su uso puede resultar en un código más conciso y fácil de leer.
 * Sin embargo, su mecanismo posicional es inherentemente diferente al significado asociativo de las variables de clase y puede dar lugar a errores que son difíciles de identificar.
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()
}

/*
    Desestructurar un objeto en una serie de variables
 */
fun demo01() {
    val person = Person("Alberto", 37)

    val (name, age) = person

    println(name)
    println(age)
}

data class Person(val name: String, val age: Int) {
    // Dado que las clases de datos declaran automáticamente funciones componentN(), las declaraciones de desestructuración funcionan aquí.
}

/*
    Desestructuración en bucle for
 */
fun demo02() {
    val persons = listOf<Person>()

    for ((name, age) in persons) {
        // ...
    }
    // or
    persons.forEach { (name, age) ->
        // ...
    }
}

/*
    Devolver dos valores de una función
 */
fun demo03() {

    fun function(name: String, age: Int): Person {
        // computations

        return Person(name, age)
    }

    // Ahora, para usar esta función:
    val (name, age) = function("Carlos", 27)
}

/*
    Desestructuración en Lambdas

    { a -> ... } // one parameter
    { a, b -> ... } // two parameters
    { (a, b) -> ... } // a destructured pair
    { (a, b), c -> ... } // a destructured pair and another parameter
 */
fun demo04() {
    //
    val persons = listOf<Person>()

    persons.map { (name, age) ->
        "$name! $age"
    }
}

/*
    Omitir variables innecesarias
 */
fun demo05() {
    val person = Person("Alberto", 37)
    val persons = listOf<Person>()

    val (_, age) = person

    for ((name, _) in persons) {
        // ...
    }

    persons.map { (_, age) ->
        // ...
    }
    }

/*
    Desestructuración sin clases de datos

    Cuidado: Desordenar la secuencia de identificadores desestructurados resultará en un problema semántico.
 */
class Book(val author: String, val title: String, val year: Int) {
    operator fun component1(): String = author
    operator fun component2(): String = title
    operator fun component3(): Int = year
}
fun demo06() {
    val book = Book("Jesus", "Bonito titulo", 32)
    val (author, title, _) = book

    println(author)
    println(title)
}