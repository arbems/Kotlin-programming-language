package classAndObjects.generics.typeProjections.examples

import kotlin.test.assertEquals

fun main() {
    demo01()
    demo02()
}

/*
 * Copia un Array de Subtypes a una Array de Supertypes
 */
// Esto le permite al compilador saber que el argumento de entrada puede ser de cualquier tipo que sea un subtipo de Any
fun copy(from: Array<out Any>, to: Array<Any?>) {
    for (index in from.indices)
        to[index] = from[index]
}

fun demo01() {
    val from: Array<Int> = arrayOf(1, 2, 3)
    val to: Array<Any?> = arrayOfNulls(3)

    copy(from, to)
}




/*
 * Añade elementos de un Subtype a una Array de su Supertypes
 */
fun fill(dest: Array<in Int>, value: Int) {
    dest[0] = value
}

fun demo02() {
    val dest: Array<Any?> = arrayOfNulls(1)

    fill(dest, 1)
}
