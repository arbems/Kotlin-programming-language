package collections.filtering

/**
    Filtering
 */

/*
    La biblioteca estándar contiene un grupo de funciones de extensión que le permiten filtrar colecciones en una sola llamada.

    Estas funciones dejan la colección original sin cambios, por lo que están disponibles tanto para colecciones mutables como de solo lectura.
    Para operar el resultado del filtrado, debe asignarlo a una variable o encadenar las funciones después del filtrado.

    En Kotlin, las condiciones de filtrado están definidas por predicados: funciones lambda que toman un elemento de colección y devuelven un valor booleano:
    true significa que el elemento dado coincide con el predicado, false significa lo contrario.
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
}


/**
    Filtering by predicate
 */
/*
    La función básica de filtrado es filter(), devuelve los elementos de la colección que coinciden con un predicado.
    Tanto para List como para Set, la colección resultante es una List, para Map es un Map.
 */
fun demo01() {
    val numbers = listOf("one", "two", "three", "four")
    val filteredList: List<String> = numbers.filter { it.length > 3 }
    println(filteredList) // print [three, four]

    val animals: Set<String> = setOf("pig", "dog", "shark", "canary")
    val filteredSet: List<String> = animals.filter { it.length > 3 }
    println(filteredSet) // print [shark, canary]

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap: Map<String, Int> = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
    println(filteredMap) // print {key11=11}
}

/*
    filterIndexed(), para disponer de la posición de los elementos, tiene un predicado con dos argumentos: el índice y el valor de un elemento.
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four")
    val filteredList: List<String> = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 5) }
    println(filteredList) // print [three, four]
}

/*
    filterNot(), para filtrar colecciones por condiciones negativas. Devuelve una lista de elementos para los cuales el predicado produce falso.
 */
fun demo03() {
    val numbers = listOf("one", "two", "three", "four")
    val filteredNot = numbers.filterNot { it.length <= 3 }
    println(filteredNot)
}

/*
    filterIsInstance() devuelve elementos de colección de un tipo dado. Al ser llamado en un List<Any>, filterIsInstance<T>() devuelve una List<T>,
    lo que le permite llamar a funciones del tipo T en sus elementos.
 */
fun demo04() {
    val numbers = listOf(null, 1, "two", 3.0, "four")

    numbers.filterIsInstance<String>().forEach {
        println(it.toUpperCase()) // TWO FOUR
    }
}
/*
    filterNotNull() devuelve todos los elementos no nulos. Al ser llamado en un Lista<T?>, FilterNotNull() devuelve una Lista<T: Any>, lo que le permite tratar los elementos como objetos no nulos.
 */
fun demo05() {
    val numbers = listOf(null, "one", "two", null)
    numbers.filterNotNull().forEach {
        println(it.length)   // length is unavailable for nullable Strings
    }
}




/**
    Partitioning
 */
/*
    partition(), filtra una colección por un predicado y mantiene los elementos que no coinciden en una lista separada.
    Entonces, tiene un par de listas como valor de retorno: la primera lista que contiene elementos que coinciden con el predicado y la segunda que contiene todo lo demás de la colección original.
 */
fun demo06() {
    val numbers = listOf("one", "two", "three", "four")
    val (match, rest) = numbers.partition { it.length > 3 }

    println(match)
    println(rest)
}



/**
    Testing predicates
 */
/*
    Finalmente, hay funciones que simplemente prueban un predicado contra elementos de la colección:
 */

/* any() devuelve verdadero si al menos un elemento coincide con el predicado dado. */
fun demo07() {

}

/* none() devuelve verdadero si ninguno de los elementos coincide con el predicado dado. */
fun demo08() {

}

/* all() devuelve verdadero si todos los elementos coinciden con el predicado dado.
   Tenga en cuenta que all() devuelve true cuando se llama con cualquier predicado válido en una colección vacía. */
fun demo09() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.any { it.endsWith("e") })
    println(numbers.none { it.endsWith("a") })
    println(numbers.all { it.endsWith("e") })

    println(emptyList<Int>().all { it > 5 })   // vacuous truth
}
/* any() y none() también se pueden usar sin un predicado: en este caso, solo verifican el vacío de la colección.
   any() devuelve true si hay elementos y false si no los hay; none() hace lo contrario. */
fun demo10() {
    val numbers = listOf("one", "two", "three", "four")
    val empty = emptyList<String>()

    println(numbers.any())
    println(empty.any())

    println(numbers.none())
    println(empty.none())
}




