package collections.sequences

/**
    Sequences
 */

/*
    Junto con las colecciones, la biblioteca estándar de Kotlin contiene otro tipo de contenedor: Sequence<T>

    Usar Secuencia para colecciones más grandes con más de un paso de procesamiento.

    Las secuencias le permiten evitar la creación de resultados de pasos intermedios, mejorando así el rendimiento de toda la cadena de procesamiento de la colección.
    Sin embargo, la naturaleza perezosa de las secuencias agrega algo de sobrecarga que puede ser importante al procesar colecciones más pequeñas o al hacer cálculos más simples.
    Por lo tanto, debe considerar ambos Sequence y Iterable, y decidir cuál es mejor para cada caso.
 */

fun main() {
    demo01()
    demo02()
    demo03()
    //demo04()
    demo05()
    demo06()
    demo07()
    demo08()
}

/**
    Constructing
 */
/*
    From elements

    Para crear una secuencia, llame a la función sequenceOf() que enumera los elementos como sus argumentos.
 */
fun demo01() {
    val numbersSequence = sequenceOf("four", "three", "two", "one")
}

/*
    From Iterable

    Si ya tiene un objeto Iterable (como una Lista o un Conjunto), puede crear una secuencia a partir de él llamando asSequence()
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four")
    val numbersSequence = numbers.asSequence()
}

/*
    From function

    Crea una secuencia compilándola con una función que calcule sus elementos: generateSequence()
    La generación de la secuencia se detiene cuando vuelve la función proporcionada null.

 */
fun demo03() {
    val oddNumbers = generateSequence(1) { it + 2 } // `it` is the previous element
    println(oddNumbers.take(5).toList())
    //println(oddNumbers.count())     // error: the sequence is infinite

    // Para crear una secuencia finita con generateSequence(), proporcione una función que devuelva nulo después del último elemento que necesita.
    val oddNumbersLessThan10 = generateSequence(1) { if (it < 10) it + 2 else null }
    println(oddNumbersLessThan10.count())
}

/*
    From chunks

    Finalmente, hay una función que le permite producir elementos de secuencia uno por uno o por fragmentos de tamaños arbitrarios: la función sequence()
    Esta función toma una expresión lambda que contiene llamadas de funciones yield() y yieldAll()
    Devuelven un elemento al consumidor de secuencia y suspenden la ejecución de la secuencia() hasta que el consumidor solicite el siguiente elemento.
    yield() toma un único elemento como argumento; yieldAll() puede tomar un objeto Iterable, un Iterator u otra secuencia.
    Un argumento de secuencia de yieldAll() puede ser infinito. Sin embargo, dicha llamada debe ser la última: todas las llamadas posteriores nunca se ejecutarán.
 */
fun demo05() {
    val oddNumbers = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbers.take(5).toList())
}




/**
    Sequence operations
 */
/*

 */
fun demo06() {

}




/**
    Sequence processing example
 */
/*
    Echemos un vistazo a la diferencia entre Iterable y Sequence con un ejemplo.
 */
/*
    Iterable
 */
fun demo07() {

}

/*
    Sequence
 */
fun demo08() {

}




/**
    Difference between Iterable and Sequence
 */
/*
    El procesamiento de secuencia es generalmente más rápido que el procesamiento de recolección directa cuando tenemos más de un paso de procesamiento.
    Hay algunas operaciones raras en las que no nos beneficiamos del uso de la secuencia porque tenemos que operar en toda la colección. sorted es un ejemplo de Kotlin.

    Sequences son lazy, así que las funciones intermedias para el procesamiento de Sequence no hacen ningún cálculo. En su lugar, devuelven una nueva secuencia que decora la anterior con una nueva operación.
    Todos estos cálculos se evalúan durante la operación del terminal como toList or count. Por otro lado, las funciones para el procesamiento Iterable devuelve una nueva colección.
 */
fun demo09() {
    val seq = sequenceOf(1,2,3)
    println(seq.filter { it % 2 == 1 })
    // Prints: kotlin.sequences.FilteringSequence@XXXXXXXX
    println(seq.filter { it % 2 == 1 }.toList()) // Prints: [1, 3]

    val list = listOf(1,2,3)
    println(list.filter { it % 2 == 1 }) // Prints: [1, 3]
}

/*
    El ordenamiento del procesamiento de la operación es diferente. En el procesamiento de secuencia, generalmente realizamos el procesamiento completo para un solo elemento, luego para otro, etc.
    En el procesamiento Iterable, procesamos toda la colección en cada paso.
 */
fun demo10() {
    sequenceOf(1,2,3)
            .filter { println("Filter $it, "); it % 2 == 1 }
            .map { println("Map $it, "); it * 2 }
            .toList()
    // Prints: Filter 1, Map 1, Filter 2, Filter 3, Map 3,
    listOf(1,2,3)
            .filter { println("Filter $it, "); it % 2 == 1 }
            .map { println("Map $it, "); it * 2 }
    // Prints: Filter 1, Filter 2, Filter 3, Map 1, Map 3,
}

/*
    Las secuencias son más eficientes para el procesamiento de recolección con más de un solo paso de procesamiento. Es decir, más de una sola función para el procesamiento de colecciones.
 */
fun demo11() {
    val list = listOf("one", "two", "three")

    singleStepListProcessing(list)
    singleStepSequenceProcessing(list)
}
fun singleStepListProcessing(list: List<String>): List<String> {
    return list.filter { it.length > 3 }
}

fun singleStepSequenceProcessing(list: List<String>): List<String> {
    return list.asSequence()
            .filter { it.length > 3 }
            .toList()
}

/*
    Notará que casi no hay diferencia en el rendimiento o que el procesamiento simple de listas es más rápido (porque es inline).
    Aunque luego compara la función con más de un paso de procesamiento, como twoStepListProcessing que usa filtro y luego mapa, la diferencia será visible.
 */
fun demo12() {
    val list = listOf("one", "two", "three")
    twoStepListProcessing(list)
    twoStepSequenceProcessing(list)

    val list02 = listOf(1, 2, 3)
    threeStepListProcessing(list02)
    threeStepSequenceProcessing(list02)
}
fun twoStepListProcessing(list: List<String>): List<String> {
    return list
            .filter { it.length > 3 }
            .map { it }
}

fun twoStepSequenceProcessing(list: List<String>): List<String> {
    return list.asSequence()
            .filter { it.length > 3 }
            .map { it }
            .toList()
}

fun threeStepListProcessing(list: List<Int>): Double {
    return list
            .filter { it > 3 }
            .map { it }
            .average()
}

fun threeStepSequenceProcessing(list: List<Int>): Double {
    return list.asSequence()
            .filter { it > 3 }
            .map { it }
            .average()
}