package functionsAndLambdas.lambdas.functionTypes

/**
   Function types
*/

/*
    Kotlin usa function types para representar el tipo de una función.
    La sintaxis de function types: (Type, Type) -> ReturnType
    Todos los tipos de funciones tienen una lista de tipos de parámetros entre paréntesis y un tipo de retorno. Para devoluciones vacías, puede usar Unit.
*/

fun main() {
    demo01()
    demo02()
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
    //demo13()
    //demo14()
    demo15()
}



/** Function type */
/*
   Estos tipos tienen una notación especial que corresponde a las firmas de las funciones, es decir, sus parámetros y valores de retorno:
 */

/*
   - Todos los tipos de funciones tienen una lista de tipos de parámetros entre paréntesis y un tipo de retorno: (Type, Type) -> ReturnType
 */
fun demo01() {
    /*
       (A, B) -> C
       La función toma dos argumentos de los tipos A y B y devuelven un valor de tipo C
    */
    var a: (Int, Int) -> Int = { a, b -> a + b }
    // es equivalente a:
    var b = { a: Int, b: Int -> a + b }

    /*
        () -> A
        La lista de parámetros puede estar vacía, Unit return type no puede omitirse.
    */
    var c: () -> Unit = { println(Int) }
    // es equivalente a:
    var d = { println(Int) }
}

/*
   - Los tipos de función pueden tener opcionalmente un tipo de receptor adicional, que se especifica antes de un punto en la notación: Receptor.(Type) -> ReturnType
     Function literals con receptor a menudo se usan junto con estos tipos.
 */
fun demo02() {
    /*
       A.(B) -> C
       Representa funciones que se pueden invocar en un objeto receptor de A con un parámetro de B y devolver un valor de C
    */
    var a: String.(String) -> String = { this + it } // this es String receptor y it es el parametro String
}

/*
    - Suspending functions pertenecen a tipos de funciones de un tipo especial, que tienen un modificador de suspensión en la notación como: suspend () -> Unit ó suspend Receptor.(Type) -> ReturnType
 */
fun demo03() {
    /*
        suspend () -> Unit ó suspend A.(B) -> C
    */
}



/*
   La notación de function types puede incluir opcionalmente nombres para los parámetros de función: (name: Int, name: Int) -> Point
   Estos nombres se pueden usar para documentar el significado de los parámetros.
 */
fun demo04() {
    /*
        (x: Int, y: Int) -> Boolean
    */
    var a = { x: Int, y: Int -> x == y }
    // a(x = 2, y = 4) // error, Named arguments are not allowed for function types
}

/*
   Para especificar que un tipo de función es anulable, use paréntesis: ((Int, Int) -> Int)?
 */
fun demo05() {
    // ((Int, Int) -> Int)?
    var a: ((Int, Int) -> Int)? = null
}

/*
   Los tipos de funciones se pueden combinar usando paréntesis: (Int) -> ((Int) -> Unit)
 */
fun demo06() {
    // (Int) -> ((Int) -> Unit)
    var a: (Int) -> ((Int) -> Unit) = { x -> { y -> println(Int) } }
}

/*
   La notación de flecha es asociativa a la derecha, (Int) -> (Int) -> Unit
   Es igual a (Int) -> ((Int) -> Unit) pero no es igual a ((Int) -> (Int)) -> Unit
 */
fun demo07() {
    // (Int) -> (Int) -> Unit
    var a: (Int) -> (Int) -> Unit = { x -> { y -> println(Int) } }
    // es equivalente a:
    // (Int) -> ((Int) -> Unit)
    var b: (Int) -> ((Int) -> Unit) = { x -> { y -> println(Int) } }
    // pero no es igual a:
    var c: ((Int) -> (Int)) -> Unit = { y -> println(Int) }
}

/*
   También puede dar a un tipo de función un nombre alternativo utilizando un type alias.
 */
fun demo08() {
    // type alias
}





/** Instantiating a function type */
/*
   Hay varias formas de obtener una instancia de un tipo de función:
 */

/*
   - Usando un bloque de código dentro de una función literal, la función literal es una notación especial utilizada para simplificar cómo se define una función.
     Hay dos tipos de literales de función en Kotlin::
            - un lambda expression: { a, b -> a + b }
            - un anonymous function: fun(s: String): Int { return s.toIntOrNull() ?: 0 }
     Los literales de función con receptor se pueden usar como valores de tipos de función con receptor.
 */
fun demo09() {
    // lambda expression
    val a: (Int, Int) -> Boolean = { a, b -> a > b}
    // es equivalente a
    val b = { a: Int, b: Int -> a > b }

    println(a(4, 3)) // true
    println(b(4, 3)) // true
}
fun demo10() {
    // anonymous function
    val a = fun(a: Int, b: Int): Boolean = a > b

    println(a(4, 3)) // true
}
fun demo11() {
    // función con receptor
    var a: Int.(Int) -> Boolean = {  this > it }
    // es equivalente a
    var b = fun Int.(a: Int) = this > a

    println(4.a(3)) // true
    println(2.b(3)) // false
}

/*
   - Usando una referencia invocable a una declaración existente:
        - un nivel superior, local, member, or extension function: ::isOdd, String::toInt
        - un nivel superior, member, or extension property: List<Int>::size,
        - un constructor: ::Regex
     Estos incluyen referencias invocables vinculadas que apuntan a un miembro de una instancia particular: foo::toString
 */
fun demo12() {
    fun isOdd(x: Int) = x % 2 != 0
    val numbers = listOf(1, 2, 3)
    numbers.filter(::isOdd) // function reference, print [1, 3]

    fun toInt(x: String) = x.toInt()
    var a: (String) -> Int = ::toInt
    println(a("1"))
}

/*
   - Usando instancias de una clase personalizada que implementa una function type como una interfaz:
 */
class IntTransformer: (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}
val intFunction: (Int) -> Int = IntTransformer()

/* El compilador puede inferir los function types para las variables si hay suficiente información: */
val a = { i: Int -> i + 1 } // The inferred type is (Int) -> Int



/*
   Los valores non-literal de los tipos de función con y sin receptor son intercambiables, de modo que el receptor puede sustituir el primer parámetro y viceversa.

   Por ejemplo, un valor de tipo (A, B) -> C se puede pasar o asignar donde se espera una A.(B) -> C y viceversa:
 */
val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
val twoParameters: (String, Int) -> String = repeatFun // OK

fun runTransformation(f: (String, Int) -> String): String {
    return f("hello", 3)
}
val result = runTransformation(repeatFun) // OK

/* Nota: Tenga en cuenta que una function type sin receptor se infiere de manera predeterminada, incluso si una variable se inicializa con una referencia a una función de extensión.
   Para alterar eso, especifique el tipo de variable explícitamente. */





/** Invoking a function type instance */
/*
   Se puede invocar un valor de un tipo de función utilizando su invoke(...) operator: f.invoke(x) ó solo f(x)

   Si el valor tiene un tipo de receptor, el objeto receptor debe pasarse como primer argumento.
   Otra forma de invocar un valor de un tipo de función con receptor es anteponerlo con el objeto receptor, como si el valor fuera un extension function: 1.foo(2)
*/
fun demo15() {
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // extension-like call
}





