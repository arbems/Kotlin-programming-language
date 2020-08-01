package collections.examples.set.iterator

/**
    Set iterator
 */

fun main() {
    demo01()
    demo02()
    demo03()
}


/**
    Iterator: iterator(), hasNext() y next()
 */
fun demo01() {
    val set: Set<String> = setOf("one", "two", "three")
    val iterator = set.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}

/**
    Iterador implícito, esto es posible porque extiende interfaz Collection<T>:
 */
fun demo02() {
    val set = setOf("one", "two", "three")
    for (item in set) {
        println(item)
    }
}

/**
    La función de extensión foreach() permite iterar automáticamente una colección:
 */
fun demo03() {
    val set = setOf("one", "two", "three")
    set.forEach {
        println(it)
    }
}