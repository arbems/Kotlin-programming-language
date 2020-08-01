package collections.operationsOverview

/**
 * Collection Operations Overview
 */

/*
    La biblioteca estándar de Kotlin ofrece una amplia variedad de funciones para realizar operaciones en colecciones, como obtener o agregar elementos,
    así como otras más complejas, como búsqueda, clasificación, filtrado, transformaciones, etc.
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
}

/**
 * Extension and member functions
 */
/*
    Collection operations son declaradas en la librería standard de dos maneras: member functions and extension functions.

    - Las funciones miembro definen operaciones que son esenciales para un tipo de colección.
    Por ejemplo, Collection contiene la función isEmpty() para verificar su vacío; La lista contiene get() para el acceso de índice a elementos.

    Para facilitar la creación de nuevas implementaciones, use las implementaciones esqueléticas de las interfaces de recopilación de la biblioteca estándar:
    AbstractCollection, AbstractList, AbstractSet, AbstractMap y sus contrapartes mutables.

    - Otras Collection operations se declaran como funciones de extensión. Estas son funciones de filtrado, transformación, ordenación y otras funciones de procesamiento de colecciones.
 */
fun demo01() {

}



/**
 * Common operations
 */
/*
    Las operaciones comunes están disponibles para colecciones de solo lectura y mutables. Las operaciones comunes se dividen en estos grupos:

    - Transformations
    - Filtering
    - plus and minus operators
    - Grouping
    - Retrieving collection parts
    - Retrieving single elements
    - Ordering
    - Aggregate operations

    Estas operaciones de colecciones devuelven sus resultados sin afectar la colección original.
    Por ejemplo, una operación de filtrado produce una nueva colección que contiene todos los elementos que coinciden con el predicado de filtrado.
    Los resultados de tales operaciones deben almacenarse en variables o usarse de alguna otra manera, por ejemplo, pasarse en otras funciones.
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four")
    numbers.filter { it.length > 3 }  // nothing happens with `numbers`, result is lost
    println("numbers are still $numbers")
    val longerThan3 = numbers.filter { it.length > 3 } // result is stored in `longerThan3`
    println("numbers longer than 3 chars are $longerThan3")
}
/*
    Para ciertas operaciones de colecciones, hay una opción para especificar el objeto de destino.
    El destino es una colección mutable a la que la función agrega sus elementos resultantes en lugar de devolverlos en un nuevo objeto.
    Para realizar operaciones con destinos, hay funciones separadas con el To postfix en sus nombres, por ejemplo, filterTo() en lugar de filter() o associateTo() en lugar de associate().
    Estas funciones toman la colección de destino como un parámetro adicional.
 */
fun demo03() {
    val numbers = listOf("one", "two", "three", "four")
    val filterResults = mutableListOf<String>()  //destination object
    numbers.filterTo(filterResults) { it.length > 3 }
    numbers.filterIndexedTo(filterResults) { index, _ -> index == 0 }
    println(filterResults) // contains results of both operations
}
/*
    Por conveniencia, estas funciones devuelven la colección de destino, por lo que puede crearla directamente en el argumento correspondiente de la llamada a la función:
 */
fun demo04() {
    val numbers = listOf("one", "two", "three", "four")
    // filter numbers right into a new hash set,
    // thus eliminating duplicates in the result
    val result = numbers.mapTo(HashSet()) { it.length }
    println("distinct item lengths are $result")
}



/**
 * Write operations
 */
/*
    Para colecciones mutables, también hay operaciones de escritura que cambian el estado de la colección. Dichas operaciones incluyen agregar, eliminar y actualizar elementos.

    - Write operations
    - List specific operations
    - Map specific operations.

    Para ciertas operaciones, hay pares de funciones para realizar la misma operación: una aplica la operación en el lugar y la otra devuelve el resultado como una colección separada.
 */
fun demo05() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val sortedNumbers = numbers.sorted()
    println(numbers == sortedNumbers)  // false
    numbers.sort()
    println(numbers == sortedNumbers)  // true
}

