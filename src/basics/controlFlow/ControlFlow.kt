package basics.controlFlow

import java.lang.Integer.parseInt
import kotlin.random.Random

/**
 * Control flow / Control de flujos
 */

fun main() {
    ifExpressions()
    whenExpressions()
    loops()
    whileLoops()
}

/**
 * Expresiones con If
 */
fun ifExpressions() {
    val a = Random.nextInt(0, 15)
    val b = 9
    var max = a

    // Uso tradicional
    if (a < b) max = b

    // Con else
    if (a > b) {
        max = a
    } else {
        max = b
    }

    max = if (a > b) {
        a
    } else {
        b
    }

    // Como expresión
    max = if (a > b) a else b
}

/**
 * Expresiones con When
 */
fun whenExpressions() {
    val a = Random.nextInt(1,10)
    when (a) {
        1 -> println("a == 1")
        2 -> println("a == 2")
        else -> { // Note the block
            println("a is neither 1 nor 2")
        }
    }

    when (a) {
        0, 1 -> println("a == 0 or a == 1")
        else -> println("otherwise")
    }

    val s = "1"
    when (a) {
        parseInt(s) -> println("s encodes a")
        else -> println("s does not encode a")
    }

    val validNumbers = arrayOf(1,2,3,4)
    when (a) {
        in 1..5 -> println("a is in the range")
        in validNumbers -> println("a is valid")
        !in 10..20 -> println("a is outside the range")
        else -> println("none of the above")
    }

    hasPrefix(1)
    hasPrefix("1")
}
fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}

/**
 * Bucles
 */
fun loops() {
    val collection = arrayOf(1,2,3)
    for (item in collection) println(item)

    for (item: Int in collection) { /* ... */ }

    for (i in 1..3) { println(i) }
    for (i in 6 downTo 0 step 2) { println("downTo: $i") }

    for (i in collection.indices) {
        println(collection[i])
    }

    for ((index, value) in collection.withIndex()) {
        println("the element at $index is $value")
    }
}

/**
 * Bucles con While
 */
fun whileLoops() {
    var x: Int = Random.nextInt(0,10)
    while (x > 0) {
        x--
    }

    do {
        val y = retrieveData()
    } while (y != null)
}
fun retrieveData() { }