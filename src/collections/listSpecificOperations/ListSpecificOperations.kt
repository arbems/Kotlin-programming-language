package collections.listSpecificOperations

import kotlin.math.sign

/**
    List Specific Operations
 */

fun main() {

}



/**
    Retrieving elements by index

    elementAt(), first(), last()
    get() or [index]
    getOrElse()
    getOrNull()
 */
/*
    Las listas son compatibles con todas las operaciones comunes para la recuperación de elemento: elementAt(), first(), last(), y otros que figuran en la recuperación de Single Elements.
    Lo que es específico para las listas es el acceso de índice a los elementos, por lo que la forma más sencilla de leer un elemento es recuperarlo por índice.
    Eso se hace con la función get() con el índice pasado en el argumento o la [index] sintaxis abreviada.

    Si el tamaño de la lista es menor que el índice especificado, se genera una excepción. Hay otras dos funciones que lo ayudan a evitar tales excepciones:
        - getOrElse() le permite proporcionar la función para calcular el valor predeterminado que se devolverá si el índice no está presente en la colección.
        - getOrNull() devuelve null como el valor predeterminado.
 */
fun demo01() {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.get(0))
    println(numbers[0])
    //numbers.get(5) // exception!
    println(numbers.getOrNull(5)) // null
    println(numbers.getOrElse(5, {it})) // 5
}




/**
    Retrieving list parts

    subList()
 */
/*
    Además de las operaciones comunes para recuperar partes de la colección, las listas proporcionan la función subList() que devuelve una vista del rango de elementos especificado como una lista.
    Por lo tanto, si un elemento de la colección original cambia, también cambia en las sublistas creadas previamente y viceversa.
 */
fun demo02() {
    val numbers = (0..13).toList()
    println(numbers.subList(3, 6))
}



/**
    Finding element positions

    indexOf() y lastIndexOf()
    indexOfFirst() y indexOfLast()
    indexOfLast() y indexOfLast()
    binarySearch()
 */
/*
    Búsqueda lineal
    En cualquier lista, puede encontrar la posición de un elemento utilizando las funciones indexOf() y lastIndexOf().
    Devuelven la primera y la última posición de un elemento igual al argumento dado en la lista. Si no hay tales elementos, ambas funciones regresan -1.
 */
fun demo03() {
    val numbers = listOf(1, 2, 3, 4, 2, 5)
    println(numbers.indexOf(2))
    println(numbers.lastIndexOf(2))
}

/*
    También hay un par de funciones que toman un predicado y buscan elementos que coincidan:

    indexOfFirst() devuelve el índice del primer elemento que coincide con el predicado o -1 si no hay tales elementos.
    indexOfLast() devuelve el índice del último elemento que coincide con el predicado o -1 si no hay tales elementos.
 */
fun demo04() {
    val numbers = mutableListOf(1, 2, 3, 4)
    println(numbers.indexOfFirst { it > 2})
    println(numbers.indexOfLast { it % 2 == 1})
}



/*
    Búsqueda binaria en listas ordenadas
    Funciona mucho más rápido que otras funciones integradas de búsqueda, pero requiere que la lista este ordenada en orden ascendente de acuerdo con un cierto orden:
    natural u otro proporcionada en el parámetro de la función. De lo contrario, el resultado es indefinido.

    binarySearch(), para buscar un elemento en una lista ordenada. Si tal elemento existe, la función devuelve su índice; de lo contrario, devuelve (-insertionPoint - 1)
    dónde insertionPointestá el índice donde se debe insertar este elemento para que la lista permanezca ordenada.
    Si hay más de un elemento con el valor dado, la búsqueda puede devolver cualquiera de sus índices.

    También puede especificar un rango de índice para buscar: en este caso, la función busca solo entre dos índices proporcionados.
 */
fun demo05() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.sort()
    println(numbers)
    println(numbers.binarySearch("two"))  // 3
    println(numbers.binarySearch("z")) // -5
    println(numbers.binarySearch("two", 0, 2))  // -3
}

/*
    Comparador de búsqueda binaria
    Cuando los elementos de la lista no  son Comparable, debe proporcionar un Comparator para usar en la búsqueda binaria.
    La lista se debe ordenar en orden ascendente de acuerdo con esto Comparator.
 */
