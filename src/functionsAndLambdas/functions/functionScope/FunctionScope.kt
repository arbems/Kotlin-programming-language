package functionsAndLambdas.functions.functionScope

/**
 * Function scope - Alcance de la función
 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
}

/*
   En Kotlin, las funciones se pueden declarar en el nivel superior de un archivo, lo que significa que no es necesario crear una clase para contener una función.
   También pueden declararse como locales, como funciones miembro y como funciones de extensión.
 */


/** Local functions */
/*
   Kotlin admite funciones locales, es decir, una función dentro de otra función:
   La función local puede acceder a variables locales de funciones externas
 */
fun accumulate(number: Int): Int {
    var accNumber = number

    fun add() {
        val number = 1
        accNumber += number
    }

    for (i in 1..10)
        add()

    return accNumber
}

fun demo01() {
    accumulate(2)
}



/** Member functions */
/*
   Una función miembro es una función que se define dentro de una clase u objeto.
 */
class Sample {
    fun foo() { println("Foo") }
}

fun demo02() {
    Sample().foo() // creates instance of class Sample and calls foo
}



/** Extension functions */
/*
    Kotlin ofrece la capacidad de extender una clase con una nueva funcionalidad sin tener que heredar de la clase o usar patrones de diseño como Decorator.
    Esto se realiza a través de declaraciones especiales llamadas extensiones.
 */
fun Int.customInt(): Int {
    return this + 3
}

fun demo03() {
    3.customInt() // print 6
}




/** Generic functions */
/*
    No solo las clases pueden tener parámetros de tipo. Las funciones también pueden. Los parámetros de tipo se colocan antes del nombre de la función:
 */
fun <T> singletonList(item: T): List<T> {
    return listOf<T>()
}

fun <T> T.basicToString(): String {  // extension function
    return ""
}

fun demo04() {
    val l = singletonList<Int>(1)
}




/** Inline functions */
/*
    En una función normal su código es almacenado una vez en memoria. Cada vez que es invocada, se añaden los parámetros en la pila y se invoca.
    En una función inline no se realiza esta invocación, sino que el código de la función es insertado cada vez que se utiliza.

    Puede resultar conveniente utilizar una función inline si se encuentra en un bucle y va a ser llamada muchas veces. Pero en la mayoría de los casos es mejor no utilizarlas.

    La verdadera ventaja de las funciones inline aparece cuando se aplican a funciones de orden superior.
 */
inline fun someMethod(a: Int, func: () -> Unit):Int {
    func()
    return 2*a
}

fun demo05() {
    someMethod(2, { })
}