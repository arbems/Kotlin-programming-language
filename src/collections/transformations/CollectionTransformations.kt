package collections.transformations

/**
    Collection Transformations
 */

/*
    La biblioteca estándar de Kotlin proporciona un conjunto de funciones de extensión para transformaciones de colecciones.
    Estas funciones crean nuevas colecciones a partir de las existentes en función de las reglas de transformación proporcionadas.
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
    demo12()
    demo13()
    demo14()
}


/**
    Mapping
 */
/*
    La transformación de map crea una colección a partir de los resultados de una función en los elementos de otra colección.

    La función básica de mapeo es map():
    Aplica la función lambda dada a cada elemento posterior y devuelve la lista de resultados.
    El orden de los resultados es el mismo que el orden original de los elementos.

    mapIndexed(), para usar adicionalmente el índice del elemento como argumento.
 */
fun demo01() {
    val numbers = setOf(1, 2, 3)

    numbers.map { it * 3 } // return List<Int>  // print [3, 6, 9]

    numbers.mapIndexed { idx, value -> value * idx } // return List<Int>  // print [0, 2, 6]
}
/*
    mapNotNull(), si la transformación produce null en ciertos elementos, puede filtrar los nulls de la colección de resultados llamando a la función mapNotNull()
 */
fun demo02() {
    val numbers = setOf(1, 2, 3)
    println(numbers.mapNotNull { if ( it == 2) null else it * 3 })
    println(numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })
}
/*
    Al transformar mapas, tiene dos opciones: transformar las claves dejando los valores sin cambios y viceversa.
    Para aplicar una transformación dada a las claves, use mapKeys(); a su vez, mapValues​​() transforma los valores.
 */
fun demo03() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value + it.key.length })
}



/**
    Zipping
 */
/*
    La transformación de compresión consiste en construir pares a partir de elementos con las mismas posiciones en ambas colecciones.
    En la biblioteca estándar de Kotlin, esto se hace mediante la función de extensión zip()

    Los elementos de la colección del receptor son los primeros elementos en estos pares. Si las colecciones tienen diferentes tamaños,
    el resultado de zip () es el tamaño más pequeño; Los últimos elementos de la colección más grande no se incluyen en el resultado.

    zip() también se puede llamar mediante infix a zip b.
 */
fun demo04() {
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    val result01: List<Pair<String, String>> = colors zip animals
    println(result01) // [(red, fox), (brown, bear), (grey, wolf)]

    val twoAnimals = listOf("fox", "bear")
    val result02: List<Pair<String, String>> = colors.zip(twoAnimals)
    println(result02) // [(red, fox), (brown, bear)]
}
/* También puede llamar a zip () con una función de transformación que toma dos parámetros */
fun demo05() {
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")

    println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color"})
}

/*
    Cuando tiene una Lista de pares, puede hacer la transformación inversa, descomprimir, que crea dos listas a partir de estos pares:
 */
fun demo06() {
    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs.unzip())
}




/**
    Association
 */
/*
    associate() debe usarse cuando el rendimiento no es crítico o es más preferible que otras opciones.

    associateWith(), esta función de asociación básica crea un mapa en el que los elementos de la colección original son claves,
    y los valores son producidos a partir de ellos por la función de transformación dada. Si dos elementos son iguales, solo el último permanece en el mapa.
 */
fun demo07() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length }) // print  {one=3, two=3, three=5, four=4}
}
/*
    associateBy(), para construir mapas con elementos de colección como valores, existe la función associateBy().
    Toma una función que devuelve una clave basada en el valor de un elemento. Si dos elementos son iguales, solo el último permanece en el mapa.
    associateBy() También se puede llamar con una función de transformación de valor.
 */
fun demo08() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.associateBy { it.first().toUpperCase() }) // {O=one, T=three, F=four}
    println(numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length })) // {O=3, T=5, F=4}
}




/**
    Flattening
 */
/*
    flatten(), puede llamarlo en una colección de colecciones, por ejemplo, una List de sets.
    La función devuelve una única List de todos los elementos de las colecciones anidadas.
 */
fun demo09() {
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten()) // print [1, 2, 3, 4, 5, 6, 1, 2]
}
/*
    flatMap(), proporciona una forma flexible de procesar colecciones anidadas. Toma una función que asigna un elemento de colección a otra colección.
    Como resultado, flatMap() devuelve una lista única de sus valores de retorno en todos los elementos. Entonces, flatMap() se comporta como una llamada posterior de map() (con una colección como resultado de mapeo) y flatten().
 */
data class StringContainer(val values: List<String>)
fun demo10() {
    val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight"))
    )
    println(containers.flatMap { it.values }) // [one, two, three, four, five, six, seven, eight]
}



/**
    String representation
 */
/*
    Si necesita recuperar el contenido de la colección en un formato legible, use funciones que transformen las colecciones en cadenas: joinToString() y joinTo().

    joinToString() crea una sola cadena a partir de los elementos de la colección en función de los argumentos proporcionados.
    joinTo() hace lo mismo pero agrega el resultado al dado Appendable object.
 */
fun demo11() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers)
    println(numbers.joinToString()) // one, two, three, four

    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString) // The list of numbers: one, two, three, four
}

/*
    Para crear una representación de cadena personalizada, puede especificar sus parámetros en los argumentos de la función separador, prefijo y postfix.
    La cadena resultante comenzará con el prefijo y finalizará con el postfix. El separador vendrá después de cada elemento, excepto el último.
 */
fun demo12() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.joinToString(separator = " | ", prefix = "start: ", postfix = ": end")) // start: one | two | three | four: end
}

/*
    Para colecciones más grandes, es posible que desee especificar el límite, una cantidad de elementos que se incluirán en el resultado.
    Si el tamaño de la colección excede el límite, todos los demás elementos serán reemplazados con un solo valor del argumento truncado.
*/
fun demo13() {
    val numbers = (1..100).toList()
    println(numbers.joinToString(limit = 10, truncated = "<...>")) // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, <...>
}

/*
    Finalmente, para personalizar la representación de los elementos, proporcione la función de transformación.
*/
fun demo14() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.joinToString { "Element: ${it.toUpperCase()}"}) // Element: ONE, Element: TWO, Element: THREE, Element: FOUR
}