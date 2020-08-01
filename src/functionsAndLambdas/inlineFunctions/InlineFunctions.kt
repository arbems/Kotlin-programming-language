package functionsAndLambdas.inlineFunctions

/**
 * Inline functions
 */

/*
    A veces es beneficioso utilizar inline functions, que proporcionan un flujo de control flexible, para funciones de orden superior.
    Las funciones de orden superior pueden tener un problema de sobrecarga de memoria, el hecho de que estén almacenados como objetos puede hacer que el uso sea desventajoso a veces, debido a la sobrecarga de memoria.
    La cuestión es que a cada objeto se le asigna espacio de memoria y a los métodos que llaman a este método también se les asigna espacio.

    Una forma de evitar el problema de la sobrecarga de memoria es declarando la función inline.
    La función y los parámetros de la función se expandirán en el sitio de la llamada, lo que significa que ayuda a reducir la sobrecarga de la llamada.

    Así que básicamente inline puede usarse cuando deseamos reducir la sobrecarga de memoria. Pero inline también aumenta el bytecode resultante. Es por eso que se debe evitar el uso de inline con funciones grandes o accesores con lógica de código grande.
 */

fun main() {

}

/*
    Declarando función inline.
    Básicamente inlinele dice al compilador que copie estas funciones y parámetros para llamar al sitio.
 */
inline fun someMethod (a: Int, func: () -> Unit): Int {
    func()
    return 2 * a
}