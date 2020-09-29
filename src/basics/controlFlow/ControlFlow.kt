package basics.controlFlow

import java.lang.Integer.parseInt
import kotlin.random.Random

/** Control de flujos */

fun main() {
    run {
        println("--- Expresiones con If ---")

        val a = Random.nextInt(0, 15)
        val b = 9

        // Uso tradicional
        var max = a
        if (a < b) max = b

        // con else
        if (a > b) {
            max = a
        } else {
            max = b
        }

        // como expresión
        max = if (a > b) a else b

        // blocks
        max = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
    }

    run {
        println("--- Expresiones con When ---")

        val a = Random.nextInt(1, 10)
        when (a) {
            1 -> println("a == 1")
            2 -> println("a == 2")
            else -> { // block
                println("a is neither 1 nor 2")
            }
        }

        // declaration
        when (a) {
            0 -> println("a == 0")
            1 -> println("a == 1")
            else -> print("otherwise")
        }

        when (a) {
            0, 1 -> print("x == 0 or x == 1")
            else -> print("otherwise")
        }

        // expression
        val b = 2
        val sum = when (val operator = "+") {
            "+" -> a + b
            "-" -> a - b
            else -> "$operator operator is invalid operator."
        }

        val result = when (val operator = "-") {
            "+" -> {
                println("sum")
                a + b
            }
            "-" -> {
                println("rest")
                a - b
            }
            else -> "$operator operator is invalid operator."
        }

        val s = "1"
        when (a) {
            parseInt(s) -> println("s encodes a")
            else -> println("s does not encode a")
        }

        val validNumbers = arrayOf(1, 2, 3, 4)
        when (a) {
            in 1..5 -> println("a is in the range")
            in validNumbers -> println("a is valid")
            !in 10..20 -> println("a is outside the range")
            else -> println("none of the above")
        }

        hasPrefix(1)
        hasPrefix("1")
    }

    run {
        println("--- Bucles con for ---")

        val collection = arrayOf(1, 2, 3)

        for (item in collection) println(item)

        for (item: Int in collection) println(item)

        for (i in 1..3) {
            println(i)
        }

        for (i in 6 downTo 0 step 2) println("downTo: $i")

        for (i in collection.indices) println(collection[i])

        for ((index, value) in collection.withIndex()) println("the element at $index is $value")
    }

    run {
        println("--- Bucles con While ---")

        var x: Int = Random.nextInt(0, 10)
        while (x > 0) {
            x--
        }

        do {
            val y = retrieveData()
        } while (y != null)
    }
}

/** Expresiones con When */
fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}

/** Bucles con While */
fun retrieveData() {}