package classAndObjects.classes
annotation class Inject

/** Clases */

fun main() {
    run {
        println("--- Miembros de clase ---")

        class Sample(private var p1: String = "default value") { // Primary constructor

            // Properties
            val p2: String = ""

            var p3: String = ""
                get() {
                    return field.capitalize()
                }
                set(value) {
                    field = value.trim()
                }

            // Secondary constructor
            constructor(p1: String, p2: String) : this(p1) {
                //...
            }

            constructor(p1: String, p2: String, p3: String) : this(p1) {
                //...
            }

            // Object Declaration
            val obj = object {
                var x: Int = 0
                var y: Int = 0
            }

            // Initializer blocks
            init {
                p1 = "text"
            }

            init {
                //...
            }

            // Functions
            fun function1(value: String) {
                p1 = value
            }

            fun function2() = p1.toUpperCase()


            // Nested class
            //class NestedClass {
                // fun funcion() = p1 // error
            //}

            // Inner class
            inner class InnerClass {
                fun function() = p1
            }
        }        
    }
    
    run {
        println("--- Constructores y bloques inicializadores ---")
        println("--- Constructor principal ---")

        class Sample(p1: String, val p2: String, private var p3: Int = 0) {
            init {
                println("Bloque de inicio se ejecuta después del constructor principal")
            }
        }

        class Sample1(p1: String) { }
        class Sample2 private constructor(p1: String) { }
        class Sample3 @Inject constructor(p1: String) { }
        class Sample4 @Inject public constructor(p1: String) { }

        class Sample5(val p1: String, var p2: String) { }

        class Sample6(val p1: String = "", val p2: String = "") { }
    }

    run {
        println("--- Constructor secundario ---")

        class Sample {
            var list: MutableList<String> = mutableListOf()

            constructor(brand: String) {
                list.add(brand)
                println("Constructor secundario")
            }

            constructor(brand: String, color: String) {
                list.add(brand + color)
                println("Constructor secundario")
            }

            init {
                println("Bloque de inicio se ejecuta antes que el cuerpo de constructores secundarios")
            }
        }

        class Sample1(val p1: String) {
            var list = mutableListOf<Sample1>()

            constructor(p1: String, parent: Sample1) : this(p1) {
                parent.list.add(this)
            }
        }
    }

    run {
        println("--- Orden de ejecución ---")

        class Sample(p1: Int, p2: String) {
            var p3: Double = 2.0

            val p1 = "Primera propiedad: $p1".also(::println)

            init {
                println("Bloque inicio 1, p1: $p1 p2: $p2 p3: $p3") // p3 vacío
            }

            constructor(p1: Int, p2: String, p3: Double) : this(p1, p2) {
                this.p3 = p3
                println("Constructor secundario, p1: $p1 p2: $p2 p3: ${this.p3}")
            }

            val p2 = "Segunda propiedad: ${p2.length}".also(::println)

            init {
                println("Bloque inicio 2, p1: $p1 p2: $p2 p3: $p3") // p3 vacío
            }
        }

        Sample(2, "two")
    }

    run {
        println("--- Creando instancias de clase ---")

        class Sample(p1: Int, p2: String, p3: Double) { }

        val p1 = Sample(1, "alberto", 3.0)
    }

    run {
        println("--- Clases abstractas ---")

        open class Base {
            open fun function1() {}
        }

        abstract class Sample : Base() {
            abstract val p1: String
            abstract val p2: Int
            abstract val p3: Boolean

            abstract override fun function1()
        }

        class Sample2(override val p1: String, override val p2: Int, override val p3: Boolean) : Sample() {
            override fun function1() { }
        }
    }

    run {
        println("--- companion objec ---")

        Sample.Companion01.p1.also { println(it) }

        Sample2.function1(150).also { println(it) }
        Sample2.Companion2.list.size.also { println(it) }

        Sample3.function01(22).also { println(it) }
    }
}


/** companion object */
class Sample {
    companion object Companion01 {
        val p1: String? = null
    }

    // Error! una clase solo puede contener un Companion object
    // companion object Companion02 {}
}

class Sample2(val p1: Int) {
    companion object Companion2 {
        val list = mutableListOf<Sample2>()

        fun function1(p1: Int): Sample2 {
            val p2 = Sample2(p1)
            list.add(p2)
            return p2
        }
    }
}

class Sample3 {
    companion object {
        fun function01(p1: Int) {}
    }
}





