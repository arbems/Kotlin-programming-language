package functionsAndLambdas.lambdas.lambdaExpressions

import kotlin.test.assertEquals

/**
   Lambda Expressions
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo06()
    demo07()
    demo08()
    demo09()
    demo10()
    demo11()
    demo12()
    demo13()
    demo14()
    demo15()
}



/*
    Las expresiones lambda y las funciones anónimas son 'literals functions', es decir, funciones que no se declaran, pero se pasan inmediatamente como una expresión.
 */
fun max(strings: List<String>, function: (String, String) -> Boolean) {
    val a = function(strings[0], strings[1])

    println(a)
}
/*
    La función max es una función de orden superior, toma un valor de función como segundo argumento.
    Este segundo argumento es una expresión que es en sí misma una función, es decir, una función literal.
 */
fun demo01() {
    val strings = listOf("test1", "test2")

    max(strings) { a, b -> a.length < b.length }
    // es equivalente a función anónima:
    max(strings, fun (a: String, b: String): Boolean = a.length < b.length)
}




/** Lambda expression syntax */
/*
    La forma sintáctica completa de las expresiones lambda es la siguiente:
 */
fun demo02() {
    val sum01: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    // es equivalente a:
    val sum02 = { x: Int, y: Int -> x + y }

    println(sum01(3, 5))
    println(sum02(3, 5))
}

/*
   Una expresión lambda siempre está rodeada de llaves, las declaraciones de parámetros en la forma sintáctica completa van dentro de llaves y
   tienen anotaciones de tipo opcionales, el cuerpo va tras un signo ->.

   Si el tipo de retorno inferido de lambda no es Unit, la última (o posiblemente única) expresión dentro del cuerpo lambda se trata como el valor de retorno.
*/





/** Passing trailing lambdas */
/*
    Si el último parámetro de una función es una función, entonces una expresión lambda pasada como el argumento correspondiente se puede colocar fuera de los paréntesis:
 */
fun demo03() {
    val items = listOf(1, 2, 3)

    items.fold(1) { acc, e -> acc * e }
}

/*
    Dicha sintaxis también se conoce como trailing lambda.

    Si el lambda es el único argumento para esa llamada, los paréntesis pueden omitirse por completo:
*/
fun test01(function: () -> Unit) { /* ... */ }

fun demo04() {
    test01 { println("...") }
}




/** it: implicit name of a single parameter */
/*
    Es muy común que una expresión lambda tenga solo un parámetro.
    Si el compilador puede descifrar la firma por sí mismo, se le permite no declarar el único parámetro y omitir ->

    El parámetro se declarará implícitamente bajo el nombre it:
*/
fun demo05() {
    val result01 = listOf(1, 2, 3).filter(predicate = { a:Int -> a > 1 })
    // es equivalente a:
    val result02 = listOf(1, 2, 3).filter { it > 1 } // this literal is of type '(it: Int) -> Boolean'

    println(result01) // [2, 3]
    println(result02) // [2, 3]
}





/** Returning a value from a lambda expression */
/*
   Podemos devolver explícitamente un valor de lambda utilizando la sintaxis de return.
   De lo contrario, el valor de la última expresión se devuelve implícitamente.

   Por lo tanto, los dos fragmentos siguientes son equivalentes:
*/
fun demo06() {
    val ints = listOf(1, 2, 3)

    val a = ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }

    // Esto devuelve el mismo resultado, el valor de la última expresión se devuelve implícitamente
    val b = ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    }

    assertEquals(a, b)
}

/*
    Esta convención, junto con pasar una expresión lambda fuera del paréntesis, permite el código LINQ-style
 */
fun demo07() {
    val strings = listOf("eeeee", "bb", "c", "dd", "aaaaa")

    val result: List<String> = strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }

    println(result)
}





/** Underscore for unused variables (since 1.1) */
/*
    Si el parámetro lambda no se utiliza, puede colocar un guión bajo en lugar de su nombre:
 */
