package collections.examples.list.constructing

import java.util.*
import kotlin.collections.HashSet

/**
    Construyendo listas
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()
    demo07()
}

/**
    Funciones estándar de la biblioteca.

    listOf<T>() y mutableListOf<T>()
  */
fun demo01() {
    val list: List<String> = listOf("one", "two", "three", "four")
    val mutableList: MutableList<String> = mutableListOf("one", "two", "three", "four") // ArrayList.
}


/**
    Listas vacías

    emptyList(), lista vacía de solo lectura.
 */
fun demo02() {
    val emptyList: List<String> = emptyList()
}


/**
    Funciones de inicializador para listas
 */
/*
    Crea una nueva lista de solo lectura con el [tamaño] especificado, donde cada elemento se calcula llamando a la función especificada
 */
fun demo03() {
    val list: List<Int> = List(3) { it * 2 }
}
/*
    Crea una nueva lista mutable con el [tamaño] especificado, donde cada elemento se calcula llamando a la función especificada.
 */
fun demo04() {
    val mutableList: List<Int> = MutableList(3) { it * 2 }
}


/**
    Constructores
 */
fun demo05() {
    val linkedList: LinkedList<String> = LinkedList(listOf("one", "two", "three"))
}


/**
    Copiando listas

    toList() y toMutableList()
 */
fun demo06() {
    val list = listOf("one", "two", "three", "four")
    val newList = list.toList()
    val mutableList = list.toMutableList()
}


/**
    Creando listas desde funciones de otras collecciones

    filter()
 */
fun demo07() {
    val list = listOf("one", "two", "three", "four")
    val newList = list.filter { it.length > 3 }

    val mutableList = mutableListOf("one", "two", "three", "four")
    val newMutableList = mutableList.filter { it.length > 3 }
}