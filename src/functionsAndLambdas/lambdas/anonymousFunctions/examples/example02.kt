package functionsAndLambdas.lambdas.anonymousFunctions.examples

/**
 * Diferencia entre expresiones lambda y funciones anónimas
 * */

fun main() {

}

/* La única diferencia es el comportamiento de non-local returns.
   Los returns no locales solo se admiten para expresiones lambda pasadas a inline functions. */
fun test01() {
    ordinaryFunction {
        // return // is not allowed here
    }
}
fun ordinaryFunction(function: () -> Unit) { }


fun test02() {
    inlineFunction {
        return // is allowed here
    }
}
inline fun inlineFunction(function: () -> Unit) { }
