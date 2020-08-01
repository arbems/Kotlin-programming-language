package collections.overview

/**
    Collections Overview
 */

/*
    Kotlin ofrece interfaces genéricas, clases y funciones para crear, completar y administrar colecciones de cualquier tipo (Biblioteca kotlin.collections).

    Una colección generalmente contiene de cero a n objetos del mismo tipo.
    Objects en una collection son llamados elements or items.
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
    demo13()
    demo14()
    demo15()
    demo16()
    demo17()
    demo18()
}



/*
    List es una colección ordenada con acceso a elementos por índices, números enteros que reflejan su posición.
    Los elementos pueden aparecer más de una vez en una lista.
    Un ejemplo de una list es una frase: es un grupo de palabras, su orden es importante y pueden repetirse.
 */
fun demo01() {
    val list: List<String> = listOf("1", "2", "1")
    val item = list[2]

    println(item)
}

/*
    Set es una colección de elementos únicos, un grupo de objetos sin repeticiones.
    En general, el orden de los elementos establecidos no tiene importancia.
    Por ejemplo, un abecedario es un set de letras.
 */
fun demo02() {
    val set: Set<Int> = setOf(1, 2, 1)
    // val item = set[2] // error
    val item = set.first()

    println(item)
}

/*
    Map (o diccionario) es un conjunto de pares clave-valor. Las claves son únicas y cada una de ellas se asigna exactamente a un valor.
    Los valores pueden ser duplicados.
    Los mapas son útiles para almacenar conexiones lógicas entre objetos, por ejemplo, la identificación de un empleado y su posición.
 */
fun demo03() {
    val map: Map<Int, String> = mapOf(1 to "value-string-1", 2 to "value-string-2", 3 to "value-string-3")
    val item = map[1]

    println(item)
}




/** Collection types */
/*
    La biblioteca estándar de Kotlin proporciona implementaciones para los tipos básicos de colección: sets, lists, y maps.

    Un par de interfaces representan cada tipo de colección:
    - Una interfaz de solo lectura que proporciona operaciones para acceder a los elementos de la colección.
    - Una interfaz mutable que amplía la interfaz de solo lectura correspondiente con operaciones de escritura: agregar, eliminar y actualizar sus elementos.
 */

/*
    Tenga en cuenta que alterar un mutable collection no requiere que sea un var: las operaciones de escritura modifican el mismo objeto de colección mutable, por lo que la referencia no cambia.
    Sin embargo, si intenta reasignar una colección val, obtendrá un error de compilación.
*/
fun demo04() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.add("five")   // esto es OK
    // numbers = mutableListOf("six", "seven")  // compilation error

    println(numbers)
}

/*
    Los tipos de colección de solo lectura son covariantes.

    Esto significa que, si una clase Rectangle hereda de Shape, puede usar List<Rectangle> en cualquier lugar donde se requiera List<Shape>.
 */
open class Shape {}
class Rectangle(public val name: String): Shape() {}
fun demo05() {
    val list = listOf(Rectangle("name01"), Rectangle("name02"), Rectangle("name03"))
    test01(list)
}
fun test01(shapes: List<Shape>) {
    val list = shapes as List<Rectangle>
    list.forEach { println(it.name) }
}

/*
    Maps son covariantes en el tipo de valor, pero no en el tipo de clave.
 */
fun demo06() {
    val map: Map<Int, Rectangle> = mapOf(1 to Rectangle("name01"), 2 to Rectangle("name02"))
    test02(map)
}
fun test02(rectangles: Map<Int, Rectangle>) {
    var list: Map<Int, Shape>
    list = rectangles // covariante en el tipo de valor
    list.forEach { println("key: ${it.key} value: ${it.value?.name}") }
}
fun demo07() {
    val map: Map<Int, Rectangle> = mapOf(1 to Rectangle("name01"), 2 to Rectangle("name02"))
    test03(map)
}
fun test03(shapes: Map<Int, Shape>) {
    var list = mapOf<Number, Shape>()
    // list = shapes // error, type mismatch. No covariante en el tipo de clave
}


/*
    Las colecciones mutables no son covariantes; de lo contrario, esto provocaría fallas en el tiempo de ejecución.

    Si MutableList<Rectangle> era un subtipo de MutableList<Shape>, podría insertar otros herederos de Shape (por ejemplo, Circle) en él, lo que violaría su argumento de tipo Rectangle.
 */
fun demo08() {
    val list = mutableListOf<Rectangle>()

    // test04(list) // error, no covariant
}
fun test04(shapes: MutableList<Shape>) {

}




/**
    Kotlin collection interfaces:

    * Iterable
        MutableIterable
            MutableCollection
                MutableList
                MutableSet

    * Iterable
        Collection
            List
                MutableList
            Set
                MutableSet

    * Map
        MutableMap

 */



/**
    Collection
 */
/*
    Collection<T> es la raíz de la jerarquía de colección, comportamiento común de una colección de solo lectura.
    Collection hereda de la interfaz Iterable<T> que define las operaciones para iterar elementos.
 */
fun printAll(strings: Collection<String>) {
    for(s in strings) print("$s ")
    println()
}

fun demo09() {
    val stringList = listOf("one", "two", "one")
    printAll(stringList)

    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)
}



