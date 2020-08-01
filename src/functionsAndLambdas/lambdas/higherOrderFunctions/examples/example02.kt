package functionsAndLambdas.lambdas.higherOrderFunctions.examples

import functionsAndLambdas.lambdas.anonymousFunctions.examples.test01

/** Higher-order function */

fun main() {
    demo03()
    demo04()
    demo05()
}

/*
    Función de orden superior
 */
fun opera(list: List<Int>, value: Int, function: (Int, Int) -> Int): List<Int> {
    val newList = arrayListOf<Int>()
    for(item in list)
    {
        newList.add(function(item, value));
    }

    return newList
}



fun demo03() {
    /*
        variable tipo función
     */
    val function = fun (x: Int, y: Int) = x + y

    val result = opera(listOf<Int>(1, 2, 3), 4, function)

    println(result) // [5, 6, 7]
}

fun demo04() {
    /*
        función anónima
     */
    val result = opera(listOf<Int>(1, 2, 3), 4, fun (x, y) = x + y)

    println(result) // [5, 6, 7]
}

fun demo05() {
    /*
        lambda
     */
    val function = { x: Int, y: Int -> x + y }

    val result01 = opera(listOf<Int>(1, 2, 3), 4, function)
    // es lo mismo a
    val result02 = opera(listOf<Int>(1, 2, 3), 4) { x: Int, y: Int -> x + y } // Si el lambda es el último parámetro puede ir fuera de los paréntesis

    println(result01) // [5, 6, 7]
    println(result02) // [5, 6, 7]
}