data class Product(val name: String, val price: Double)
fun demo06() {
    val productList = listOf(
            Product("WebStorm", 49.0),
            Product("AppCode", 99.0),
            Product("DotTrace", 129.0),
            Product("ReSharper", 149.0))

    println(productList.binarySearch(Product("AppCode", 99.0), compareBy<Product> { it.price }.thenBy { it.name }))
}

/*
    Los comparadores personalizados también son útiles cuando una lista usa un orden diferente al natural, por ejemplo, un orden de elementos String que no distingue entre mayúsculas y minúsculas.
 */
fun demo07() {
    val colors = listOf("Blue", "green", "ORANGE", "Red", "yellow")
    println(colors.binarySearch("RED", String.CASE_INSENSITIVE_ORDER)) // 3
}


/*
    Comparación de búsqueda binaria
    La búsqueda binaria con función de comparación le permite encontrar elementos sin proporcionar valores de búsqueda explícitos.
    En cambio, toma una función de comparación que asigna elementos a valores Int y busca el elemento donde la función devuelve cero.
    La lista se debe ordenar en orden ascendente de acuerdo con la función proporcionada; en otras palabras, los valores de retorno de comparación deben crecer de un elemento de la lista al siguiente.
 */
fun priceComparison(product: Product, price: Double) = sign(product.price - price).toInt()
fun demo08() {
    val productList = listOf(
            Product("WebStorm", 49.0),
            Product("AppCode", 99.0),
            Product("DotTrace", 129.0),
            Product("ReSharper", 149.0))

    println(productList.binarySearch { priceComparison(it, 99.0) })
}




/**
    List write operations

    add() y addAll()
    set() or []
    fill()
    removeAt()
    sort(), sortDescending(), sortBy()
    shuffle()
    reverse()
 */
/*
    Adding

    Para agregar elementos a una posición específica en una lista, use add() y addAll() proporcione la posición para la inserción de elementos como argumento adicional.
    Todos los elementos que vienen después de la posición se desplazan hacia la derecha.
 */
fun demo09() {
    val numbers = mutableListOf("one", "five", "six")
    numbers.add(1, "two")
    numbers.addAll(2, listOf("three", "four"))
    println(numbers)
}

/*
    Updating

    Las listas también ofrecen una función para reemplazar un elemento en una posición determinada, set() y su forma de operador []. set() no cambia los índices de otros elementos.
 */
fun demo10() {
    val numbers = mutableListOf("one", "five", "three")
    numbers[1] =  "two"
    println(numbers)
}

/*
    fill() simplemente reemplaza todos los elementos de la colección con el valor especificado.
 */
fun demo11() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.fill(3)
    println(numbers)
}

/*
    Removing

    Para eliminar un elemento en una posición específica de una lista, use la función removeAt() que proporciona la posición como argumento.
    Todos los índices de elementos que vienen después del elemento que se elimina disminuirá en uno.
 */
fun demo113() {
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.removeAt(1)
    println(numbers)
}

/*
    Sorting

    Las funciones de clasificación in situ tienen nombres similares a las funciones que se aplican a las listas de solo lectura, pero sin el sufijo ed / d:
        - sort* en lugar de sorted* en los nombres de todas las funciones de ordenación: sort(), sortDescending(), sortBy(), y así sucesivamente.
        - shuffle() en lugar de shuffled().
        - reverse() en lugar de reversed().
 */
/*
    asReversed() invocado en una lista mutable devuelve otra lista mutable que es una vista inversa de la lista original.
    Los cambios en esa vista se reflejan en la lista original. El siguiente ejemplo muestra funciones de clasificación para listas mutables:
 */
fun demo12() {
    val numbers = mutableListOf("one", "two", "three", "four")

    numbers.sort()
    println("Sort into ascending: $numbers")
    numbers.sortDescending()
    println("Sort into descending: $numbers")

    numbers.sortBy { it.length }
    println("Sort into ascending by length: $numbers")
    numbers.sortByDescending { it.last() }
    println("Sort into descending by the last letter: $numbers")

    numbers.sortWith(compareBy<String> { it.length }.thenBy { it })
    println("Sort by Comparator: $numbers")

    numbers.shuffle()
    println("Shuffle: $numbers")

    numbers.reverse()
    println("Reverse: $numbers")
}