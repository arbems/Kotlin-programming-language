package classAndObjects.generics.examples.animal

// generic constraint, poliformismo, herencia, out, in

fun main() {
    val cage1 = Cage(Dog("dog-name", "white"))
    println(cage1.getName())

    val cage2 = Cage(Cat("cat-name", "black)"))
    println(cage2.getName())

    val cage3 = Cage(Bird("bird-name", "red"))
    println(cage3.getName())

    /** Generic constraint */
    // val cage = Cage("Error, tiene que ser de tipo Animal, Dog, Cat o Bird")

    /** Poliformismo */
    var animal: Animal = Dog("Doglin", "gray")
    var dog: Dog = Dog("Tristan", "black")
    animal = dog

    /** Out */
    var cage4: CovariantCage<Dog> = CovariantCage(Dog("Charlie", "white"))
    var cage5: CovariantCage<Animal> = cage4
    println(cage5.printAnimalInfo()) // print: Animal is called Charlie

    /** In */
    var cage6: ContravariantCage<Bird> = ContravariantCage(Bird("Kiwi", "green"))
    var cage7: ContravariantCage<Canary> = cage6
    println(cage7.printAnimalInfo()) // print: Animal is called Kiwi and color green

    /** Type projection */
    val bird: Bird = Eagle("Nico", "brown")
    val animal01: Animal = Dog("Tobi", "gray")
    val cage08 = Cage<Animal>(animal01)
    val cage09 = Cage<Bird>(bird)
    examine(cage08)
    examine(cage09) // 'out' proporciona seguridad de tipo para que esta declaración sea válida
}

// <T: Animal> se conoce como Generic constraint, solo permite Supertype Animal o Subtypes
class Cage<T: Animal>(val t: T) {
    fun getName(): String? = t?.name
}

open class Animal(val name: String)

data class Dog(val dogName: String, val color: String): Animal(dogName)

data class Cat (val catName: String, val color: String): Animal(catName)

open class Bird(private val birdName: String, val color: String): Animal(birdName)

data class Canary(val canaryName: String, val canaryColor: String): Bird(canaryName, canaryColor)

data class Eagle(val eagleName: String, val eagleColor: String): Bird(eagleName, eagleColor)

class CovariantCage<out T: Animal>(private val t: T?) {
    fun getName(): String? = t?.name
    fun getContentType(): T? = t?.let { t } ?: run { null }
    fun printAnimalInfo(): String = "Animal is called ${t?.name}"
}

class ContravariantCage<in T: Bird>(private var t: T?) {
    fun getName(): String? = t?.name
    fun setContentType(t: T) { this.t = t }
    fun printAnimalInfo(): String = "Animal is called ${t?.name} and color ${t?.color}"
}

fun examine(cageItem: Cage<out Animal>) {
    val animal: Animal = cageItem.t
    println(animal)
}