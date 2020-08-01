package basics.controlFlow
/**
 * Control flow / Control de flujos
 * */

import java.lang.Integer.parseInt
import kotlin.random.Random

fun main(args: Array<String>) {
    ifExpressions()
    whenExpressions()
    loops()
    whileLoops()
}

fun ifExpressions() {
    /** If Expression */

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

fun whenExpressions() {
    /** When Expression */

    val x = Random.nextInt(1,10)
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> { // Note the block
            println("x is neither 1 nor 2")
        }
    }

    when (x) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    val s = "1"
    when (x) {
        parseInt(s) -> println("s encodes x")
        else -> println("s does not encode x")
    }

    val validNumbers = arrayOf(1,2,3,4)
    when (x) {
        in 1..5 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }

    hasPrefix(1)
    hasPrefix("1")
}
fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}

fun loops() {
    /** For Loops */

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

fun whileLoops() {
    /** While Loops */

    var x: Int = Random.nextInt(0,10)
    while (x > 0) {
        x--
    }

    do {
        val y = retrieveData()
    } while (y != null)
}
fun retrieveData() { }