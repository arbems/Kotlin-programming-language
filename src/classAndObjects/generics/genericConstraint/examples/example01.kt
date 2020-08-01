package classAndObjects.generics.genericConstraint.examples

import kotlin.test.assertEquals

fun main() {
    demo01()
}

/** Generic Constraints */
fun <T: Comparable<T>> sort(list: List<T>): List<T> {
    return list.sorted()
}

fun demo01() {
    val listOfInts = listOf(5,2,3,4,1)

    val sorted = sort(listOfInts) // si intentamos pasar una lista de elementos que no implementa la interface Comparable, lanza un error

    assertEquals(sorted, listOf(1,2,3,4,5))
}