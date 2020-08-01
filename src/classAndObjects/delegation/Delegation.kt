package classAndObjects.delegation

/**
 * Delegation
 * */

fun main() {
    // Implementation by Delegation
    val b = BaseImpl(10)
    Derived(b).print()
    Derived(b).printMessageLine()
}

/** Implementation by Delegation */
// El patrón de delegación ha demostrado ser una buena alternativa a la herencia de implementación, y Kotlin lo admite de forma nativa y no requiere un código repetitivo.
// La cláusula by en la lista de supertipos para Derived indica que b se almacenará internamente en objetos de Derived y el compilador generará todos los métodos de Base que reenvíen a b.
interface Base {
    fun print()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
    override fun printMessageLine() { println(x) }
}

class Derived(b: Base) : Base by b {
    override fun printMessageLine() { print("abc") } // Sobre-escribir un miembro de una interfaz implementada por delegación
}