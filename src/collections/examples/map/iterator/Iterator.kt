package collections.examples.map.iterator

/**
    Map iterator
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
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")
    val iterator = map.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}

/**
    Iterador implícito, esto es posible porque extiende interfaz Collection<T>:
 */
fun demo02() {
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")

    for (item in map) {
        println(item)
    }

    for ((k, v) in map) {
        println("$k = $v")
    }
}

/**
    La función de extensión foreach() permite iterar automáticamente una colección:
 */
fun demo03() {
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")

    map.forEach {
        println(it)
    }

    map.forEach {
        (k, v) -> println("$k = $v")
    }
}