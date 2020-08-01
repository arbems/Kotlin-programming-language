package functionsAndLambdas.lambdas.functionTypes.examples

/** Function references */

/*
    Kotlin admite funciones como un tipo , lo que significa que puede guardar una función en una variable, usarla como otro argumento de función o incluso hacer que una función devuelva otra función.
    Esta es la característica principal para considerar que un lenguaje admite un estilo de programación funcional.
 */

fun main() {
    demo01()
    demo02()
}


fun demo01() {
    val sum: (Int, Int) -> Int = { x, y -> x + y }

    var result = applyOp(2, 3, sum)
    println(result) // print 5

    /*
        Una referencia de función se comporta como una lambda y, como tal, puede asignar esta referencia a una variable con la misma estructura:
    */
    val sumLambda: (Int, Int) -> Int = ::sum
}
/*
    función que acepta una lambda de este tipo como argumento (Int, Int) -> Int
 */
fun applyOp(x: Int, y: Int, op: (Int, Int) -> Int): Int {
    return op(x, y)
}


/*
    Function references
 */
fun demo02() {
    // applyOp(2, 3, sum) // error, no se detecta como lambda, solo espera ser llamado como una función regular. Pero mantiene la misma estructura: recibe dos enteros y devuelve uno.

    /*
        Necesitamos usar la referencia de función. Para eso, solo tiene que anteponer dos puntos al nombre de la función:
    */
    var result = applyOp(2, 3, ::sum)
    println(result) // print 5
}
/*
    En vez de una función lambda tenemos una función simple, esto está haciendo lo mismo, pero en lugar de tener una variable que mantenga la función, solo tenemos una función
 */
fun sum(x: Int, y: Int) = x + y