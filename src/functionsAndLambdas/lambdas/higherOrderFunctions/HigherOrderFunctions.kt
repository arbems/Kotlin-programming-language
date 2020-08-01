package functionsAndLambdas.lambdas.higherOrderFunctions

/**
   Higher-Order Functions
*/

/*
    Una función de orden superior es una función que acepta funciones como parámetros o devuelve una función.
*/

fun main() {
    demo01()
}


/*
   Las funciones de Kotlin se pueden almacenar en variables y estructuras de datos, se pueden pasar como parámetro y devolver desde otras funciones de orden superior.

   Para facilitar esto, Kotlin, como lenguaje de programación estáticamente tipado, utiliza una familia de function type para representar funciones
   y proporciona un conjunto de construcciones de lenguaje especializado como lambda expressions.
 */

/*
   Un buen ejemplo es el lenguaje de programación funcional para colecciones, que toma un valor de acumulador inicial y una función de combinación
   y construye su valor de retorno combinando consecutivamente el valor de acumulador actual con cada elemento de colección, reemplazando el acumulador:
 */
fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}
/*
   El parámetro combine tiene una function type (R, T) -> R  entonces acepta una función que toma dos argumentos de los tipos R y T y devuelve un valor de tipo R
   Se invoca dentro del ciclo for, y el valor de retorno se asigna al acumulador.

   Para llamar a fold, debemos pasarle una instancia de function type como un parámetro y
   las expresiones lambda se utilizan ampliamente para este propósito en los sitios de llamada de función de orden superior:
 */
fun demo01() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas are code blocks enclosed in curly braces.
    items.fold(0) {
        // When a lambda has parameters, they go first, followed by '->'
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // The last expression in a lambda is considered the return value:
        result
    }

    // Parameter types in a lambda are optional if they can be inferred:
    val joinedToString = items.fold("Elements:", { acc, i -> "$acc $i" })
    println(joinedToString)

    // Function references can also be used for higher-order function calls:
    val product = items.fold(1, Int::times)
    println(product)
}