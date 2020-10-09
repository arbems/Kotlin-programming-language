package classAndObjects.classes

fun main() {
    run {
        println("--- Clases abstractas ---")

        open class Base {
            open fun function1() {}
        }

        abstract class Sample : Base() {
            val p1: String = "hello"
            abstract val p2: Int
            abstract val p3: Boolean

            abstract override fun function1()
        }

        class Sample2(override val p2: Int, override val p3: Boolean) : Sample() {
            override fun function1() { }
        }
    }
}