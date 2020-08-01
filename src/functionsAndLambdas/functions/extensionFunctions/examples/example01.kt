package functionsAndLambdas.functions.extensionFunctions.examples

fun main() {
    demo01()
    demo02()
}


fun appendTag(string: String): String {
    return StringBuilder().append("<")
            .append(string)
            .append(">")
            .toString()
}

fun demo01() {
    val a = "MindOrks"
    val b = "AfterAcademy"

    // Using the function
    println(appendTag(a)) // <MindOrks>
    println(appendTag(b)) // <AfterAcademy>
}



/* En Kotlin para acortar esto tenemos algo llamado Función de Extensión. */
fun String.appendTag() {
    StringBuilder().append("<")
            .append(this)
            .append(">")
            .toString()
}

fun demo02() {
    // Aquí, puede ver que no estamos llamando a la función appendTag una y otra vez. Por el contrario, simplemente llamamos appendTag como una extensión con el operador de punto.

    val a = "MindOrks"
    val b = "AfterAcademy"

    // Using the extension function
    println(a.appendTag()) // kotlin.Unit
    println(b.appendTag()) // kotlin.Unit
}