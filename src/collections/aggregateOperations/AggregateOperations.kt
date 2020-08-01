package collections.aggregateOperations


/**
    Collection Aggregate Operations
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
    Las colecciones de Kotlin contienen funciones para operaciones agregadas de uso común , operaciones que devuelven un único valor basado en el contenido de la colección.

    min() y max() devolver el elemento más pequeño y el más grande respectivamente;
    average() devuelve el valor promedio de elementos en la colección de números;
    sum() devuelve la suma de elementos en la colección de números;
    count() devuelve el número de elementos en una colección;
 */
fun demo01() {
    val numbers = listOf(6, 42, 10, 4)

    println("Count: ${numbers.count()}")
    println("Max: ${numbers.max()}")
    println("Min: ${numbers.min()}")
    println("Average: ${numbers.average()}")
    println("Sum: ${numbers.sum()}")
}

/*
    También hay funciones para recuperar los elementos más pequeños y más grandes mediante ciertas funciones de selector o personalizadas Comparator:

    maxBy() / minBy() tome una función de selector y devuelva el elemento para el que devuelve el valor más grande o más pequeño.
    maxWith() / minWith() tomar un Comparatorobjeto y devolver el elemento más grande o más pequeño de acuerdo con eso Comparator.
 */
fun demo02() {
    val numbers = listOf(5, 42, 10, 4)
    val min3Remainder = numbers.minBy { it % 3 }
    println(min3Remainder)

    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWith(compareBy { it.length })
    println(longestString)
}

/*
    Además, hay funciones de suma avanzadas que toman una función y devuelven la suma de sus valores de retorno en todos los elementos:

    sumBy() aplica funciones que devuelven Intvalores en elementos de colección.
    sumByDouble() funciona con funciones que regresan Double.
 */
fun demo03() {
    val numbers = listOf(5, 42, 10, 4)
    println(numbers.sumBy { it * 2 })
    println(numbers.sumByDouble { it.toDouble() / 2 })
}



/**
    Fold and reduce

    reduce() y fold()
    reduceRight() y foldRight()
    reduceIndexed() y foldIndexed()
    reduceRightIndexed() y foldRightIndexed()
 */
/*
    Funciones reduce() y fold() que aplican la operación proporcionada a los elementos de la colección de forma secuencial y devuelven el resultado acumulado.
    La operación toma dos argumentos: el valor acumulado previamente y el elemento de colección.

    La diferencia entre las dos funciones es que fold() toma un valor inicial y lo usa como el valor acumulado en el primer paso,
    mientras que el primer paso reduce() usa los elementos primero y segundo como argumentos de operación en el primer paso.

    Este ejemplo muestra la diferencia: fold() se utiliza para calcular la suma de elementos duplicados. Si le pasa la misma función reduce(),
    devolverá otro resultado porque usa los elementos primero y segundo de la lista como argumentos en el primer paso, por lo que el primer elemento no se duplicará.
 */
fun demo04() {
    val numbers = listOf(5, 2, 10, 4)

    val sum = numbers.reduce { sum, element -> sum + element }
    println(sum)
    val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
    println(sumDoubled)

    //val sumDoubledReduce = numbers.reduce { sum, element -> sum + element * 2 } //incorrect: the first element isn't doubled in the result
    //println(sumDoubledReduce)
}

/*
    Para aplicar una función a elementos en el orden inverso, use funciones reduceRight() y foldRight().
    Funcionan de manera similar a fold() y reduce() pero comienzan desde el último elemento y luego continúan hasta el anterior.
    Tenga en cuenta que al plegar o reducir a la derecha, los argumentos de la operación cambian su orden: primero va el elemento y luego el valor acumulado.
 */
fun demo05() {
    val numbers = listOf(5, 2, 10, 4)
    val sumDoubledRight = numbers.foldRight(0) { element, sum -> sum + element * 2 }
    println(sumDoubledRight)
}

/*
    También puede aplicar operaciones que toman índices de elementos como parámetros. Para este propósito, utilice las funciones reduceIndexed() y foldIndexed()
    el índice del elemento de paso como primer argumento de la operación.

    Finalmente, hay funciones que aplican tales operaciones a elementos de colección de derecha a izquierda, reduceRightIndexed() y foldRightIndexed().
 */
fun demo06() {
    val numbers = listOf(5, 2, 10, 4)
    val sumEven = numbers.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum }
    println(sumEven)

    val sumEvenRight = numbers.foldRightIndexed(0) { idx, element, sum -> if (idx % 2 == 0) sum + element else sum }
    println(sumEvenRight)
}