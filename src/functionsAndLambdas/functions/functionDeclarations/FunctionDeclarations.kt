package functionsAndLambdas.functions.functionDeclarations

/**
 * Function declarations
 */

fun main() {
    demo01()
}

/*
   fun
   Nombre función
   Parámetros de la función entre paréntesis y separados por comas. [name]: [typed]
   Tipo de retorno [: typed]
   Cuerpo de la función entre llaves [{ /* body */ }]
*/
fun double(x: Int, y: Int): Int {
    return x * y
}
fun demo01() {
    double(3, 4)
}