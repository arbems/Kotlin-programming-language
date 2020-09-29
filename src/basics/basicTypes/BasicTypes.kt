package basics.basicTypes

import kotlin.random.Random

/** Tipos básicos */

fun main(args: Array<String>) {
    run {
        println("--- Numbers ---")

        val p1: Byte = 1 // Byte
        val p2: Short = 1 // Short
        val p3 = 2147483647 // Int
        val p4 = 2147483648 // Long
        val p5 = 2L // Long

        val p6 = 3.14 // Double
        val p7 = 2.7182818284 // Double
        val p8 = 2.7182818284f // Float, actual value is 2.7182817

        // Guiones bajos en literales numéricos
        val p9 = 1_000_000
        val p10 = 1234_5678_9012_3456L
        val p11 = 999_99_9999L
        val p12 = 0xFF_EC_DE_5E
        val p13 = 0b11010010_01101001_10010100_10010010

        // Representación
        val a: Int = 1000
        println(a === a) // 'true'
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA === anotherBoxedA) // !!!'false'!!! no mantiene la identidad
        println(boxedA == anotherBoxedA) // 'true' si mantiene la igualdad

        // Conversiones explícitas
        val c: Int? = 1 // ClassAndObjects.A boxed Int (java.lang.Integer)
        // val b: Long? = c // error: conversión implícita (java.lang.Long)
        // print(b == c) // Sorpresa! Esto imprime "false", Long's equals() comprueba si el otro también es Long

        val b: Byte = 1 // OK, los literales se verifican estáticamente
        // val i: Int = b // Error!
        val o: Int = b.toInt() // OK: explícita

        val l = 1L + 3 // Long + Int => Long

        // Operaciones
        // División entre enteros siempre devuelve un entero
        val x = 5 / 2
        //println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
        println(x == 2) // true

        val p = 5L / 2
        println(p == 2L) // true

        val y = 5 / 2.toDouble()
        println(y == 2.5) // true

        // Operaciones bit a bit
        val u = (1 shl 2) and 0x000FF000

        // Comparación de números
        // Comprobaciones de igualdad: a == b y a != b
        val d: Int = Random.nextInt()
        if (a == d)
            println(true)
        // Los operadores de comparación: a < b, a > b, a <= b,a >= b
        if (a >= d)
            println(true)
        // Rango de instancias y alcance cheques: a..b, x in a..b, x !in a..b
        val q = 1
        val v = 5
        val r = 5
        if (r !in q..v)
            throw IllegalArgumentException("Out of range")
    }

    run {
        println("--- Characters ---")
        // Los caracteres están representados por el tipo Char
        val c: Char = '1'
        // if (c == 1) { } error
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        val r = c.toInt() - '0'.toInt() // Explicit conversions to basics.types.numbers
    }

    run {
        println("--- Booleans ---")
        // Las operaciones incorporadas en booleanos || && !=
        val a = Random.nextInt()
        val b = Random.nextInt()
        val c = Random.nextInt()
        if (a >= 1 || (b <= 3 && c != 2) ) { }
    }

    run {
        println("--- Arrays ---")
        // arrayOf
        val a: Array<Int> = arrayOf(1, 2, 3);
        val k: Array<Int?> = arrayOfNulls<Int>(3)
        println(a.size)
        val value = a[0] // a.get(0)
        a[0] = 10 // a.set(0, 10)
        a.forEach { println(it) }

        // Otra opción es usar el Array constructor que toma el tamaño de la matriz
        // y la función que puede devolver el valor inicial de cada elemento de la matriz dado su índice
        val b = Array(5) { i -> (i * i).toString() }
        b.forEach { println(it) }

        // Los Arrays en Kotlin son invariables
        var c = Array<Any>(5) { i -> (i * i).toString() }
        var d = Array<String>(3) { i -> (i * i).toString() }
        // c = d // error: tipo no coinciden

        // IntArray, ByteArray, ShortArray..
        val x: IntArray = intArrayOf(1, 2, 3)

        // Array de int de tamaño 5 con valores [0, 0, 0, 0, 0]
        val e = IntArray(5)

        // Inicializa los valores del array con una constante
        val f = IntArray(5) { 42 } // Array de int de tamaño 5 con valores [42, 42, 42, 42, 42]

        // Inicializa los valores en el array usando lambda
        var g = IntArray(5) { it * 1 } // Array de int de tamaño 5 con valores [0, 1, 2, 3, 4] (valores inicializados a su valor de índice)

        // Recorriendo Arrays
        for (index in a.indices) {
            println("La posición $index")
        }
        for ((index, value) in a.withIndex()) {
            println("La posición $index contiene el valor $value")
        }
        for (value in a) {
            println("Contiene el valor $value")
        }
    }

    run {
        println("--- Strings ---")
        val str: String = "hello"
        for (char in str) {
            println(char)
        }

        val s = "abc " + 1 + " def "
        println(s + "ghi")

        // String literals
        val s1 = "Hello, world!\n"

        // a raw string
        val text = """
                for (c in "basics.ControlFlow.foo")
                    print(c)
                """
        println(text)

        // Elimina espacios en blanco iniciales
        // Por defecto | se usa como prefijo de margen, pero puede elegir otro carácter y pasarlo como parámetro, como trimMargin (">").
        val text1 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
        println(text1)

        // string templates
        val i = 10
        println("i = $i") // prints "i = 10"
        val s2 = "abc"
        println("$s2.length is ${s2.length}") // prints "abc.length is 3"
        val price = """
        ${'$'}9.99
        """
        println(price)
    }
}