# Programando con lenguaje Kotlin - Coroutine Context y Dispatchers

## Dispatchers y threads

Las corrutinas siempre se ejecutan en algún *contexto* representado por un valor del tipo [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/), definido en la biblioteca estándar de Kotlin.

Todos los constructores de corrutinas, como *launch* y *async*, aceptan un parámetro opcional de *CoroutineContext* que se puede utilizar para especificar explícitamente el [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) para la nueva corrutina y otros elementos de contexto como el [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) de la corrutina.

El **Dispatcher** de corrutina determina qué hilo o hilos utiliza la correspondiente corrutina para su ejecución.
Puede limitar la ejecución de corrutinas a un subproceso específico, enviarlo a un grupo de subprocesos o dejar que se ejecute *unconfined*.

Object [Dispatchers](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html) agrupa varias implementaciones de *CoroutineDispatcher*:

* **Dispatchers.Default**: El CoroutineDispatcher predeterminado que utilizan todos los constructores estándar como launch , async , etc. si no se especifica un dispatcher ni ningún otro ContinuationInterceptor en su contexto. Utiliza un grupo común de subprocesos en segundo plano compartidos. Ésta es una opción adecuada para corrutinas informáticas intensivas que consumen recursos de la CPU.

* **Dispatchers.IO**: El CoroutineDispatcher que está diseñado para la descarga de bloqueo tareas IO a un conjunto compartido de hilos. Está diseñado para descargar operaciones de bloqueo intensivas en E/S (como E/S de archivos y E/S de socket de bloqueo).

* **Dispatchers.Main**: Un *Dispatcher* de rutina que se limita al subproceso principal que opera con objetos de IU. Por lo general, estos *Dispatchers* son de un solo subproceso.

* **Dispatchers.Unconfined**: 


[CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/) es un conjunto indexado de instancias de [Element](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-element/). Un conjunto indexado es una mezcla entre un set y un map. Cada *Element* de este conjunto tiene una [Key](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-key.html).

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/0001.png" width="600" /><br>

**Keys** que nos sirven para obtener los cuatro *Element* de nuestro **CoroutineContext**:

* **Job**: Obtenemos el *Job* de la corrutina a la que se asocia el contexto.
* **ContinuationInterceptor**: Obtenemos el *CoroutineDispatcher* de la corrutina a la que se asocia el contexto.
* **CoroutineExceptionHandler**: Obtenemos el *manejador de excepciones* de la corrutina a la que se asocia el contexto.
* **CoroutineName**: Obtenemos el *nombre de la corrutina* a la que se asocia el contexto. Establecer un nombre es útil para efectos de depuración.

Podemos combinar elementos de un contexto con los elementos de otro contexto gracias al operador `plus`, devolviendo un nuevo contexto que contiene los elementos combinados.

## Unconfined vs confined dispatcher
## Depuración coroutines y threads
## Depuración con IDEA
## Depuración usando logging
## Saltando entre threads
## Job en el context
## hijos de una coroutine
## Responsabilidades de los padres
## Nombrer coroutines para depurar
## Combinando elementos de context
## Coroutine scope

[CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)

## Datos locales de subprocesos


