package collections.examples.set.constructing

import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

/**
    Construyendo Sets
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
}

/**
    Funciones estándar de la biblioteca.

    setOf<T>() y mutableSetOf<T>()
 */
fun demo01() {
    val set: Set<String> = setOf("one", "two", "three", "four")
    val mutableSet: MutableSet<String> = mutableSetOf("one", "two", "three", "four")
}


/**
    Sets vacíos

    emptySet(), set vacío de solo lectura.
 */
fun demo02() {
    val emptySet: Set<String> = emptySet()
}



/**
    Constructores
 */
fun demo03() {
    val linkedSet: LinkedHashSet<String> = LinkedHashSet(setOf("one", "two", "three"))
    val presizedSet: HashSet<Int> = HashSet(32)
}



/**
    Copiando sets

    toSet() y toMutableSet()
 */
fun demo04() {
    val set = setOf("one", "two", "three", "four")
    val newSet = set.toSet()
    val mutableSet = set.toMutableSet()
}



/**
    Creando listas desde funciones de otras collecciones

    filter()
 */
fun demo05() {
    val set = setOf("one", "two", "three", "four")
    val newSet = set.filter { it.length > 3 }

    val mutableSet = mutableSetOf("one", "two", "three", "four")
    val newMutableSet = mutableSet.filter { it.length > 3 }
}