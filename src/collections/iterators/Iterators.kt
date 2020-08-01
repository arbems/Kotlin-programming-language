package collections.iterators

/**
    Iterators
 */


fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()
}

/*
    Acceso a los elementos de forma secuencial sin exponer la estructura subyacente de la colección.
    Los iteradores son útiles cuando necesita procesar todos los elementos de una colección uno por uno, por ejemplo, imprimir valores o realizar actualizaciones similares.

    Todos los que heredan de la interfaz Iterable<T>, pueden obtener iteradores, llamando a la función iterator(), llamar a la función next() devuelve este elemento y mueve la posición del iterador al siguiente elemento si existe.
 */
fun demo01() {
    val numbers = listOf("one", "two", "three", "four")
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }
}
/*
    Otra forma de pasar por una colección Iterable es el conocido bucle. Al usar for en una colección, obtienes el iterador implícitamente.
    Entonces, el siguiente código es equivalente al ejemplo anterior:
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four")
    for (item in numbers) {
        println(item)
    }
}
/*
    Finalmente, hay una función útil foreach() que le permite iterar automáticamente una colección y ejecutar el código dado para cada elemento.
    Entonces, el mismo ejemplo se vería así:
 */
fun demo03() {
    val numbers = listOf("one", "two", "three", "four")
    numbers.forEach {
        println(it)
    }
}


/**
    List iterators
 */
/*
    Para las listas, hay una implementación especial de iterador: ListIterator
    Admite listas iterativas en ambas direcciones: hacia adelante y hacia atrás. hasPrevious() y previous().
    ListIterator proporciona información sobre los índices de elementos. nextIndex() y previousIndex().

    Tener la capacidad de iterar en ambas direcciones, significa que ListIterator todavía se puede usar después de que alcanza el último elemento.
 */
fun demo04() {
    val numbers = listOf("one", "two", "three", "four")
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
}

/**
    Mutable iterators
 */
/*
    Para iterar colecciones mutables, esta MutableIterator que extiende Iterator con la función de eliminación de elementos remove().
    Por lo tanto, puede eliminar elementos de una colección mientras la itera.
 */
fun demo05() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val mutableIterator = numbers.iterator()

    mutableIterator.next()
    mutableIterator.remove()
    println("After removal: $numbers")
}
/*
    Además de eliminar elementos, MutableListIterator también puede insertar y reemplazar elementos mientras itera la lista.
 */
fun demo06() {
    val numbers = mutableListOf("one", "four", "four")
    val mutableListIterator = numbers.listIterator()

    mutableListIterator.next()
    mutableListIterator.add("two")
    mutableListIterator.next()
    mutableListIterator.set("three")
    println(numbers)
}