package basics.returnsAndJumps

import kotlin.random.Random

/**
 * Returns y saltos
 * */

/**
 * return. Por defecto regresa de la función de cierre más cercana o de la función anónima.
 * break. Termina el bucle de cierre más cercano.
 * continue. Continúa con el siguiente paso del bucle de cierre más cercano.
 */

fun main() {
    breakReturns()
    continueNext()
    returns()
}

/**
 * break
 */
fun breakReturns() {
    // break termina el bucle de cierre más cercano
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

/**
 * continue
 */
fun continueNext() {
    // continue continúa con el siguiente paso del bucle de cierre más cercano.
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

/**
 * returns
 */
fun returns() {
    demo1()
    demo2()
    demo3()
    demo4()
    demo5()
}
fun demo1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // retorno no local directamente al llamador de la función
        println(it) // print 1 2
    }
    println("este punto es inalcanzable")
}
fun demo2() {
    listOf(1, 2, 3, 4, 5).forEach Lit@{
        if (it == 3) return@Lit // retorno local al llamador de la lambda
        println(it) // print 1 2 4 5
    }
    println("hecho con etiqueta explícita")
}
fun demo3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // retorno local al llamador de la lambda
        println(it) // print 1 2 4 5
    }
    println("hecho con etiqueta implícita")
}
fun demo4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // retorno local al llamador de anonymous fun
        println(value) // print 1 2 4 5
    })
    println("hecho con funcion anonima")
}
fun demo5() {
    run loop@ {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // retorno no local de la lambda pasada para ejecutar
            println(it) // print 1 2
        }
    }
    println("hecho con bucle anidado")
}