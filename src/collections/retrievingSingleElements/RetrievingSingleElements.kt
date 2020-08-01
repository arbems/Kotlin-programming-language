package collections.retrievingSingleElements

/**
    Retrieving Single Elements
 */

/*
    Recuperando elementos individuales.
    Las colecciones de Kotlin proporcionan un conjunto de funciones para recuperar elementos individuales de las colecciones.

    Las funciones descritas en esta página se aplican tanto a listas como a conjuntos.
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
    Retrieving by position

    elementAt()
    first() y last()
    elementAtOrNull() y elementAtOrElse()
 */
/*
    elementAt(), para recuperar un elemento en una posición específica.
    Llámalo con el número entero como argumento, y recibirás el elemento de colección en la posición dada.
    El primer elemento tiene la posición 0, y el último es (size - 1).

    elementAt()es útil para colecciones que no proporcionan acceso indexado
 */
fun demo01() {
    val numbers = linkedSetOf("one", "two", "three", "four", "five")
    println(numbers.elementAt(3)) // print four

    val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
    println(numbersSortedSet.elementAt(0)) // elements are stored in the ascending order // print four
}

/*
    También hay alias útiles para recuperar el primer y el último elemento de la colección: first() y last().
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.first()) // print one
    println(numbers.last()) // print five
}

/*
    Para evitar excepciones al recuperar un elemento con posiciones no existentes, use variaciones seguras de elementAt():

    elementAtOrNull() devuelve nulo cuando la posición especificada está fuera de los límites de la colección.
    elementAtOrElse() adicionalmente toma una función lambda que asigna un Intargumento a una instancia del tipo de elemento de colección. Cuando se llama con una posición fuera de los límites, elementAtOrElse()devuelve el resultado de la lambda en el valor dado.
 */
fun demo03() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.elementAtOrNull(5)) // print null
    println(numbers.elementAtOrElse(5) { index -> "The value for index $index is undefined"}) // print The value for index 5 is undefined
}




/**
    Retrieving by condition

    first() y last()
    firstOrNull() o find() y lastOrNull() o findLast()
 */
/*
    Funciona first() y last() también le permite buscar en una colección elementos que coincidan con un predicado dado.
    Cuando llama first() con un predicado que prueba un elemento de colección, recibirá el primer elemento en el que el predicado devuelve true.
    A su vez, last() con un predicado devuelve el último elemento.
 */
fun demo04() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.first { it.length > 3 })
    println(numbers.last { it.startsWith("f") })
}

/*
    Si ningún elemento coincide con el predicado, ambas funciones arrojan excepciones.
    Para evitarlos, use firstOrNull() y lastOrNull() en su lugar: devuelven null si no se encuentran elementos coincidentes.
 */
fun demo05() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.firstOrNull { it.length > 6 })
}

/*
    Alias:
    find() en vez de firstOrNull()
    findLast() en vez de lastOrNull()
 */
fun demo06() {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.find { it % 2 == 0 })
    println(numbers.findLast { it % 2 == 0 })
}



/**
    Random element

    random()
 */
/*
    Si necesita recuperar un elemento arbitrario de una colección, llame a la función random(). Puede llamarlo sin argumentos o con un objeto aleatorio como fuente de aleatoriedad.
 */
fun demo07() {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.random())
}




/**
    Checking existence

    contains() y containsAll()
    isEmpty() y isNotEmpty()
 */
/*
    contains(), para verificar la presencia de un elemento en una colección. Devuelve true si hay un elemento de la colección qu equals() al argumento de la función.
    Puedes llamar a contains() en el formulario del operador con la palabra clave in.
 */
fun demo08() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.contains("four"))
    println("zero" in numbers)
}

/*
    Para verificar la presencia de varias instancias juntas a la vez, la llamada containsAll() con una colección de estas instancias como argumento.
 */
fun demo09() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.containsAll(listOf("four", "two")))
    println(numbers.containsAll(listOf("one", "zero")))
}

/*
    Además, puede verificar si la colección contiene algún elemento llamando a isEmpty() o isNotEmpty().
 */
fun demo10() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.isEmpty())
    println(numbers.isNotEmpty())

    val empty = emptyList<String>()
    println(empty.isEmpty())
    println(empty.isNotEmpty())
}





