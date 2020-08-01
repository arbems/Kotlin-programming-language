package collections.ordering

/**
    Collection Ordering
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
}


/*
    Para definir un orden natural para un tipo definido por el usuario, haga que el tipo sea un heredero de Comparable. Esto requiere implementar la función compareTo(). compareTo() debe tomar otro objeto del mismo tipo como argumento y devolver un valor entero que muestre qué objeto es mayor:

    - Los valores positivos muestran que el objeto receptor es mayor.
    - Los valores negativos muestran que es menor que el argumento.
    - Cero muestra que los objetos son iguales.

    A continuación se muestra una clase que se puede usar para ordenar versiones que consisten en la parte mayor y la menor.
 */
class Version(val major: Int, val minor: Int): Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        } else if (this.minor != other.minor) {
            return this.minor - other.minor
        } else return 0
    }
}
fun demo01() {
    println(Version(1, 2) > Version(1, 3)) // false
    println(Version(2, 0) > Version(1, 5)) // true
}

/*
    Custom orders le permite ordenar instancias de cualquier tipo de la manera que desee.
    Puede definir un orden para objetos no comparables o definir un orden que no sea natural para un tipo comparable, usando Comparator función compare():
    toma dos instancias de una clase y devuelve el resultado entero de la comparación entre ellas.
 */
fun demo02() {
    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length } // ordena las cadenas por su longitud
    println(listOf("aaa", "bb", "c").sortedWith(lengthComparator)) // [c, bb, aaa]
}
/* Una forma más corta de definir a Comparatores la compareBy() función de la biblioteca estándar.
   Toma una función lambda que produce un Comparablevalor a partir de una instancia y define el orden personalizado como el orden natural de los valores producidos. */
fun demo03() {
    println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length }))
}



/**
    Natural order

    sorted() y sortedDescending()
 */
/*
    Las funciones básicas sorted() y los sortedDescending() elementos de retorno de una colección ordenados en secuencia ascendente y descendente según su orden natural. Estas funciones se aplican a colecciones de Comparableelementos.
 */
fun demo04() {
    val numbers = listOf("one", "two", "three", "four")

    println("Sorted ascending: ${numbers.sorted()}")
    println("Sorted descending: ${numbers.sortedDescending()}")
}



/**
    Custom orders

    sortedBy() y sortedByDescending()
    sortedWith()
 */
/*
    Para orden personalizados u ordenar objetos no comparables, existen las funciones sortedBy() y sortedByDescending().
    Toman una función de selector que asigna elementos de colección a Comparable valores y clasifican la colección en el orden natural de esos valores.
 */
fun demo05() {
    val numbers = listOf("one", "two", "three", "four")

    val sortedNumbers = numbers.sortedBy { it.length }
    println("Sorted by length ascending: $sortedNumbers")
    val sortedByLast = numbers.sortedByDescending { it.last() }
    println("Sorted by the last letter descending: $sortedByLast")
}
/*
    Para definir un orden personalizado para la ordenación de la colección, puede proporcionar el tu propio Comparator.
    Para hacer esto, llame a la función sortedWith() pasando su Comparator. Con esta función, ordenar las cadenas por su longitud se ve así:
 */
fun demo06() {
    val numbers = listOf("one", "two", "three", "four")
    println("Sorted by length ascending: ${numbers.sortedWith(compareBy { it.length })}")
}



/**
    Reverse order

    reversed()
 */
/*
    Puede recuperar la colección en el orden inverso utilizando la función reversed().
    reversed() devuelve una nueva colección con las copias de los elementos.
    Entonces, si cambia la colección original más adelante, esto no afectará los resultados obtenidos previamente reversed().
 */
fun demo07() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.reversed())
}
/*
    Otra función de inversión asReversed(): devuelve una vista invertida de la misma instancia de colección, por lo que puede ser más ligera y preferible que reversed() si la lista original no cambiara.
 */
fun demo08() {
    val numbers = listOf("one", "two", "three", "four")
    val reversedNumbers = numbers.asReversed()
    println(reversedNumbers)
}
/*
    Si la lista original es mutable, todos sus cambios se reflejan en sus vistas invertidas y viceversa.

    Sin embargo, si la mutabilidad de la lista es desconocida o si la fuente no es una lista, reversed() es más preferible ya que su resultado es una copia que no cambiará en el futuro.
 */
fun demo09() {
    val numbers = mutableListOf("one", "two", "three", "four")
    val reversedNumbers = numbers.asReversed()
    println(reversedNumbers)
    numbers.add("five")
    println(reversedNumbers)
}




/**
    Random order

    shuffled()
 */
/*
    Hay una función que devuelve una nueva List que contiene los elementos de la colección en un orden aleatorio - shuffled().
    Puede llamarlo sin argumentos o con un Random objeto.
 */
fun demo10() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.shuffled())
}

