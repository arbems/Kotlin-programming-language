package collections.constructing

import java.util.*
import kotlin.collections.HashSet

/**
    Constructing Collections
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()
    demo07()
    demo08()
    demo09()
    demo10()
    demo11()
}

/**
    Constructing from elements
 */
/*
    La forma más común de crear una colección es con las funciones estándar de la biblioteca listOf<T>(), setOf<T>(), mutableListOf<T>(), mutableSetOf<T>()
 */
fun demo01() {
    val list = listOf("one", "two", "three", "four")
    val mutableList = mutableListOf("one", "two", "three", "four")

    val set = setOf("one", "two", "three", "four")
    val mutableSet = mutableSetOf("one", "two", "three", "four")

    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1) // Generalmente creado con una función infix, se recomienda que lo use solo si el rendimiento no es crítico.
    /*  Alternativa por ejemplo, puede crear un mapa mutable y rellenarlo utilizando las operaciones de escritura. La función apply() puede ayudar a mantener fluida la inicialización aquí.
        La función apply() puede ayudar a mantener fluida la inicialización aquí. */
    val mutableMap = mutableMapOf<String, Int>().apply { this["key1"] = 1; this["key2"] = 2; this["key3"] = 3; this["key4"] = 1 }
}


/**
    Empty collections

    emptyList(), emptySet() y emptyMap()
 */
/*
    También hay funciones para crear colecciones sin ningún elemento: emptyList(), emptySet() y emptyMap().
    Al crear colecciones vacías, debe especificar el tipo de elementos que contendrá la colección.
 */
fun demo02() {
    val emptyList: List<String> = emptyList()
    val emptySet: Set<String> = emptySet()
    val emptyMap: Map<String, Int> = emptyMap()
}


/**
    Initializer functions for lists
 */
/*
    Para las listas, hay un constructor que toma el tamaño de la lista y la función de inicializador que define el valor del elemento en función de su índice.
 */
fun demo03() {
    val doubled = List(3) { it * 2 } // or MutableList if you want to change its content later
    println(doubled) // [0, 2, 4]
}



/**
    Concrete type constructors
 */
/*
    Para crear una colección de tipos concretos, como un ArrayList o LinkedList, puede utilizar los constructores disponibles para estos tipos.
    Constructores similares están disponibles para implementaciones de Set y Map.
 */
fun demo04() {
    val linkedList: LinkedList<String> = LinkedList(listOf("one", "two", "three"))
    val presizedSet: HashSet<Int> = HashSet(32)
}



/**
    Copying

    toList(), toMutableList(), toSet(), toMutableSet()
 */
/*
    Para crear una colección con los mismos elementos que una colección existente, puedes usar operaciones de copia.

    toList(), toMutableList(), toSet() su resultado es una nueva colección de los mismos elementos.
    Si agrega o elimina elementos de la colección original, esto no afectará a las copias. Las copias también pueden cambiarse independientemente de la fuente.
 */
fun demo05() {
    val sourceList = mutableListOf(1, 2, 3)
    val copyList = sourceList.toMutableList()
    val readOnlyCopyList = sourceList.toList()
    sourceList.add(4)
    println("Copy size: ${copyList.size}") // print 3

    //readOnlyCopyList.add(4)             // compilation error
    println("Read-only copy size: ${readOnlyCopyList.size}") // print 3
}
/* Estas funciones también se pueden utilizar para convertir colecciones a otros tipos, por ejemplo, crear un set a partir de un List o viceversa. */
fun demo06() {
    val sourceList = mutableListOf(1, 2, 3)
    val copySet = sourceList.toMutableSet()
    copySet.add(3)
    copySet.add(4)
    println(copySet)
}
/*
    Nuevas referencias a la misma instancia de colección. Se crean nuevas referencias cuando inicializa una variable de colección con una colección existente.
    Entonces, cuando la instancia de colección se modifica a través de una referencia, los cambios se reflejan en todas sus referencias.
 */
fun demo07() {
    val sourceList = mutableListOf(1, 2, 3)
    val referenceList = sourceList
    referenceList.add(4)
    println("Source size: ${sourceList.size}")
}
/*  La inicialización de la colección puede usarse para restringir la mutabilidad.
    Por ejemplo, si crea una referencia de List a una MutableList, el compilador generará errores si intenta modificar la colección a través de esta referencia.
 */
fun demo08() {
    val sourceList = mutableListOf(1, 2, 3)
    val referenceList: List<Int> = sourceList
    //referenceList.add(4)            //compilation error
    sourceList.add(4)
    println(referenceList) // shows the current state of sourceList // print [1, 2, 3, 4]
}



/**
    Invoking functions on other collections
 */
/*
    Las colecciones se pueden crear como resultado de varias operaciones en otras colecciones. Por ejemplo, al filtrar una lista se crea una nueva lista de elementos que coinciden con el filtro:
 */
fun demo09() {
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)
}
/*
    map() produce una lista de resultados de transformación:
 */
fun demo10() {
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })
}
/*
    associateWith() produce mapas:
 */
fun demo11() {
    val numbers: List<String> = listOf("one", "two", "three", "four")
    val map: Map<String, Int> = numbers.associateWith { it.length }
    println(map)
}