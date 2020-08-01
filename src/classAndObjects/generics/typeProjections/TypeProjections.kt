package classAndObjects.generics.typeProjections

fun main() {

}

/**
* Type Projections
* */



/** Use-site variance */
/*
* Es muy conveniente declarar un parámetro de tipo T como out y evitar problemas con el subtipo en el sitio de uso,
* ¡pero algunas clases no se pueden restringir para que solo devuelvan T! Un buen ejemplo de esto es Array:
* */
class ArrayDemo<T>(val size: Int) {
    // fun get(index: Int): T { /* ... */ } // error
    fun set(index: Int, value: T) { /* ... */ }
}
/*
* Esta clase no puede ser co o contravariante en T. Y esto impone ciertas inflexibilidades.
* Considera esta función:
*
* This function is supposed to copy items from one array to another. Intentemos aplicarlo en la práctica:
* */
fun copy01(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
fun demo01() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    // copy01(ints, any) // error: es tipo Array<Int> pero se esperaba Array<Any>
}
/*
* Aquí nos encontramos con el mismo problema: Array<T> es invariante en T, por lo tanto, ninguno de Array<Int> y Array<Any> es un subtipo del otro.
* Porque la copia podría estar haciendo cosas malas, es decir, podría intentar escribir, digamos, desde un String, y si realmente pasáramos un Array<Int> allí,
* se habría arrojado una ClassCastException algún tiempo después.
*
* Entonces, lo único que queremos asegurar es que copy() no haga nada malo. Queremos prohibir que escriba a from:
* */
fun copy02(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
fun demo02() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    copy02(ints, any)
}
/* Lo que ha sucedido aquí se llama proyección tipo, dijimos que from no es simplemente un Array, sino una restringida (proyectada): solo podemos llamar a los métodos que devuelven el parámetro de tipo T,
*  en este caso significa que solo podemos llamar a get().
*
* También puede proyectar un tipo con in:
 */
fun fill(dest: Array<in String>, value: String) { /* ... */ }
/*
* Array<in String>, puede pasar un Array de CharSequence o un Array<Object> a la función fill().
* */
fun demo03() {
    val objs: Array<CharSequence> = arrayOf("A", "B")
    fill(objs, "value-string")
}


/** Star-projection */
/*
    A veces quiere decir que no sabe nada sobre el argumento de tipo, pero aún así quiere usarlo de manera segura.
    La manera segura aquí es definir tal proyección del tipo genérico, que cada instancia concreta de ese tipo genérico sería un subtipo de esa proyección.

    Kotlin proporciona la llamada sintaxis Star-projection para esto:

    - Para Foo<out T : TUpper>, donde T es un parámetro de tipo covariante con el límite superior TUpper, Foo<*> es equivalente a Foo<out TUpper>.
      Esto significa que cuando el T se desconoce con seguridad se puede leer valores de TUpper a partir Foo<*>.
    - Para Foo<in T>, donde T es un parámetro de tipo contravariante, Foo<*> es equivalente a Foo<in Nothing>.
      Significa que no hay nada en lo que pueda escribir de Foo<*> de manera segura cuando T se desconoce.
    - Para Foo<T : TUpper>, donde T es un parámetro de tipo invariante con el límite superior TUpper,
      Foo<*> es equivalente a Foo<out TUpper> para leer valores y Foo<in Nothing> para escribir valores.
      Si un tipo genérico tiene varios parámetros de tipo, cada uno de ellos se puede proyectar de forma independiente.
      Por ejemplo, si el tipo se declara como interface Function<in T, out U> podemos imaginar las siguientes proyecciones de estrellas:

        Function<*, String> significa Function<in Nothing, String>;
        Function<Int, *> significa Function<Int, out Any?>;
        Function<*, *> significa Function<in Nothing, out Any?>.
* */
