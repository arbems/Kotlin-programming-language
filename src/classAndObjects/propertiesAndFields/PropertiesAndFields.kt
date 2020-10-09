package classAndObjects.propertiesAndFields

annotation class Inject

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
        println("--- lateinit ---")
        class Sample {
            //var str: String // error: property must be initialized
            lateinit var p1: String

            init {
                //initializeProperty()

                if (::p1.isInitialized) {
                    println("$p1 is initialized")
                }
                else {
                    println("is not initialized")
                }
            }

            fun initializeProperty() {
                p1 = "hello,"
            }
        }
        val sample = Sample()
    }
}

/**  Compile-Time Constants */
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

