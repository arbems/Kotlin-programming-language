package collections.examples.set

/**
    Set Overview
 */

/*
    Una colección la cual no contiene elementos duplicados. Más formalmente, sets no contienen ningún par de elementos e1 y e2, de modo que e1.equals(e2), y como máximo un elemento nulo.
    Como implica su nombre, esta interfaz modela la abstracción del set matemático.

    - Es una agrupación desordenada de elementos.
    - Set se usa para la colección de elementos sin duplicados.
    - No se definen nuevos métodos dentro de la interfaz Set, por lo que debemos usar los métodos de la interfaz Collection solo con las subclases Set.
 */

fun main() {

}


/** Read-only & Mutable Set */
/*
    Funciones básicas:
 */
fun demo01() {
    val set: Set<String> = setOf("one", "two", "three", "four", "five")

    /*
        Query Operations
    */
    val size = set.size
    val isEmpty = set.isEmpty()
    val contains = set.contains("two")

    /*
        Bulk Operations
    */
    val containsAll = set.containsAll(listOf("one", "two"))
}



/** Mutable Set Only */
/*
    Funciones básicas:
 */
fun demo02() {
    val mutableSet: MutableSet<String> = mutableSetOf("one", "two", "three", "four", "five")

    /*
        Query Operations
    */
    val iterator = mutableSet.iterator()
    while (iterator.hasNext()) iterator.next()
    iterator.next()
    iterator.remove()

    /*
        Modification Operations
    */
    mutableSet.add("four")
    mutableSet.remove("four")

    /*
        Bulk Modification Operations
    */
    mutableSet.addAll(mutableListOf("four", "five"))
    mutableSet.removeAll(mutableListOf("four", "five"))
    mutableSet.retainAll(mutableListOf("one", "two"))
    mutableSet.clear()
}