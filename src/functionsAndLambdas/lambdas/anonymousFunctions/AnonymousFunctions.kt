package functionsAndLambdas.lambdas.anonymousFunctions

/**
 * Anonymous Functions
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
}

/*
   Una cosa que falta en la sintaxis de la expresión lambda presentada anteriormente es la capacidad de especificar el tipo de retorno de la función.
   En la mayoría de los casos, esto es innecesario porque el tipo de retorno se puede inferir automáticamente.
   Sin embargo, si necesita especificarlo explícitamente, puede usar una sintaxis alternativa: una función anónima.
 */
fun demo01() {
    // función anónima, puedes especificar el tipo de retorno
    var a = fun(x: Int, y: Int): Int = x + y

    // expresión lambda, no puede especificar el tipo de retorno
    val b = { x: Int, y: Int -> x + y }
}

/*
  Una función anónima se parece mucho a una declaración de función normal, excepto que se omite su nombre. Su cuerpo puede ser una expresión o un bloque:
 */
fun demo02() {
    // expresión
    fun(x: Int, y: Int): Int = x + y
    // bloque
    fun(x: Int, y: Int): Int {
        return x + y
    }
}

/*
   Los parámetros y el tipo de retorno se especifican de la misma manera que para las funciones normales,
   excepto que los tipos de parámetros se pueden omitir si se pueden inferir del contexto:
 */
fun demo03() {
    val ints = listOf(1, 2, 3)
    ints.filter(fun(item) = item > 0)
}

/*
   La inferencia de tipo de retorno para funciones anónimas funciona igual que para las funciones normales:
   el tipo de retorno se infiere automáticamente para funciones anónimas con un cuerpo de expresión.
   Debe especificarse explícitamente (o se supone que es Unit) para funciones anónimas con un cuerpo de bloque.
 */
fun demo04() {
    // Función anónima con un cuerpo de bloque, debe especificarse explícitamente el tipo de retorno:
    fun(a: Int): Int {
        return a * 2
    }

    /*
    error, return type is required:
    fun(a: Int) {
        return a
    }
    */
}
fun demo05() {
    // Función anónima con un cuerpo de expresión infiere automáticamente el tipo de retorno:
    fun(a: Int) = a * 2
}


/*
   Nota: Tenga en cuenta que los parámetros de funciones anónimas siempre se pasan entre paréntesis.
   La sintaxis abreviada que permite dejar la función fuera del paréntesis funciona solo para expresiones lambda.

   Otra diferencia entre las expresiones lambda y las funciones anónimas es el comportamiento de non-local returns.
   Una declaración return sin etiqueta siempre regresa de la función declarada con la palabra clave fun.
   Esto significa que un return dentro de una expresión lambda regresará de la función que lo encierra,
   mientras que un return dentro de una función anónima regresará de la función anónima misma.
 */