package functionsAndLambdas.lambdas.anonymousFunctions.examples

/** Anonymous Function */

fun main() {

}

/* Una función anónima es muy similar a la función normal, excepto por el nombre de la función que se omite de la declaración.
   El cuerpo de la función anónima puede ser una expresión o un bloque. */
fun demo01() {
    // anonymous function with body as an expression
    val anonymous01 = fun(x: Int, y: Int): Int = x + y

    val sum = anonymous01(3,5)
}
fun demo02() {
    // anonymous function with body as a block
    val anonymous02 = fun(a: Int, b: Int): Int {
        return a * b
    }

    val mul = anonymous02(3,5)
}


/* Return type and parameters
   - El tipo de retorno y los parámetros también se especifican de la misma manera que para la función regular, pero podemos omitir los parámetros si pueden inferirse del contexto.
   - El tipo de retorno de la función se puede inferir automáticamente de la función si es una expresión y debe especificarse explícitamente para la función anónima si es un bloque de cuerpo.
*/