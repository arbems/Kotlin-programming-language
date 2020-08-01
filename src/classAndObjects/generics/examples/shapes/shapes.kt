package classAndObjects.generics.examples.shapes

fun main() {
    var shapes = listOf(
            Triangle(5),
            Triangle(50),
            Circle(10),
            Circle(21)
    )

    shapes = shapes.customFilter { it.area > 20 }
    shapes = shapes.customFilterGeneric { it.area > 20 }
    shapes = shapes.customFilterGenericConstraint { it.area > 20 }
    shapes.forEach { println("${it.javaClass.kotlin.simpleName} con área de ${it.area}") }

    //...

    // (1..10).toList().customFilter { ... } // error
    var numberList = (1..10).toList().customFilterGeneric { it > 5 }
    // (1..10).toList().customFilterGenericConstraint { ... } // error, type mismatch: tipo no coincide
    numberList.forEach { println(it) }


}

open class Shape(val area: Int)
class Triangle(val a: Int): Shape(a)
class Circle(val a: Int): Shape(a)

fun List<Shape>.customFilter(filterCustom: (Shape) -> Boolean): List<Shape> {
    val resultList = mutableListOf<Shape>()
    for(item in this) {
        if(filterCustom(item))
        {
            resultList.add(item)
        }
    }
    return resultList
}

fun <T> List<T>.customFilterGeneric(filterCustom: (T) -> Boolean): List<T> {
    val resultList = mutableListOf<T>()
    for(item in this) {
        if(filterCustom(item))
        {
            resultList.add(item)
        }
    }
    return resultList
}

// Generic constraint
fun <T: Shape> List<T>.customFilterGenericConstraint(filterCustom: (T) -> Boolean): List<T> {
    val resultList = mutableListOf<T>()
    for(item in this) {
        if(filterCustom(item))
        {
            resultList.add(item)
        }
    }
    return resultList
}