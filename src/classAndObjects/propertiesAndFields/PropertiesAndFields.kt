package classAndObjects.propertiesAndFields

/**
 * Propiedades y campos
 */

fun main() {
    run {
        println("--- Declarando propiedades ---")
        class Sample {
            val name: String = "Robert" // tipo explícito, getter por defecto
            val sexo = "H" // el tipo de propiedad es opcional si se puede inferir del inicializador
            var state: String? = null
            //var country: String // la propiedad debe ser inicializada o ser abstracta
        }
        val sample = Sample()
        //sample.name = "Tom" // val no se puede reasignar
        sample.state = "state"
    }

    run {
        println("--- Getters y Setters ---")
        class Sample {
            //var p1: Int? // error: inicializador requerido
            var p2 = 1 // tipo Int inferido, getter y setter por defecto

            //val p3: Int? // error: inicializador requerido
            val p4 = 1 // tipo Int inferido, getter por defecto

            val p5: Boolean
                get() = this.p6 == 0

            val p6 get() = 0

            var p7: String = ""
                get() = field.toUpperCase()
                set(value) {
                    field = value.toLowerCase()
                }

            val size = 0
            val p1 get() = this.size == 0  // Boolean
        }
    }

    run {
        println("--- Backing field y Backing property ---")
        class Sample {
            var name: String = "alberto"
                get() = field.capitalize()
                set(value) {
                    if (value.isNotBlank())
                        field = value
                }

        private var _table: Map<String, Int>? = null
        public val table: Map<String, Int>
            get() {
                if (_table == null) {
                    _table = HashMap() // Type parameters are inferred
                }
                return _table ?: throw AssertionError("Set to null by another thread")
            }
        }
    }

    run {

    }
}

annotation class Inject

/**  Compile-Time Constants */
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

/**
 * Propiedades y variables de inicialización tardía (Late-initialized)
 */
// En el siguiente código básicamente la inicialización se da posteriormente. Habitualmente cuando una variable no es de un tipo que acepte null requiere ser inicializada en el constructor;
// sin embargo, no siempre aplica esto como, por ejemplo, en situaciones donde la inicialización de las variables se da a través de la inyección de dependencias o en la función setup dentro de nuestras pruebas unitarias.
// Para manejar este tipo de situaciones, sobretodo para evitar las verificaciones de valores nulls podemos marcar la propiedad con el modificador lateinit
var adapter1: MyAdapter // error: property must be initialized
lateinit var adapter: MyAdapter // adapter.isInitialized

class MyAdapter() {}
// Es bueno recalcar que la aplicación de lazy aplica para propiedades de tipo val (inmutables) y lateinit para propiedades de tipo var (mutables)
// siempre que no tenga un setter o getter personalizado ni que sea un tipo primitivo.

/**
 * Initialization by Lazy
 */
// La inicialización de la variable adapter solo se realizará durante la primera vez en que se haga uso de la variable adapter.
// Por definición lazy es una función que durante la primera invocación ejecuta el lambda que se le haya pasado y en posteriores invocaciones retornará el valor computado inicialmente.
val adapter2: MyAdapter by lazy { initializeAdapter() }
fun initializeAdapter(): MyAdapter {
    return MyAdapter()
}

