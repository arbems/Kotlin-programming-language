package functionsAndLambdas.functions.functionUsage

/**
 * Function usage
 */

fun main() {
    demo03()
    demo04()
    demo05()
    demo06()
    demo07()
    demo08()
    demo09()
    demo10()
    demo11()
    demo12()
}




/** Parameters */
/*
   name: typed
   El tipo de los parámetros se deben declarar de manera explícita
 */
fun powerOf(number: Int, exponent: Int) { /*...*/ }




/** Default arguments */
/*
    Los parámetros pueden tener valores por defecto, cuando el parámetro es omitido
 */
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) { /*...*/ }

/*
    Al sobrescribir funciones use siempre los mismos valores de parámetros predeterminados que el método base.
 */
open class A {
    open fun foo(i: Int = 10) { /*...*/ }
}

class B : A() {
    // override fun foo(i: Int = 20) { /*...*/ } // error, no default value allowed
    override fun foo(i: Int) { /*...*/ }
}

fun demo03() {
    B().foo() // default value 10
    B().foo(5)
    B().foo(i = 7)
}

/*
    Usando default value
 */
fun foo(bar: Int = 0, baz: Int) { /*...*/ }
fun demo04() {
    foo(baz = 1) // the default value bar = 0 is used
}

/*
    Si el último argumento después de los parámetros predeterminados es una lambda, se puede pasar como un argumento con nombre o fuera del paréntesis:
 */
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /*...*/ }
fun demo05() {
    foo(1) { println("hello") } // Uses the default value baz = 1
    foo(qux = { println("hello") })  // Named arguments, uses both default values bar = 0 and baz = 1
    foo { println("hello") }         // outside the parentheses, uses both default values bar = 0 and baz = 1
}



/** Named arguments */
/*
    Esto es muy conveniente cuando una función tiene una gran cantidad de parámetros o parámetros predeterminados.
 */
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') { /*...*/ }
fun demo06() {
    val str = "value-string"
    reformat(str) // all default arguments
    reformat(str, true, true, false, '_') // non-default
    reformat(str, normalizeCase = true, upperCaseFirstLetter = true, divideByCamelHumps = false, wordSeparator = '_') // named parameters
    reformat(str, wordSeparator = '_') // not need all arguments
}

/*
    Cuando se llama a una función con argumentos posicionales y nombrados, todos los argumentos posicionales deben colocarse antes del primero nombrado.
 */
fun f(x: Int, y: Int) { /* ... */}
fun demo07() {
    f(1, y = 2) // is allowed
    // f(x = 1, 2) // is not allowed
}



/** Unit-returning functions */
/*
    Si una función no devuelve ningún valor, su tipo de retorno es Unit
 */
fun printHello(name: String?): Unit {
    // `return Unit` or `return` is optional
}
/*
    declaración de tipo de retorno también es opcional, no hace falta indicarlo de manera explícita
 */
fun printBye(name: String?) {
    // `return Unit` or `return` is optional
}




/** Single-expression functions */
/*
    Las llaves pueden ser omitidas cuando devuelve una expresión simple
 */
fun sum(x: Int): Int = x + 2
/*
    Declarar explícitamente el tipo de retorno es opcional en las single-expression
 */
fun rest(x: Int) = x - 2





/** Explicit return types */
/*
    Las funciones con cuerpo de bloque siempre deben especificar los tipos de retorno explícitamente.
    Unit en cuyo caso es opcional.
    Kotlin no infiere tipos de retorno para funciones con cuerpos de bloque porque tales funciones pueden tener un flujo de control complejo en el cuerpo,
    y el tipo de retorno no será obvio para el lector (y a veces incluso para el compilador).
 */
fun multi(a: Int, b: Int) { // is equals to (a: Int, b: Int): Unit
    // return a * b // error: Type mismatch. Required: Unit, Found: Int
}
// Correct with return type Int
fun multi02(a: Int, b: Int): Int {
    return a * b
}




/** Variable number of arguments (Varargs) */
/*
   Un parámetro de una función (normalmente la última) puede marcarse con el modificador vararg:
   Solo un parámetro puede marcarse como vararg.
   Si un parámetro vararg no es el último en la lista, los valores para los siguientes parámetros se pueden pasar utilizando la sintaxis de argumento con nombre
 */
fun <T> asList(vararg ts: T, name: String): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array, dentro de una función, un parámetro vararg de tipo T es visible como un array de T
        result.add(t)
    return result
}
/* permitiendo que se pase un número variable de argumentos a la función */
fun demo08(){
    val list = asList(1, 2, 3, name = "value-string")
}

/*
   Cuando llamamos a una función vararg, podemos pasar argumentos uno por uno, p. asList (1, 2, 3) o,
   si ya tenemos un array y queremos pasar su contenido a la función, usamos el operador de propagación (prefijo *)
 */
fun demo09(){
    val array = arrayOf(1, 2, 3)
    val list = asList(-1, 0, *array, 4, name = "value-string")
}




/** Infix notation */
/*
   infix methods
   Funciones que se pueden llamar usando la notación infix (omitiendo el punto y los paréntesis para la llamada).
   Las funciones de infix deben cumplir los siguientes requisitos:
    - Deben ser funciones miembro (es aquella que está declarada en ámbito de clase) o funciones de extensión;
    - Deben tener un solo parámetro;
    - El parámetro no debe aceptar un número variable de argumentos(Varargs) y no debe tener un valor predeterminado. */

/* función de extensión */
infix fun Int.customInfix(x: Int): Int {
    return this * x
}
fun demo10() {
    // calling the function using the infix notation
    3 customInfix 2 // print 6

    // is the same as
    3.customInfix(2) // print 6
}

/* función miembro */
class Test() {
    infix fun customInfix(a: Int): Int {
        return a
    }
}
fun demo11() {
    Test() customInfix 1 // print 1
}

/* 1 shl 2 + 3 es igual a 1 shl (2 + 3)
   0 until n * 2 es igual a 0 until (n * 2)
   xs union ys as Set<*> es igual a xs union (ys as Set<*>)
   a && b xor c es igual a a && (b xor c)
   a xor b in c es igual a (a xor b) in c  */
fun demo12() {
    1 shl 2 + 3
    1 shl (2 + 3) // print 32

    val n = 3
    0 until n * 2
    0 until (n * 2) // print 0..5

    val xs = setOf("one", "two", "three")
    val ys = setOf("four", "five", "six")
    xs union ys as Set<*>
    xs union (ys as Set<*>) // print [one, two, three, four, five, six]

    val a = true
    val b = false
    val c = true
    a && b xor c
    a && (b xor c) // print true
}

/*
   Tenga en cuenta que las funciones de infix siempre requieren que se especifiquen tanto el receptor como el parámetro.
   Cuando está llamando a un método en el receptor actual usando la notación infix, necesita usar this explícitamente:
 */
class MyStringCollection {
    infix fun add(s: String) { /*...*/ }

    fun build() {
        this add "abc"   // Correct
        add("abc")       // Correct
        //add "abc"        // Incorrect: the receiver must be specified
    }
}


