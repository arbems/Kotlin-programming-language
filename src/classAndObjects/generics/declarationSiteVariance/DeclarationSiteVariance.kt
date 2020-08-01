package classAndObjects.generics.declarationSiteVariance

/**
* Declaration-site variance
* */

fun main() {

}



/** out */
/*
* out (covariant type), T es un parámetro de tipo covariante
* * La regla general es: cuando un tipo de parámetro T de una clase C es declarado out.
* Esto debe ocurrir solo en out-position en los miembros de C, pero tambien C<Base> puede ser con seguridad un supertype de C<Derived>.
*
* Esto dice que la clase C es covariante en el parámetro T, o que T es un tipo de parámetro covariante.
* Puedes pensar en C como siendo un productor de T's, y NOT un consumidor de T's.
*
* El modificador out se denomina anotación de varianza, y dado que se proporciona en el sitio de declaración de parámetro de tipo, hablamos de la variación del sitio de declaración.
* */

interface Source<out T> {
    fun nextT(): T
}

fun demo01(strs: Source<String>) {
    val objects: Source<Any> = strs // Esto es correcto, ya que T es un parámetro out
}

/*
 *   Cuando un tipo de parámetro T de una clase A es declarado out, A<Base> puede ser con seguridad supertype de A<Derived>.
 * */
class A<out T>(private val value: T) {
    fun getValue(): T = value
}

fun demo02(ref: A<String>) {
    val y: A<Any> = ref // SubType puede ser asignado a SuperType, usando out
}




/** in */
/*
* in (contravariance type), T es un parámetro de tipo contravariante
* Además de out, Kotlin proporciona una anotación de varianza complementaria: in. Convierte un parámetro de tipo contravariante: solo se puede consumir y nunca producir.
* */

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo03(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 es de tipo Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}


/*
* Cuando un tipo de parámetro T de una clase B es declarado in, B<Derived> puede ser con seguridad supertype de B<Base>.
 */
class B<in T> {
    fun toString(value: T): String = value.toString()
}

fun demo04(ref: B<Number>) {
    val y: B<Double> = ref // SuperType puede ser asignado a subtype, usando in
}