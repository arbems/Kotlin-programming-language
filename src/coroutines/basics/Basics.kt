package coroutines.basics

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
    Corrutinas
 */

fun main() {
    //demo()
    //demo2()
    demo3()
    //demo01()
    //demo02()
    //demo03()
    //demo04()
    //demo05()
}

fun demo() {
    println("Start")

    runBlocking {
        println("Before delay.")
        delay(5000)
        println("After delay.")
    }

    println("End")
}

fun demo2() {
    println("Start")

    GlobalScope.launch {
        println("Before delay.")
        delay(5000)
        println("After delay.")
    }

    println("End")
    Thread.sleep(6000)
}

fun demo3() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}



/* Aquí estamos lanzando una nueva corrutina en el alcance GlobalScope, lo que significa que la vida útil de la nueva corrutina está limitada por la vida útil de toda la aplicación. */
/* Print: Hello, World! */
fun demo01() {
    GlobalScope.launch { // Lanza una nueva corrutina en segundo plano y continua
        delay(1000L) // Retraso sin bloqueo durante 1 segundo (la unidad de tiempo predeterminada es ms)
        println("World!") // imprimir después del retraso
    }
    print("Hello, ") // el hilo principal continúa mientras se retrasa la rutina
    Thread.sleep(2000L) // Retraso con bloqueo durante 2 segundo para mantener viva la JVM
}

/* Este ejemplo también se puede reescribir de una manera más idiomática, usando runBlocking para ajustar la ejecución de la función principal: */
fun demo02() = runBlocking<Unit> {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L) // delaying for 2 seconds to keep JVM alive
} // Esta es también una forma de escribir pruebas unitarias para suspender funciones

/* Ahora el resultado sigue siendo el mismo, pero el código de la corrutina principal no está vinculado a la duración del trabajo en segundo plano de ninguna manera. Mucho mejor. */
fun demo03() = runBlocking {
    val job = GlobalScope.launch { // lanza una nueva corrutina y mantenie una referencia a su trabajo
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // espera hasta que la corrutina hijo se complete
}

/*
    En lugar de lanzar corutinas en GlobalScope , tal como lo hacemos habitualmente con subprocesos (los subprocesos siempre son globales),
    podemos lanzar corrutinas en el ámbito específico de la operación que estamos realizando.

    Cada creador de corrutinas, incluido runBlocking, agrega una instancia de CoroutineScope al alcance de su bloque de código.

    Podemos lanzar corrutinas en este ámbito sin tener que hacerlo join explícitamente, porque una corrutina externa (runBlocking en nuestro ejemplo) no se completa hasta que se completen todas las corrutinas lanzadas en su ámbito.
    Por lo tanto, podemos simplificar nuestro ejemplo:
 */
fun demo04() = runBlocking { // this: CoroutineScope
    launch { // lanza una nueva corrutina en el alcance de runBlocking
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

/*
    runBlocking y coroutineScope pueden verse similares porque ambos esperan a que su cuerpo y todos sus hijos se completen.
    La principal diferencia es que el método runBlocking bloquea el hilo actual para esperar, mientras que coroutineScope simplemente suspende,
    liberando el hilo subyacente para otros usos. Debido a esa diferencia, runBlocking es una función regular y coroutineScope es una función de suspensión.
 */
fun demo05() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope { // Creates a coroutine scope
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over") // This line is not printed until the nested launch completes
    }
