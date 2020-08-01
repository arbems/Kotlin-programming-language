package basics.returnsAndJumps
/**
 * Retunrs and jumps / Devoluciones y saltos
 * */

import kotlin.random.Random

fun returnsAndJumps() {
    /*
    * return. Por defecto regresa de la función de cierre más cercana o de la función anónima.
    * break. Termina el bucle de cierre más cercano.
    * continue. Continúa con el siguiente paso del bucle de cierre más cercano.
    * */

    /** break */
    // break termina el bucle de cierre más cercano o el de la etiqueta marcada
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (10 <= Random.nextInt(0, 15)) break@loop
        }
    }

    /** continue */
    // continue continúa con el siguiente paso del bucle de cierre más cercano.
    for (i in 1..100) {
        for (j in 1..100) {
            if (10 <= Random.nextInt(0, 15)) continue
        }
    }

    /** return */
    foo()
    foo2()
    foo3()
    foo4()
    foo5()
}
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // non-local return directly to the caller of basics.ControlFlow.foo()
        println(it)
    }
    println("este punto es inalcanzable")
}
fun foo2() {
    listOf(1, 2, 3, 4, 5).forEach Lit@{
        if (it == 3) return@Lit // local return to the caller of the lambda, i.e. the forEach loop
        println(it)
    }
    println("hecho con etiqueta explícita")
}
fun foo3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // local return to the caller of the lambda, i.e. the forEach loop
        println(it)
    }
    println("hecho con etiqueta implícita")
}
fun foo4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // local return to the caller of the anonymous fun, i.e. the forEach loop
        print(value)
    })
    println("hecho con funcion anonima")
}
fun foo5() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            print(it)
        }
    }
    println("hecho con bucle anidado")
}