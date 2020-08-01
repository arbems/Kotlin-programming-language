package classAndObjects.generics.genericFunctions

/**
 * Generic functions
 * */

fun main() {
    demo04()
}

/*
 * No solo las clases pueden tener parámetros de tipo. Las funciones también pueden. Los parámetros de tipo se colocan antes del nombre de la función:
 * */
fun <T> singletonList(item: T): List<T> {
    // ...
    return listOf()
}

fun <T> T.basicToString(): String {  // extension function
    // ...
    return this.toString()
}

fun demo04() {
    val l01 = singletonList<Int>(1)
    val l02 = singletonList<String>("1")

    println(1.basicToString())
    println("value-string".basicToString())
}