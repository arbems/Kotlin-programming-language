package classAndObjects.generics.examples

fun main() {
    val a = ClassA<String>("string-value")
    println(a.getValue())

    val b = ClassA("string-value") // Kotlin puede inferir el tipo genérico del tipo de parámetro
    println(b.getValue())
}

class ClassA<T>(private val value: T) {
    fun getValue(): T = value
}