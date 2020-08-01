package functionsAndLambdas.lambdas.higherOrderFunctions.examples

fun main() {
    demo01()
    demo02()
}

/*
   Ejemplo de función de orden superior, pasamos una expresión lambda como parámetro.
   - buildString toma una acción de función como parámetro
   - La función de acción toma StringBuilder (constructor) como un parámetro que devuelve Unit
   - y la función general buildString devuelve una cadena como salida.
*/
fun buildString(action: (StringBuilder) -> Unit): String {
    val builder = StringBuilder()
    action(builder)
    return builder.toString()
}
fun demo01() {
    // usando lambda
    // aquí builder es un receptor
    /* El receptor se usa en kotlin para acceder a la propiedad del tipo de receptor sin ninguna línea adicional de código o calificador. */
    var a = buildString { builder ->
        builder.append("<")
        builder.append("MindOrks")
        builder.append(">")
    }

    println(a)
}
/*
    Estamos obteniendo el builder como parámetro porque estamos pasando en acción (builder)
 */

fun demo02() {
    // expandiendo función, esto es solo para fines explicativos
    var a = buildString (fun (builder: StringBuilder) {
        builder.append("<")
        builder.append("MindOrks")
        builder.append(">")
    })

    println(a)
}