fun demo08() {
    val map = mapOf(1 to "x", 2 to "y", -1 to "zz")

    map.forEach { (_, value) -> println("$value!") }
}




/** Destructuring in lambdas (since 1.1) */
/*
   Si una lambda tiene un parámetro del tipo Pair, (o Map.Entry, o cualquier otro tipo que tenga las funciones apropiadas de componentN),
   puede introducir varios parámetros nuevos en lugar de uno poniéndolos entre paréntesis:
*/
fun demo09() {
    val map = mapOf(1 to "x", 2 to "y", -1 to "zz")
    map.mapValues { entry -> "${entry.value}!" }
    map.mapValues { (key, value) -> "$value!" }
}

/*
   Tenga en cuenta la diferencia entre declarar dos parámetros y declarar un pair de desestructuración en lugar de un parámetro:

   { a -> ... } // 1 parámetro
   { a, b -> ... } // 2 parámetros
   { (a, b) -> ... } // 1 destructured pair
   { (a, b), c -> ... } // 1 destructured pair y otro parámetro
*/

/*
    Si un componente del parámetro desestructurado no se utiliza, puede reemplazarlo con el guión bajo para evitar inventar su nombre:
 */
fun demo10() {
    val map = mapOf(1 to "x", 2 to "y", -1 to "zz")
    map.mapValues { (_, value) -> "$value!" }
}

/*
    Puede especificar el tipo para todo_ el parámetro desestructurado o para un componente específico por separado:
 */
fun demo11() {
    val map = mapOf(1 to "x", 2 to "y", -1 to "zz")

    map.mapValues { (_, value): Map.Entry<Int, String> -> "$value!" }

    map.mapValues { (_, value: String) -> "$value!" }
}





/** Closures */
/*
   Una expresión lambda o una función anónima (así como una función local y una expresión de objeto) pueden acceder a su cierre,
   es decir, las variables declaradas en el ámbito externo. Las variables capturadas en el cierre se pueden modificar en la lambda:
 */
fun demo12() {
    val ints = listOf(1, 2, 3)

    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    println(sum)
}





/** Function literals with receiver */
/*
   Los function types con receptor, como A.(B) -> C, se pueden instanciar con una forma especial de literales de función: literales de función con receptor.
   Kotlin proporciona la capacidad de llamar a una instancia de un tipo de función con el receptor que proporciona el objeto receptor.

   Dentro del cuerpo de la función literal, el objeto receptor pasado a una llamada se convierte en un implícito this,
   para que pueda acceder a los miembros de ese objeto receptor sin ningún calificador adicional, o acceder al objeto receptor utilizando un this expression.
   Este comportamiento es similar a las extension functions, que también le permiten acceder a los miembros del objeto receptor dentro del cuerpo de la función.

   Aquí hay un ejemplo de una función literal con receptor junto con su tipo, donde se llama a plus en el objeto receptor:
 */
fun demo13() {
    val sum01: Int.(Int) -> Int = { other -> plus(other) }
    // es equivalente a:
    val sum02: Int.(Int) -> Int = { other -> this + other }

    assertEquals(3.sum01(4), 3.sum02(4))
}

/*
   La sintaxis de la función anónima le permite especificar el tipo de receptor de una función literal directamente.
   Esto puede ser útil si necesita declarar una variable de un tipo de función con el receptor y usarla más tarde.
 */
fun demo14() {
    val sum = fun Int.(other: Int): Int = this + other

    assertEquals(3.sum(4), 7)
}



/** type-safe builders */
/*
   Las expresiones lambda se pueden usar como literales de función con el receptor cuando el tipo de receptor se puede inferir del contexto.
   Uno de los ejemplos más importantes de su uso es type-safe builders:
 */
class HTML {
    fun body() { /* ... */ }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}

fun demo15() {
    html {       // lambda with receiver begins here
        body()   // calling a method on the receiver object
    }
}