package classAndObjects.inheritance

/** Herencia */

fun main() {

    run {
        println("--- Superclass Any ---")

        class Derived : Any() {
            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun hashCode(): Int {
                return super.hashCode()
            }

            override fun toString(): String {
                return super.toString()
            }
        }

        Derived()
    }

    run {
        println("--- Heredar de una clase ---")

        open class Base(p1: Int) {
            public constructor(p1: Int, p2: String) : this(p1) { }
        }

        class Derived : Base {
            constructor(p1: Int, p2: String) : super(p1) { }
        }

        class Derived2(p1: Int) : Base(p1) {
            constructor(p1: Int, p2: String) : this(p1) { }
        }

        Derived(5, "five")
        Derived2(9, "nine")
    }

    run {
        println("--- Sobrescribiendo métodos ---")

        open class Base {
            open fun function1() { }

            fun function2() { }
        }

        class Derived : Base() {
            override fun function1() { }
            // override fun function2() { }  Error! es final y no puede ser sobrescrito
        }

        open class Derived2 : Base() {
            final override fun function1() { }
        }

        class Sample : Derived2() {
            // override fun function1() {}  Error! es final y no puede ser sobrescrito
        }
    }

    run {
        println("--- Sobrescribiendo propiedades ---")

        open class Base {
            open val vertexCount: Int = 0
        }

        class Derived : Base() {
            override var vertexCount = 4.also { println(it) }
        }

        class Derived2(override var vertexCount: Int) : Base() {
            init {
                println(vertexCount)
            }
        }

        Derived()
        Derived2(8)
    }

    run {
        println("--- Orden de inicialización de clases derivadas ---")

        open class Base(val name: String) {

            init {
                println("Inicializando base")
            }

            open val size: Int = name.length.also { println("Inicializando tamaño en base: $it") }
        }

        class Derived(name: String) : Base(name.capitalize().also { println("Argumento para base: $it") }) {

            init {
                println("Inicializando derivada")
            }

            override val size: Int = (super.size + 2).also { println("Inicializando tamaño en derivada: $it") }
        }

        Derived("alberto")
    }

    run {
        println("--- Llamando a la implementación de la super clase ---")

        open class Base {
            open fun function1() { println("Base.function1") }
            val p1: String get() = "p1"
        }

        class Derived : Base() {
            override fun function1() {
                super.function1()
                println("Derived.function1")
            }

            val p2: String get() = super.p1

            inner class Derived2 {
                fun function2() { }
                fun function3() {
                    super@Derived.function1()
                    function2()
                    println("Derived2.function3 ${super@Derived.p1}")
                }
            }
        }

        Derived().function1()
        Derived().Derived2().function3()
    }

    run {
        println("--- Sobrescribiendo reglas ---")

        open class Base {
            open fun function1() { }
        }

        class Derived : Base(), Interface1 {
            override fun function1() {
                super<Base>.function1()
                super<Interface1>.function1()
            }
        }

        Derived()
    }
}

interface Interface1 {
    fun function1() { }
}