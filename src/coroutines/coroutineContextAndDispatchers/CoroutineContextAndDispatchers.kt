package coroutines.coroutineContextAndDispatchers

import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

/**
 * Coroutine Context & Dispatchers
 */

fun main() {
    demo02()
}

/**
 * Dispatchers & threads
 */
fun demo01() = runBlocking {
    /* launch sin parámetros, hereda el contexto de CoroutineScope */
    launch { // context del padre, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}") // main
    }

    launch(Dispatchers.Unconfined) { // not confined -- funcionara con el hilo principal
        println("Unconfined: I'm working in thread ${Thread.currentThread().name}") // main
    }

    launch(Dispatchers.Default) { // se enviará a DefaultDispatcher
        println("Default: I'm working in thread ${Thread.currentThread().name}") // DefaultDispatcher-worker-1
    }

    /* Crea un hilo para que se ejecute la corrutina. Un hilo dedicado es un recurso muy caro.
       En una aplicación real, debe liberarse, cuando ya no se necesite, mediante la función de cierre, o almacenarse en una variable de nivel superior y reutilizarse en toda la aplicación. */
    launch(newSingleThreadContext("MyOwnThread")) { // obtendrá su propio hilo nuevo
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}") // MyOwnThread
    }
}

/**
 * Unconfined vs confined dispatcher
 */
fun demo02() = runBlocking {
    launch(Dispatchers.Unconfined) { // not confined -- funcionara con el hilo principal
        println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined: After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context del padre, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

/**
 * Saltando entre hilos
 */
fun demo03() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                println("Started in ctx1")
                withContext(ctx2) {
                    println("Working in ctx2")
                }
                println("Back to ctx1")
            }
        }
    }
}

/**
 * Job en contexto
 */
fun demo04() = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")
    println("Is Job active: ${coroutineContext[Job]?.isActive}")
}

/**
 * Hijos de una corrutina
 */
/*
 * Cuando se lanza una corrutina en el CoroutineScope de otra corrutina, hereda su contexto a través de CoroutineScope.coroutineContext
 * y el trabajo de la nueva corrutina se convierte en un elemento secundario del trabajo de la corrutina principal.
 * Cuando se cancela la corrutina principal, todos sus elementos secundarios también se cancelan de forma recursiva.
 *
 * Cuando se utiliza GlobalScope para iniciar una corrutina, no hay un padre para el trabajo de la nueva corrutina.
 * Por lo tanto, no está vinculado al alcance desde el que se lanzó y funciona de forma independiente.
 */
fun demo05() = runBlocking<Unit> {
    // lanzar una corrutina para procesar algún tipo de solicitud entrante
    val request = launch {
        // GlobalScope, no hereda contexto.
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // hereda el contexto padre
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancela el procesamiento de la solicitud
    delay(1000) // espera un segundo para ver que pasa
    println("main: Who has survived request cancellation?")
}

/**
 * Responsabilidades de los padres
 */
/*
 * Una corrutina padre siempre espera la finalización de todos sus hijos.
 * Un padre no tiene que rastrear explícitamente a todos los hijos que lanza, y no tiene que usar Job.join para esperarlos al final.
 */
fun demo06() = runBlocking<Unit> {
    // lanzar una corrutina para procesar algún tipo de solicitud entrante
    val request = launch {
        repeat(3) { i ->
            launch  {
                delay((i + 1) * 2000L) // variable delay 2000ms, 4000ms, 6000ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join() // espera a que se complete la solicitud, incluidos todos sus elementos secundarios
    println("Now processing of the request is complete")
}

/**
 * Nombrar corutinas para depurar
 */
/*
 * Cuando una corrutina está vinculada al procesamiento de una solicitud específica o al realizar alguna tarea en segundo plano específica,
 * es mejor nombrarla explícitamente para fines de depuración.
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun demo07() = runBlocking(CoroutineName("main")) {
    log("Started main coroutine")
    // run two background value computations
    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log("Computing v1")
        252
    }
    val v2 = async(CoroutineName("v2coroutine")) {
        delay(1000)
        log("Computing v2")
        6
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}

/**
 * Combinando elementos de contexto
 */
fun demo08() = runBlocking<Unit> {
    val job = Job()
    val handleException = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    launch(Dispatchers.Default + job + handleException + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}

/**
 * Coroutine scope
 */



/**
 * Datos locales de hilos
 */