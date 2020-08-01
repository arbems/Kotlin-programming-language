package collections.examples.map.constructing

import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashMap

/**
    Construyendo Maps
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

    mapOf<T>() y mutableMapOf<T>()
    apply()
 */
fun demo01() {
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")
    val mutableMap: MutableMap<Int, String> = mutableMapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")

    // La función apply() puede ayudar a mantener fluida la inicialización
    val mutableMap02 = mutableMapOf<Int, String>().apply { this[1] = "one"; this[2] = "two"; this[3] = "three"; this[4] = "four" }
}


/**
    Maps vacíos

    emptyMap(), map vacío de solo lectura del tipo especificado.
 */
fun demo02() {
    val emptyMap: Map<Int, String> = emptyMap()
}



/**
    Constructores
 */
fun demo03() {
    val linkedMap: LinkedHashMap<Int, String> = LinkedHashMap(mapOf(1 to "one", 2 to "two", 3 to "three"))
    val presizedMap: HashMap<Int, String> = HashMap(32)
}



/**
    Copiando maps
 */
fun demo04() {
    val map = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")
    val newMap = map.toMap()
    val newMutableMap = map.toMutableMap()
}


/**
    Creando maps desde funciones de otras collecciones

    filter()
 */
fun demo05() {
    val map = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")
    val newMap = map.filter { it.value.length > 3 }

    val mutableMap = mutableMapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")
    val newMutableMap = mutableMap.filter { it.value.length > 3 }
}