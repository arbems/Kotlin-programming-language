package classAndObjects.generics.genericConstraint

import kotlin.Comparable

/**
 * Generic constraint
 * */

fun main() {
    demo05()
    demo06()
}



/*
* El conjunto de todos los tipos posibles que se pueden sustituir por un parámetro de tipo dado puede estar restringido por restricciones genéricas.
*/
/** Upper bounds */
/* El tipo especificado después de dos puntos es el límite superior, solo un subtipo de <T> comparable puede ser sustituido por T.
*
* Por defecto, upper bound es Any?
* */
fun <T : Comparable<T>> sort(list: List<T>) { /* ... */ }

fun demo05() {
    val list = listOf(1, 2, 3)
    sort(list) // OK. Int es un subtipo de Comparable<Int>
    // sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> no es un subtipo de Comparable<HashMap<Int, String>>
}



/** Where */
/*
* Solo se puede especificar un límite superior dentro de los corchetes angulares (<...>).
* Si el mismo parámetro de tipo necesita más de un límite superior, necesitamos una cláusula where separada:
* */
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
fun demo06() {
    var list  = listOf("1", "2", "3")
    list = copyWhenGreater(list, "1")
    list.forEach { println(it) }
}
/*
* El tipo pasado debe satisfacer todas las condiciones de la cláusula where simultáneamente. En el ejemplo anterior, el tipo T debe implementar tanto CharSequence como Comparable.
* */