package collections.examples.map

/**
    Map Overview
 */

fun main() {
    demo01()
    demo02()
}


/** Read-only & Mutable Map */
/*
    Los mapas asocian claves con valores. Las claves deben ser únicas.
    A diferencia de las interfaces List y Set en Kotlin, map no extiende de la interfaz Collection.
 */
fun demo01() {
    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")

    /*
       Query Operations
     */
    val size = map.size
    val isEmpty = map.isEmpty()
    val containsKey = map.containsKey(2)
    val containsValue = map.containsValue("two")
    val element = map.get(3) // or map[3]
    //val elementByDefault: String = map.getOrDefault("aaa", "not exist")

    /*
        View
    */
    val keys: Set<Int> = map.keys
    val values: Collection<String> = map.values
    val entries: Set<Map.Entry<Int, String>> = map.entries
}

/** Mutable Map Only */
/*
    Funciones básicas:
 */
fun demo02() {
    val mutableMap: MutableMap<Int, String> = mutableMapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")

    /*
        Modification Operations
    */
    val value: String? = mutableMap.put(6, "six") // or map[6] = "six"
    val previousValue: String? = mutableMap.remove(6)
    val removed = mutableMap.remove(5, "five")

    /*
        Bulk Modification Operations
    */
    mutableMap.clear()
    mutableMap.putAll(mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five"))

    /*
        Views
    */
    val keys: MutableSet<Int> = mutableMap.keys
    val values: MutableCollection<String> = mutableMap.values
    val entries: MutableSet<MutableMap.MutableEntry<Int, String>> = mutableMap.entries
}