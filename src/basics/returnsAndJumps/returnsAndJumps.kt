package basics.returnsAndJumps

import kotlin.random.Random

/** Returns y saltos */

fun main() {
    run {
        println("--- break ---")

        // termina el bucle de cierre más cercano
        for (i in 1..100) {
            for (j in 1..100) {
                if (10 <= Random.nextInt(0, 15)) break
            }
        }
        // o el de la etiqueta marcada
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (10 <= Random.nextInt(0, 15)) break@loop
            }
        }
    }

    run {
        println("--- continue ---")

        // continúa con el siguiente paso del bucle de cierre más cercano.
        for (i in 1..100) {
            for (j in 1..100) {
                if (10 <= Random.nextInt(0, 15)) continue
            }
        }
        // o el de la etiqueta marcada
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (10 <= Random.nextInt(0, 15)) continue@loop
            }
        }
    }

    run {
        println("--- return ---")

        sample1()
        sample2()
        sample3()
        sample4()
        sample5()
    }
}


/** return */
fun sample1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // return de la función sample1()
        println(it) // print 1 2
    }
    println("este punto es inalcanzable")
}
fun sample2() {
    listOf(1, 2, 3, 4, 5).forEach Lit@{
        if (it == 3) return@Lit // return de la expresión lambda
        println(it) // print 1 2 4 5
    }
    println("hecho con etiqueta explícita")
}
fun sample3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // return de la expresión lambda
        println(it) // print 1 2 4 5
    }
    println("hecho con etiqueta implícita")
}
fun sample4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // return de la función anónima
        println(value) // print 1 2 4 5
    })
    println("hecho con funcion anonima")
}
fun sample5() {
    run loop@ {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // return no local de la lambda pasada para ejecutar
            println(it) // print 1 2
        }
    }
    println("hecho con bucle anidado")
}