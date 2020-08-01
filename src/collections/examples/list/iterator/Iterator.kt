package collections.examples.list.iterator

/**
    List iterator
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
}


/**
    Iterator: iterator(), hasNext() y next()
 */
fun demo01() {
    val list: List<String> = listOf("one", "two", "three")
    val iterator = list.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}

/**
    Iterador implícito, esto es posible porque extiende interfaz Collection<T>:
 */
fun demo02() {
    val list = listOf("one", "two", "three")
    for (item in list) {
        println(item)
    }
}

/**
    La función de extensión foreach() permite iterar automáticamente una colección:
 */
fun demo03() {
    val list = listOf("one", "two", "three")
    list.forEach {
        println(it)
    }
}

/**
    ListIterator, es una implementación especial para iterar listas.
 */
/*
    Itera la lista en ambas direcciones hasPrevious() y previous(). Y proporciona información sobre los indices nextIndex() y previousIndex()
 */
fun demo04() {
    val list = listOf("one", "two", "three")
    val listIterator = list.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
}

/**
    MutableIterator, para iterar colecciones mutables
  */
/*
    Extiende Iterator y puede añadir, eliminar, reemplazar elementos de una colección mientras la itera.
 */
fun demo05() {
    val list = mutableListOf("one", "two", "three", "four")
    val mutableListIterator = list.listIterator()
    mutableListIterator.next()
    mutableListIterator.remove()
    mutableListIterator.next()
    mutableListIterator.add("one")
    mutableListIterator.next()
    mutableListIterator.set("three")
    println("$list")
}