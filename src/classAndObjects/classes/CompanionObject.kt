package classAndObjects.classes

fun main() {
    run {
        println("--- companion object ---")

        Sample.Companion01.p1.also { println(it) }

        Sample2.function1(150).also { println(it) }
        Sample2.Companion2.list.size.also { println(it) }

        Sample3.function01(22).also { println(it) }
    }
}

/** companion object */
class Sample {
    companion object Companion01 {
        val p1: String? = null
    }

    // Error! una clase solo puede contener un Companion object
    // companion object Companion02 {}
}

class Sample2(val p1: Int) {
    companion object Companion2 {
        val list = mutableListOf<Sample2>()

        fun function1(p1: Int): Sample2 {
            val p2 = Sample2(p1)
            list.add(p2)
            return p2
        }
    }
}

class Sample3 {
    companion object {
        fun function01(p1: Int) {}
    }
}
