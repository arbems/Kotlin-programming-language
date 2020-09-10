# Programando con lenguaje Kotlin - Coroutine Context y Dispatchers

## Dispatchers y threads

Las corrutinas siempre se ejecutan en algún *contexto* representado por un valor del tipo [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/), definido en la biblioteca estándar de Kotlin.

Todos los constructores de corrutinas, como *launch* y *async*, aceptan un parámetro opcional de *CoroutineContext* que se puede utilizar para especificar explícitamente el [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) para la nueva corrutina y otros elementos de contexto como el [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) de la corrutina.

El **Dispatcher** de corrutina determina qué hilo o hilos utiliza la correspondiente corrutina para su ejecución.
Puede limitar la ejecución de corrutinas a un hilo específico, enviarlo a un grupo de hilos o dejar que se ejecute *unconfined*.

<img src="https://raw.githubusercontent.com/arbems/Kotlin-Programming-Language/master/src/coroutines/coroutineContextAndDispatchers/0001.png" witdh="600"/>

Object [Dispatchers](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html) agrupa varias implementaciones de *CoroutineDispatcher*:

* **Dispatchers.Default**: *CoroutineDispatcher* por defecto que utilizan todos los constructores estándar como launch, async, etc. si no se especifica un dispatcher ni ningún otro *ContinuationInterceptor* en su contexto. Utiliza un grupo común de subprocesos en segundo plano compartidos. Ésta es una opción adecuada para corrutinas informáticas intensivas que consumen recursos de la CPU, como cálculos, algoritmos, etc.

* **Dispatchers.IO**: *CoroutineDispatcher* que está diseñado para descargar tareas de E/S de bloqueo a un grupo compartido de subprocesos. En general, todas las tareas que bloquearán el hilo mientras esperan la respuesta de otro sistema: peticiones al servidor, acceso a la base de datos, sitema de archivos, sensores etc.

* **Dispatchers.Main**: *CoroutineDispatcher* que se limita al subproceso principal que opera con objetos de IU. Por lo general, estos *Dispatchers* son de un solo subproceso.

* **Dispatchers.Unconfined**: *CoroutineDispatcher* que inicia una corrutina en el hilo del llamador, pero solo hasta el primer punto de suspensión. Después de la suspensión, reanuda la corrutina en el hilo que está totalmente determinada por la función de suspensión que se invocó. Es apropiado para corrutinas que no consumen tiempo de CPU ni actualizan ningún dato compartido (como la interfaz de usuario) confinado a un hilo específico. *Dispatchers.Unconfined* no debe usarse en código general.

Elegir el **Dispatcher** incorrecto puede reducir o anular la efectividad de la corrutina, a tener en cuenta para elegir *Dispatcher*:

* Si el código interactúa con los elementos de la interfaz de usuario, *Dispatchers.Main* es apropiado.
* Si el código es intensivo en CPU. Es decir, el código realiza cálculos (CPU), *Dispatchers.Default* es apropiado ya que está respaldado por un grupo de subprocesos con tantos subprocesos como núcleos de CPU.
* El código es intensivo en IO. Es decir, el código se comunica a través de la red / archivo (IO). *Dispatchers.IO* es apropiado.

[CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/) es un conjunto indexado de instancias de [Element](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-element/). Un conjunto indexado es una mezcla entre un set y un map. Cada *Element* de este conjunto tiene una [Key](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-key.html).

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/0001.png" width="600" /><br>

**Keys** que nos sirven para obtener los cuatro *Element* de nuestro **CoroutineContext**:

* **Job**: Obtenemos el *Job* de la corrutina a la que se asocia el contexto.
* **ContinuationInterceptor**: Obtenemos el *CoroutineDispatcher* de la corrutina a la que se asocia el contexto.
* **CoroutineExceptionHandler**: Obtenemos el *manejador de excepciones* de la corrutina a la que se asocia el contexto.
* **CoroutineName**: Obtenemos el *nombre de la corrutina* a la que se asocia el contexto. Establecer un nombre es útil para efectos de depuración.

Podemos combinar elementos de un contexto con los elementos de otro contexto gracias al operador `plus`, devolviendo un nuevo contexto que contiene los elementos combinados.

## Unconfined vs confined dispatcher

???

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


