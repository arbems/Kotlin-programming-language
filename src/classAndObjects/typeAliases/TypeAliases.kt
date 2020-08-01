package classAndObjects.typeAliases

/**
 * Type Aliases
 * */

fun main() {

}

/** Collection types */
// No puede definir los typealias dentro de ninguna clase ya que los alias de tipo anidados y locales no son compatibles.
typealias MapIntToList = HashMap<Int, List<String>>
class A {
    val map = MapIntToList()
}


/** Template type */
typealias MapIntToTemplate<T> = HashMap<Int, T>
class B {
    val stringMap = MapIntToTemplate<String>()
    val mapOfLists = MapIntToTemplate<List<Int>>()
}


/** Inner classes & Interfaces */
class DemoClass {
    interface ViewHolderCallback

    inner class CustomViewHolder
}

typealias ViewHolderCallbackInner = DemoClass.ViewHolderCallback
typealias CustomViewHolderInner = DemoClass.CustomViewHolder

// Another example
// typealias AndroidColors = android.R.color
// typealias ProjectColors = R.color
// ContextCompat.getColor(this, ProjectColors.colorPrimary)
// ContextCompat.getColor(this, AndroidColors.black)