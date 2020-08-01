package collections.rangesAndProgressions

/**

 */

fun main() {
    demo01()
    demo02()
    demo03()
    demo04()
    demo05()
    demo06()

}

/*
    Kotlin le permite crear fácilmente rangos de valores utilizando la función rangeTo() kotlin.ranges y su forma de operador ..
    Por lo general, rangeTo() se complementa con in o !in funciones.
 */
fun demo01() {
    val i = 2
    if (i in 1..4) {  // equivalent of 1 <= i && i <= 4
        print(i)
    }
}

/*
    Rangos de tipo integral (IntRange, LongRange, CharRange)
    Estos rangos también son progresiones de los tipos integrales correspondientes. Tales rangos se usan generalmente para la iteración en los bucles for.
 */
fun demo02() {
    for (i in 1..4) print(i)
}
/* iterar números en orden inverso, use la función downTo en lugar de .. */
fun demo03() {
    for (i in 4 downTo 1) print(i)
}
/* iterar sobre números con un paso arbitrario */
fun demo04() {
    for (i in 1..8 step 2) print(i)
    println()
    for (i in 8 downTo 1 step 2) print(i)
}
/* Para iterar un rango de números que no incluye su elemento final, use la función hasta: */
fun demo05() {
    for (i in 1 until 10) {       // i in [1, 10), 10 is excluded
        print(i)
    }
}




/**
    Range
 */
/*
    Los rangos se definen para tipos comparables: teniendo un orden, puede definir si una instancia arbitraria está en el rango entre dos instancias dadas.
    La operación principal en rangos es contains, que se utiliza generalmente en la forma de iny !inoperadores.
 */
class Version(val major: Int, val minor: Int): Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        }
        return this.minor - other.minor
    }
}

fun demo06() {
    val versionRange = Version(1, 11)..Version(1, 30)
    println(Version(0, 9) in versionRange)
    println(Version(1, 20) in versionRange)
}




/**
    Progression
 */
/*
    Los rangos de tipos integrales, como Int, Long y Char, pueden tratarse como progresiones aritméticas de ellos.

    En Kotlin, estas progresiones se definen por tipos especiales: IntProgression, LongProgression y CharProgression.

    Las progresiones tienen tres propiedades esenciales: first elemento, last elemento y los elementos posteriores son el elemento anterior más un step.

    Cuando crea una progresión implícitamente iterando un rango, first y last elementos de esta progresión son los puntos finales del rango, y el step es 1.
*/
fun demo07() {
    for (i in 1..10) print(i)

    // Para definir un paso de progresión personalizado, use la función de paso en un rango.
    for (i in 1..8 step 2) print(i)

    // Por lo tanto, el último elemento no siempre es el mismo que el valor final especificado.
    for (i in 1..9 step 3) print(i) // the last element is 7

    // Para crear una progresión para iterar en orden inverso, use downTo
    for (i in 4 downTo 1) print(i)

    // Las progresiones implementan Iterable<N>, donde N es Int, Long o Char respectivamente, por lo que puede usarlas en varias funciones de colección como mapa, filtro y otras.
    println((1..10).filter { it % 2 == 0 })
}