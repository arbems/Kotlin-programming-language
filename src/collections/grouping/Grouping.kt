package collections.grouping

/**
    Grouping
 */

/*
    La biblioteca estándar de Kotlin proporciona funciones de extensión para agrupar elementos de colección.
 */

fun main() {
    demo01()
    demo02()
}



/*
    groupBy()
 */
fun demo01() {
    val numbers = listOf("one", "two", "three", "four", "five")

    /*
        La función básica groupBy() toma una función lambda y devuelve a Map.
        Cada clave es el resultado lambda y el valor correspondiente es el List de los elementos sobre los que se devuelve este resultado.

        Agrupa una lista de Strings por su primera letra:
     */
    val map01: Map<Char, List<String>> = numbers.groupBy { it.first().toUpperCase() } // {O=[one], T=[two, three], F=[four, five]}
    println(map01)

    /*
        groupBy() con un segundo argumento lambda: una función de transformación de valor.
        En el map de resultados de groupBy() con dos lambdas, las claves producidas por la función keySelector se asignan a los resultados de la función de transformación de valor en lugar de los elementos originales.
     */
    val map02: Map<Char, List<String>> = numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }) // {o=[ONE], t=[TWO, THREE], f=[FOUR, FIVE]}
    println(map02)
}



/*
    groupingBy()
    Si desea agrupar elementos y luego aplicar una operación a todos los grupos al mismo tiempo.
    Devuelve una instancia del tipo Grouping, le permite aplicar operaciones a todos los grupos de manera lazy: los grupos se crean realmente justo antes de la ejecución de la operación.
    Grouping admite las siguientes operaciones:
        - eachCount() cuenta los elementos en cada grupo.
        - fold() y reduce() realice operaciones de plegado y reducción en cada grupo como una colección separada y devuelva los resultados.
        - aggregate() aplica una operación dada posteriormente a todos los elementos en cada grupo y devuelve el resultado. Esta es la forma genérica de realizar cualquier operación en un Grouping. Úselo para implementar operaciones personalizadas cuando doblar o reducir no es suficiente.
 */
fun demo02() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")

    val grouping: Grouping<String, Char> = numbers.groupingBy { it.first() }

    val map: Map<Char, Int> = grouping.eachCount()

    println(map) // {o=1, t=2, f=2, s=1}
}