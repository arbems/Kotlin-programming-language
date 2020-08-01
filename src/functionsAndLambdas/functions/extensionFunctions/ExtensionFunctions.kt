package functionsAndLambdas.functions.extensionFunctions

/**
 * Extension functions
 */

fun main() {
    demo01()
    demo02()
}

/*
   Para declarar una función de extensión, necesitamos anteponer su nombre con un tipo de receptor, es decir, el tipo que se está extendiendo.
   La palabra clave this dentro de una función de extensión corresponde al objeto receptor (el que se pasa antes del punto).
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

fun demo01() {
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'

    list.forEach{ println(it) } // print 3, 2, 1
}

/*
   Podemos hacerlo genérico.
   Declaramos el parámetro de tipo genérico antes del nombre de la función para que esté disponible en la expresión de tipo de receptor.
 */
fun <T> MutableList<T>.swapGeneric(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

fun demo02() {
    val list = mutableListOf("1", "2", "3")
    list.swapGeneric(0, 2) // 'this' inside 'swap()' will hold the value of 'list'

    list.forEach{ println(it) } // print 3, 2, 1
}