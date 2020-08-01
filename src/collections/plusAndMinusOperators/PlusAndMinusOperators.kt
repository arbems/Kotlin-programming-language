package collections.plusAndMinusOperators

/**
    plus and minus Operators
 */

fun main() {
    demo01()
}

/*
    En Kotlin, los operadores plus(+) y minus(-) se definen para colecciones.
 */
fun demo01() {
    val numbers = listOf("one", "two", "three", "four")

    /*
        El resultado de plus contiene los elementos de la colección original y del segundo operando.
     */
    val plusList = numbers + "five" // primer operando una colección, segundo operando puede ser un elemento u otra colección y el valor de retorno es una nueva colección de solo lectura

    /*
        El resultado de minus contiene los elementos de la colección original, excepto los elementos del segundo operando.
        Si es un elemento, minus elimina su primera aparición; Si se trata de una colección, se eliminan todas las apariciones de sus elementos.
     */
    val minusList = numbers - listOf("three", "four")

    println(plusList) // [one, two, three, four, five]
    println(minusList) // [one, two]
}