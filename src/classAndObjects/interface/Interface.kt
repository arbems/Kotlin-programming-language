package classAndObjects.`interface`

/** Interfaces */

import javax.swing.text.Position

fun main() {}

/** Interface */
interface Interface1 {
    fun function1() // abstract
    fun function2() {
        // optional body
    }
}
interface Interface2 {
    fun function3()
}

/** Implementing Interfaces */
class Sample : Interface1 {
    override fun function1() {
        // body
    }
}
class Sample2 : Interface1, Interface2 {
    override fun function1() {
        // body
    }

    override fun function3() {
        // body
    }
}

/** Properties in Interfaces */
interface Interface3 {
    val p1: Int // abstract

    val p2: String
        get() = "hello"

    fun function1() {
        print(p1)
    }
}
class Sample3 : Interface3 {
    override val p1: Int = 10
}

/** Interfaces Inheritance */
interface Interface4 {
    val p1: String
}

interface Interface5 : Interface4 {
    val p2: String
    val p3: String

    override val p1: String get() = "$p2 $p3"
}

data class Sample4(
        // 'p1' is not required
        override val p2: String,
        override val p3: String,
        val p4: String
) : Interface5


/** Resolving overriding conflicts */
interface Interface6 {
    fun function1() { print("A") } // is not abstract, if the function has a body
    fun function2() // is abstract
}

interface Interface7 {
    fun function1() { print("C") }
    fun function2() { print("D") }
}

class Sample5 : Interface6 {
    override fun function2() { print("B") }
}

class Sample6 : Interface6, Interface7 {

    init {
        super<Interface6>.function1()
        super<Interface7>.function1()
        super<Interface7>.function2()
    }

    override fun function1() {
        super<Interface6>.function1()
        super<Interface7>.function1()
    }

    override fun function2() {
        super<Interface7>.function2()
    }
}