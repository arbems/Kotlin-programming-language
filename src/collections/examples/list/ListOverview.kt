package collections.examples.list

/**
    List Overview
 */
/*
    Una colección ordenada (también conocido como una sequencia). El usuario de esta interface tiene control preciso sobre dónde se inserta cada elemento en la lista.
    El usuario puede acceder a los elementos por su índice entero (posición el la lista), y buscar por elementos en la lista.

    - Es una agrupación ordenada de elementos.
    - Lista se utiliza para recopilar elementos con duplicados.
    - Los nuevos métodos se definen dentro de la interfaz de la Lista.
 */

fun main() {
    demo01()
    demo02()
}



/** Read-only & Mutable List */
/*
    Funciones básicas:
 */
fun demo01() {
    val list: List<String> = listOf("one", "two", "three", "four", "five")

    /*
        Query Operations
    */
    val size = list.size
    val isEmpty = list.isEmpty()
    val contains = list.contains("two")

    /*
        Bulk Operations
    */
    val containsAll = list.containsAll(listOf("one", "two"))

    /*
        Positional Access Operations
    */
    val element = list.get(1) // or list[1]

    /*
        Search Operations
    */
    val index = list.indexOf("two")
    val lastIndex = list.lastIndexOf("two")

    /*
        List Iterators
    */
    val listIterator = list.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }


    /*
        View
    */
    val subList = list.subList(0, 1)


}



/** Mutable List Only */
/*
    Operaciones de escritura: agregar, eliminar y actualizar sus elementos. Funciones básicas:
 */
fun demo02() {
    val mutableList: MutableList<String> = mutableListOf("one", "two", "three", "four", "five")

    /*
        Modification Operations
    */
    mutableList.add("four")
    mutableList.remove("four")

    /*
        Bulk Modification Operations
    */
    mutableList.addAll(mutableListOf("four", "five"))
    mutableList.addAll(1, listOf("one"))
    mutableList.removeAll(mutableListOf("four", "five"))
    mutableList.retainAll(mutableListOf("one", "two"))
    mutableList.clear()

    /*
        Positional Access Operations
    */
    mutableList.set(1, "cero") // or mutableList[1] = "zero"
    mutableList.add(1, "nine")
    mutableList.removeAt(0)

    /*
        List Iterators
    */
    val listIterator = mutableList.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    listIterator.next()
    listIterator.remove()
    listIterator.next()
    listIterator.add("one")
    listIterator.next()
    listIterator.set("three")
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }

    /*
        View
    */
    val subList = mutableList.subList(0, 1)
}