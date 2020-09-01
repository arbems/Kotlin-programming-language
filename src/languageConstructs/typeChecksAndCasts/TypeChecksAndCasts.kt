package languageConstructs.typeChecksAndCasts

/**
 * Comprobaciones de type y casts
 * */

/**/

fun main() {
    demo01()
    demo02()
}

/*
    is and !is Operators
 */
fun demo01() {
    val obj: String = "hello"
    if (obj is String) {
        println(obj.length)
    }

    if (obj !is String) { // same as !(obj is String)
        println("Not a String")
    }
    else {
        println(obj.length)
    }
}

/*
    Casts inteligentes
 */
fun demo02() {
    demo02("hello")
    demo02(3)
    demo02(listOf<String>())
}
fun demo02(x: Any) {
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }

    if (x !is String) return

    // x is automatically cast to string on the right-hand side of `||`
    if (x !is String || x.length == 0) return

    // x is automatically cast to string on the right-hand side of `&&`
    if (x is String && x.length > 0) {
        print(x.length) // x is automatically cast to String
    }

    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }
}

/*
    Operador de conversión "inseguro"
 */
fun demo03() {
    var y: Int = 0

    // si es NULL lanza una excepción.
    //val x: String = y as String

    // Para hacer que dicho código sea correcto para valores NULL, use el tipo que acepta valores NULL en el lado derecho de la conversión:
    val x: String? = y as String?

    println(x)
}

/*
    Operador de conversión "seguro" (nullable)
 */
fun demo04() {
    var y: Int = 0

    // as? operador de conversión "seguro", que devuelve NULL en caso de error
    val x: String? = y as? String

    println(x)
}

/*

 */
fun demo05() {

}

/*

 */
fun demo06() {

}

/*

 */
fun demo07() {

}

/*

 */
fun demo08() {

}

