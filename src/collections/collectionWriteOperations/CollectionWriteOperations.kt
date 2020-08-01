package collections.collectionWriteOperations

/**
    Collection Write Operations
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()
    demo07()
}


/*
    Las colecciones mutables admiten operaciones para cambiar el contenido de la colección, por ejemplo, agregar o quitar elementos.
 */



/**
    Adding elements

    add()
    addAll()
    plus operator - plusAssign (+=)
 */
/*
    add(), para agregar un solo elemento a un List o Set. El objeto especificado se agrega al final de la colección.
 */
fun demo01() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    println(numbers)
}

/*
    addAll() agrega cada elemento del objeto de argumento a un List o Set. El argumento puede ser un Iterable, una Sequence o un Array.
    Los tipos de receptor y el argumento pueden ser diferentes, por ejemplo, puede agregar todos los elementos desde un Set a una List.

    addAll() en una List agrega elementos nuevos en el mismo orden en que aparecen en el argumento. También puede llamar addAll() especificando una posición del elemento como primer argumento.
    El primer elemento de la colección de argumentos se insertará en esta posición. Otros elementos de la colección de argumentos lo seguirán, desplazando los elementos del receptor hasta el final.
 */
fun demo02() {
    val numbers = mutableListOf(1, 2, 5, 6)
    numbers.addAll(arrayOf(7, 8))
    println(numbers)
    numbers.addAll(2, setOf(3, 4))
    println(numbers)
}

/*
    También puede agregar elementos utilizando la versión in situ del  plus operator - plusAssign (+=) Cuando se aplica a una colección mutable,
    += agrega el segundo operando (un elemento u otra colección) al final de la colección.
 */
fun demo03() {
    val numbers = mutableListOf("one", "two")
    numbers += "three"
    println(numbers)
    numbers += listOf("four", "five")
    println(numbers)
}




/**
    Removing elements

    remove()
    removeAll()
    retainAll()
    clear()
    minus operator - minusAssign( -=)
 */
/*
    Para eliminar un elemento de una colección mutable, use la función remove(), acepta el valor del elemento y elimina una aparición de este valor.
 */
fun demo04() {
    val numbers = mutableListOf(1, 2, 3, 4, 3)
    numbers.remove(3)                    // removes the first `3`
    println(numbers)
    numbers.remove(5)                    // removes nothing
    println(numbers)
}

/*
    Para eliminar múltiples elementos a la vez, existen las siguientes funciones:

    removeAll() elimina todos los elementos que están presentes en la colección de argumentos. Alternativamente, puede llamarlo con un predicado como argumento; en este caso, la función elimina todos los elementos para los cuales el predicado devuelve true.
    retainAll() es lo opuesto a removeAll(): elimina todos los elementos excepto los de la colección de argumentos. Cuando se usa con un predicado, deja solo elementos que coinciden.
    clear() elimina todos los elementos de una lista y lo deja vacío.
 */
fun demo05() {
    val numbers = mutableListOf(1, 2, 3, 4)
    println(numbers)
    numbers.retainAll { it >= 3 }
    println(numbers)
    numbers.clear()
    println(numbers)

    val numbersSet = mutableSetOf("one", "two", "three", "four")
    numbersSet.removeAll(setOf("one", "two"))
    println(numbersSet)
}

/*
    Otra forma de eliminar elementos de una colección es con el operador minusAssign( -=), la versión in situ de minus.
    El segundo argumento puede ser una sola instancia del tipo de elemento u otra colección. Con un solo elemento en el lado derecho, -= elimina la primera aparición del mismo.
    A su vez, si se trata de una colección, se eliminan todas las apariciones de sus elementos.
    Por ejemplo, si una lista contiene elementos duplicados, se eliminan de una vez. El segundo operando puede contener elementos que no están presentes en la colección. Dichos elementos no afectan la ejecución de la operación.
 */
fun demo06() {
    val numbers = mutableListOf("one", "two", "three", "three", "four")
    numbers -= "three"
    println(numbers)
    numbers -= listOf("four", "five")
    //numbers -= listOf("four")    // does the same as above
    println(numbers)
}



/**
    Updating elements
 */
/*
    Las listas y mapas también proporcionan operaciones para actualizar elementos. Para los conjuntos, la actualización no tiene sentido ya que en realidad está eliminando un elemento y agregando otro.
 */
fun demo07() {

}



