package collections.mapSpecificOperations

/**
    Map Specific Operations
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
}


/**
    Retrieving keys and values

    get() or [key]
    getValue()
 */
/*
    Para recuperar un valor de un mapa, debe proporcionar su clave como argumento de la función get(). La [key] sintaxis abreviada también es compatible. Si no se encuentra la clave dada, regresa null.

    getValue() tiene un comportamiento ligeramente diferente: arroja una excepción si la clave no se encuentra en el mapa. Además, tiene dos opciones más para manejar la ausencia de clave:

    getOrElse() funciona de la misma manera que para las listas: los valores para claves inexistentes se devuelven de la función lambda dada.
    getOrDefault() devuelve el valor predeterminado especificado si no se encuentra la clave.
 */
fun demo01() {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap.get("one"))
    println(numbersMap["one"])
    println(numbersMap.getOrDefault("four", 10))
    println(numbersMap["five"])               // null
    //numbersMap.getValue("six")      // exception!
}

/*
    Para realizar operaciones en todas las claves o todos los valores de un mapa, puede recuperarlos de las propiedades keys y en values. keys es un conjunto de todas las claves del mapa y values es una colección de todos los valores del mapa.
 */
fun demo02() {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap.keys)
    println(numbersMap.values)
}




/**
    Filtering

    filter()
    filterKeys() y filterValues()
 */
/*
    Puede filtrar mapas con la función filter(), así como otras colecciones. Al llamar a filter() en un mapa, pasarle un predicado con un Pair como argumento.
    Esto le permite utilizar tanto la clave como el valor en el predicado de filtrado.
 */
fun demo03() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
    println(filteredMap)
}
/*
    También hay dos formas específicas para filtrar mapas: por claves y por valores. Para cada manera, hay una función: filterKeys() y filterValues().
    Ambos devuelven un nuevo mapa de entradas que coinciden con el predicado dado. El predicado para filterKeys() verifica solo las claves del elemento, el de los filterValues() verifica solo valores.
 */
fun demo04() {
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredKeysMap = numbersMap.filterKeys { it.endsWith("1") }
    val filteredValuesMap = numbersMap.filterValues { it < 10 }

    println(filteredKeysMap)
    println(filteredValuesMap)
}




/**
    plus and minus operators
 */
/*
    Debido al acceso clave a los elementos, los operadores plus(+) y minus(-) trabajan para mapas de manera diferente que para otras colecciones.

    plus devuelve un Map que contiene elementos de sus dos operandos: un Mapa a la izquierda y un Pair u otro Map a la derecha.
    Cuando el operando del lado derecho contiene entradas con claves presentes en el lado izquierdo Map, el mapa de resultados contiene las entradas del lado derecho.
 */
fun demo05() {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap + Pair("four", 4))
    println(numbersMap + Pair("one", 10))
    println(numbersMap + mapOf("five" to 5, "one" to 11))
}

/*
    minus crea una entrada Map a partir de un Mapa a la izquierda, excepto aquellas con keys del operando del lado derecho.
    Por lo tanto, el operando del lado derecho puede ser una sola key o una colección de keys: List, Set, etc.
 */
fun demo06() {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap - "one")
    println(numbersMap - listOf("two", "four"))
}




/**
    Map write operations

    put()
    putAll()
    plusAssign (+=) or []
    remove()
 */
/*
    Los mapas mutables ofrecen operaciones de escritura específicas del mapa. Estas operaciones le permiten cambiar el contenido del mapa utilizando el acceso basado en claves a los valores.

    Existen ciertas reglas que definen las operaciones de escritura en los mapas:
        - Los valores pueden ser actualizados. A su vez, las claves nunca cambian: una vez que agrega una entrada, su clave es constante.
        - Para cada clave, siempre hay un único valor asociado. Puede agregar y eliminar entradas enteras.
 */

/*
    Funciones de biblioteca estándar para operaciones de escritura disponibles en mapas mutables:
 */
/*
    Adding and updating entries

    Para agregar un nuevo par clave-valor a un mapa mutable, use put(). Cuando una nueva entrada se coloca en un LinkedHashMap(la implementación de mapa predeterminada),
    se agrega para que quede en último lugar al iterar el mapa. En los mapas ordenados, las posiciones de los nuevos elementos se definen por el orden de sus keys.
 */
fun demo07() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2)
    numbersMap.put("three", 3)
    println(numbersMap)
}
/*
    Para agregar múltiples entradas a la vez, use putAll(). Su argumento puede ser una Map o un grupo de Pairs: Iterable, Sequence o Array.
 */
fun demo08() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    numbersMap.putAll(setOf("four" to 4, "five" to 5))
    println(numbersMap)
}
/*
    Ambos put() y putAll() sobrescriben los valores si las claves dadas ya existen en el mapa. Por lo tanto, puede usarlos para actualizar los valores de las entradas del mapa.
 */
fun demo09() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2)
    val previousValue = numbersMap.put("one", 11)
    println("value associated with 'one', before: $previousValue, after: ${numbersMap["one"]}")
    println(numbersMap)
}
/*
    También puede agregar nuevas entradas a los mapas utilizando el formulario de operador abreviado. Hay dos maneras:

    plusAssign (+=) operador.
    el alias [] del operador para put().
 */
fun demo10() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2)
    numbersMap["three"] = 3 // calls numbersMap.put("three", 3)
    numbersMap += mapOf("four" to 4, "five" to 5)
    println(numbersMap)
}




/*
    Removing entries

    Para eliminar una entrada de un mapa mutable, use la función remove(). Al llamar remove(), puede pasar una clave o un par clave-valor completo.
    Si especifica tanto la clave como el valor, el elemento con esta clave se eliminará solo si su valor coincide con el segundo argumento.
 */
fun demo11() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    numbersMap.remove("one")
    println(numbersMap)
    numbersMap.remove("three", 4) // doesn't remove anything
    println(numbersMap)
}

/*
    También puede eliminar entradas de un mapa mutable por sus keys o values. Cuando se solicitan valores, remove() elimina solo la primera entrada con el valor dado.
 */
fun demo12() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3, "threeAgain" to 3)
    numbersMap.keys.remove("one")
    println(numbersMap)
    numbersMap.values.remove(3)
    println(numbersMap)
}

/*
    El operador minusAssign(-=) también está disponible para mapas mutables.
 */
fun demo13() {
    val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    numbersMap -= "two"
    println(numbersMap)
    numbersMap -= "five" // doesn't remove anything
    println(numbersMap)
}
