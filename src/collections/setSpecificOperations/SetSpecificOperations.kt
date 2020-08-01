package collections.setSpecificOperations

/**
    Set Specific Operations
 */

fun main() {
    demo01()
    demo02()
    demo03()
}


/**
    union()
    intersect()
    subtract()
 */
/*
    El paquete de colecciones Kotlin contiene funciones de extensión para operaciones populares en sets: encontrar intersecciones, fusionar o restar colecciones entre sí.

    Para fusionar dos colecciones en una, use la función union(). Se puede usar en forma infix a union b.
    Tenga en cuenta que para las colecciones ordenadas el orden de los operandos es importante: en la colección resultante, los elementos del primer operando van antes que los elementos del segundo.
 */
fun demo01() {
    val numbers = setOf("one", "two", "three")

    println(numbers union setOf("four", "five"))
    println(setOf("four", "five") union numbers)
}


/*
    Para encontrar una intersección entre dos colecciones (elementos presentes en ambas), use intersect()
 */
fun demo02() {
    val numbers = setOf("one", "two", "three")

    println(numbers intersect setOf("two", "one"))
}


/*
    Para buscar elementos de colección que no estén presentes en otra colección, use subtract()
 */
fun demo03() {
    val numbers = setOf("one", "two", "three")

    println(numbers subtract setOf("three", "four"))
    println(numbers subtract setOf("four", "three")) // same output
}


/* NOTA: Tenga en cuenta que las operaciones de configuración también son compatibles List.
   Sin embargo, el resultado de las operaciones establecidas en las listas sigue siendo un Set, por lo que se eliminan todos los elementos duplicados. */