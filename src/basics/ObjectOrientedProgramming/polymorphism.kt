package basics.ObjectOrientedProgramming

/** polymorphism */

fun main() {
    run {
        println("--- compile time polymorphism ---")

        fun printNumber(n: Number) = println("$n printNumber(n: Number)")

        fun printNumber(n: Int) = println("$n printNumber(n: Int)")

        fun printNumber(n: Double) = println("$n printNumber(n: Double)")

        printNumber(99 as Number)
        printNumber(1)
        printNumber(3.1)
    }

    run {
        println("--- runtime polymorphism ---")
        val a: Number = 99
        val b: Int = 1
        val c: Double = 3.1

        fun sum(numbers: List<Number>): Number = numbers.sumByDouble { it.toDouble() }

        println(sum(listOf(a, b, c)))
    }
}

