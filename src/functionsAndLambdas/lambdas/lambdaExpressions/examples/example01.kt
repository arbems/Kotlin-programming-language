package functionsAndLambdas.lambdas.lambdaExpressions.examples

import kotlin.test.assertEquals

/**
 * Lambdas functions
 * */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
}

/*
   La expresión Lambdas y la función anónima son function literals, lo que significa que estas funciones no se declaran sino que se pasan inmediatamente como una expresión.

   Una expresión lambda siempre está rodeada por llaves, las declaraciones de argumentos van dentro de llaves y tienen anotaciones de tipo opcionales, code_body va después de una flecha -> signo.
   Si el tipo de retorno inferido de lambda no es Unit, la última expresión dentro del cuerpo lambda se trata como valor de retorno.
 */
fun demo01() {
    var sum = { a: Int, b: Int -> a + b }

    println(sum(2, 3)) // print 5
}

/*
    En Kotlin, la expresión lambda contiene una parte opcional excepto 'code body'. A continuación se muestra la expresión lambda después de eliminar la parte opcional.
 */
fun demo02() {
    val sum:(Int, Int) -> Int = { a, b -> a + b}

    println(sum(2, 3)) // print 5
}

/* Nota: No siempre requerimos una variable porque se puede pasar directamente como argumento a una función. */

/*
   Debemos declarar explícitamente el tipo de nuestra expresión lambda. Si lambda no devuelve ningún valor, entonces podemos usar: Unit
   Ejemplos lambdas con return type:
 */
fun demo03() {
    val lambda1: (Int) -> Int = { a -> a * a }
    val lambda2: (String, String) -> String = { a, b -> a + b }
    val lambda3: (Int) -> Unit = { println(it) }

    assertEquals(lambda1(4), 16)
    assertEquals(lambda2("2", "3"), "23")
    assertEquals(lambda3(4), println("4"))
}

/*
   Lambdas se puede usar como extensión de clase
   La palabra clave this se usa para la cadena y la palabra clave it se usa para el parámetro Int pasado en la lambda.
 */
fun demo04() {
    val lambda4: String.(Int) -> String = { this + it }

    println("value-string".lambda4(3))
}

/*
    it: nombre implícito de un parámetro único, se usa para representar el parámetro único que pasamos a la expresión lambda.
 */
fun demo05() {
    val numbers = arrayOf(1,-2,3,-4,5)

    println(numbers.filter { it > 0 }) // print [1, 3, 5]
}