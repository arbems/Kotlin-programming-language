package classAndObjects.generics.typeProjections.examples

fun main() {
    demo03()
}

/** Star Projections */
// Hay situaciones en las que no nos importa el tipo específico del valor.
// Solo queremos imprimir todos los elementos de un Array y no importa cuál sea el tipo de elementos en esta matriz.
fun printArray(array: Array<*>) {
    array.forEach { println(it) }

    // Podemos leer valores, pero no podemos escribirlos porque provocará un error de compilación
    // array[0] = 1 // error
    // array[0] = "" // error
}

fun demo03() {
    val a: Array<Int> = arrayOf(1, 2, 3);
    val b: Array<String> = arrayOf("1", "2", "3");

    printArray(a)
    printArray(b)
}