/**
    MutableCollection
 */
/*
    MutableCollection es una colección con operaciones de escritura, como agregar y quitar.
 */
fun List<String>.getShortWordsTo(shortWords: MutableCollection<String>, maxLength: Int) {
    this.filterTo(shortWords) { it.length <= maxLength }
    // throwing away the articles
    val articles = setOf("a", "A", "an", "An", "the", "The")
    shortWords -= articles
}

fun demo10() {
    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords) // [ago, in, far, far]
}



/**
    List
 */
/*
    List<T> almacena elementos en un orden específico y les proporciona acceso indexado. En algunos aspectos, las listas son muy similares a los arrays.
    Sin embargo, hay una diferencia importante: el tamaño de un array se define durante la inicialización y nunca cambia; a su vez, una lista no tiene un tamaño predefinido;
    El tamaño de una lista se puede cambiar como resultado de operaciones de escritura: agregar, actualizar o eliminar elementos.

    En Kotlin, la implementación predeterminada de List es ArrayList, que se puede considerar como un array redimensionable.
    Los índices comienzan desde cero, el índice del primer elemento, y van a lastIndex, que es (list.size - 1).
 */
fun demo11() {
    val numbers = listOf("one", "two", "three", "four")
    println("Number of elements: ${numbers.size}")
    println("Third element: ${numbers.get(2)}")
    println("Fourth element: ${numbers[3]}")
    println("Index of element \"two\" ${numbers.indexOf("two")}")
}

/*
    Los elementos de la lista (incluidos los nulos) pueden duplicarse: una lista puede contener cualquier número de objetos iguales o ocurrencias de un solo objeto.
    Dos listas se consideran iguales si tienen los mismos tamaños y elementos estructuralmente iguales en las mismas posiciones.
 */
class Person(name: String, var age: Int) { }
fun demo12() {
    val bob = Person("Bob", 31)
    val people = listOf<Person>(Person("Adam", 20), bob, bob)
    val people2 = listOf<Person>(Person("Adam", 20), Person("Bob", 31), bob)
    println(people == people2) // false
    bob.age = 32
    println(people == people2) // false
}



/**
    MutableList
 */
/*
    MutableList es una List con operaciones de escritura específicas de la lista, por ejemplo, para agregar o eliminar un elemento en una posición específica.
 */
fun demo13() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    numbers.removeAt(1)
    numbers[0] = 0
    println("Demo13: $numbers") // print [0, 3, 4, 5]
}


/**
    Set
 */
/*
    Set<T> almacena elementos únicos; su orden es generalmente indefinido.
    Los elementos nulos también son únicos: un conjunto puede contener solo un nulo.
    Dos conjuntos son iguales si tienen el mismo tamaño, y para cada elemento de un conjunto hay un elemento igual en el otro conjunto.
 */
fun demo14() {
    val numbers = setOf(1, 2, 3, 4)
    println("Number of elements: ${numbers.size}")
    if (numbers.contains(1)) println("1 is in the set")

    val numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equal: ${numbers == numbersBackwards}") // print The sets are equal: true
}



/**
    MutableSet
 */
/*
    MutableSet es un conjunto con operaciones de escritura de MutableCollection.
    La implementación predeterminada de Set - LinkedHashSet - conserva el orden de inserción de los elementos.
    Por lo tanto, las funciones que dependen del orden, como first() o last(), devuelven resultados predecibles en dichos conjuntos
 */
fun demo15() {
    val numbers = setOf(1, 2, 3, 4)  // LinkedHashSet is the default implementation
    val numbersBackwards = setOf(4, 3, 2, 1)

    println(numbers.first() == numbersBackwards.first()) // false
    println(numbers.first() == numbersBackwards.last()) // true
}
/* Una implementación alternativa, HashSet, no dice nada sobre el orden de los elementos, por lo que llamar a tales funciones en él devuelve resultados impredecibles.
   Sin embargo, HashSet requiere menos memoria para almacenar la misma cantidad de elementos.
 */



/**
    Map
 */
/*
    Map<K, V> no hereda de la interfaz Collection; sin embargo, también es un tipo de colección Kotlin.
    Un mapa almacena pares clave-valor (o entradas); Las claves son únicas, pero se pueden emparejar diferentes claves con valores iguales.
    La interfaz del mapa proporciona funciones específicas, como el acceso al valor por clave, búsqueda de claves y valores, etc.
 */
fun demo16() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map") // same as previous
}
/*
    Dos mapas que contienen los pares iguales son iguales independientemente del orden de los pares.
 */
fun demo17() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
    val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)

    println("The maps are equal: ${numbersMap == anotherMap}") // true
}



/**
    MutableMap
 */
/*
    MutableMap es un mapa con operaciones de escritura de mapas, por ejemplo, puede agregar un nuevo par clave-valor o actualizar el valor asociado con la clave dada.
 */
fun demo18() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2)
    numbersMap.put("three", 3)
    numbersMap["one"] = 11

    println(numbersMap)
}
/*
    La implementación predeterminada de Map - LinkedHashMap - conserva el orden de inserción de elementos al iterar el mapa.
    A su vez, una implementación alternativa, HashMap, no dice nada sobre el orden de los elementos.
 */