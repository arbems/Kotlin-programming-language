package functionsAndLambdas.functions.genericFunctions

/**
 * Generic functions
 */

fun main() {
    demo01()
    demo02()
    demo03()
}

/*
    No solo las clases pueden tener parámetros de tipo. Las funciones también pueden.
    Los parámetros de tipo se colocan antes del nombre de la función.
 */


/*
    Local function
 */
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

fun demo01() {
    // Para llamar a una función genérica, especifique los argumentos de tipo en el sitio de la llamada después del nombre de la función:
    singletonList<Int>(1) // print [1]

    // El tipo de parámetro puede ser omitido
    println(singletonList("1")) // print [1]
}


/*
    extension function
 */
fun <T> T.basicToString(item: T): String {
    return item.toString()
}

fun demo02() {
    String.basicToString(3) // print 3
}


/*
    Member function
 */
class Test() {
    fun <T> singletonList(item: T): List<T> {
        return listOf(item)
    }
}

fun demo03() {
    var list = Test().singletonList(2)

    list.forEach { println(it) } // print 2
}