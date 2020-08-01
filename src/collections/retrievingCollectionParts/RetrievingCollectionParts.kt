package collections.retrievingCollectionParts

/**
    Retrieving Collection Parts
 */

/*
    Recuperando partes de la colección.
    La biblioteca estándar de Kotlin contiene funciones de extensión para recuperar partes de una colección.
    Estas funciones proporcionan una variedad de formas de seleccionar elementos para la recopilación de resultados:
    enumerar sus posiciones explícitamente, especificar el tamaño del resultado y otros.
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
}


/** Slice */
/*
    slice() devuelve una lista de los elementos de la colección con índices dados. Los índices pueden pasarse como un rango o como una colección de valores enteros.
 */
fun demo01() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))
}



/** Take and drop */
/*
    Función take(), para obtener el número especificado de elementos a partir del primero.
    Para obtener los últimos elementos, use takeLast().
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3))
    println(numbers.takeLast(3))
}

/*
    Funciones drop() y dropLast(), para tomar todos los elementos excepto un número dado de primer o último elemento.
 */
fun demo03() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.drop(1))
    println(numbers.dropLast(5))
}

/*
    También puede usar predicados para definir el número de elementos para taking o dropping. Hay cuatro funciones similares a las descritas anteriormente:

    - takeWhile() es take() con un predicado: lleva los elementos hasta, pero excluye el primero que no coincide con el predicado.
      Si el primer elemento de la colección no coincide con el predicado, el resultado está vacío.

    - takeLastWhile() es similar a takeLast(): toma el rango de elementos que coinciden con el predicado desde el final de la colección.
      El primer elemento del rango es el elemento al lado del último elemento que no coincide con el predicado.
      Si el último elemento de la colección no coincide con el predicado, el resultado está vacío;

    - dropWhile() es lo opuesto a takeWhile() con el mismo predicado: devuelve los elementos del primero que no coinciden con el predicado hasta el final.

    - dropLastWhile() es lo opuesto a takeLastWhile() con el mismo predicado: devuelve los elementos desde el principio hasta el último que no coincide con el predicado.
 */
fun demo04() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.takeWhile { !it.startsWith('f') }) // print [one, two, three]
    println(numbers.takeLastWhile { it != "three" }) // print [four, five, six]
    println(numbers.dropWhile { it.length == 3 }) // print [three, four, five, six]
    println(numbers.dropLastWhile { it.contains('i') }) // print [one, two, three, four]
}




/** Chunked */
/*
    función chunked(), para dividir una colección en partes de un tamaño determinado, toma un solo argumento, el tamaño del fragmento, y devuelve una List
 */
fun demo05() {
    val numbers = (0..13).toList()
    println(numbers.chunked(3))
}

/*
    También puede aplicar una transformación para los fragmentos devueltos de inmediato. Para hacer esto, proporcione la transformación como una función lambda cuando llame a chunked().

    El argumento lambda es una parte de la colección. Cuando se llama a chunked() con una transformación, los trozos son listas de corta duración que deben consumirse directamente en esa lambda.
 */
fun demo06() {
    val numbers = (0..13).toList()
    println(numbers.chunked(3) { it.sum() })  // `it` is a chunk of the original collection
}




/** Windowed */
/*
    windowed(), puede recuperar todos los rangos posibles de los elementos de la colección de un tamaño determinado.

    A diferencia de chunked(), windowed() devuelve rangos de elementos (windows) comenzando por cada elemento de la colección.
    Todas las ventanas se devuelven como elementos de una sola Lista.
 */
fun demo07() {
    val numbers = listOf("one", "two", "three", "four", "five")
    val list: List<List<String>> = numbers.windowed(3)
    println(list) // print [[one, two, three], [two, three, four], [three, four, five]
}

/*
    proporciona más flexibilidad con parámetros opcionales:

    - step define una distancia entre los primeros elementos de dos ventanas adyacentes. Por defecto, el valor es 1, por lo que el resultado contiene ventanas que comienzan con todos los elementos.
      Si aumenta el paso a 2, recibirá solo ventanas a partir de elementos impares: primero, tercero, etc.
    - partialWindows incluye ventanas de tamaños más pequeños que comienzan desde los elementos al final de la colección. Por ejemplo, si solicita ventanas de tres elementos, no puede construirlas para los últimos dos elementos.
      La habilitación partialWindowsen este caso incluye dos listas más de tamaños 2 y 1.

    Finalmente, puede aplicar una transformación a los rangos devueltos de inmediato. Para hacer esto, proporcione la transformación como una función lambda al llamar windowed().
 */
fun demo08() {
    val numbers = (1..10).toList()
    println(numbers.windowed(3, step = 2, partialWindows = true)) // [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8, 9], [9, 10]]
    println(numbers.windowed(3) { it.sum() }) // [6, 9, 12, 15, 18, 21, 24, 27]
}

/*
    zipWithNext(), para construir ventanas de dos elementos. Crea pares de elementos adyacentes de la colección del receptor.
    Tenga en cuenta que zipWithNext() no divide la colección en pares; crea un Pair para cada elemento excepto el último.
 */
fun demo09() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.zipWithNext())
    println(numbers.zipWithNext() { s1, s2 -> s1.length > s2.length})
}