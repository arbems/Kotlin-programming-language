package classAndObjects.generics.examples.polymorphism

// El cine proyecta largometrajes de varios tipos (peliculas, documentales..)

fun main() {
    val playList = listOf(
            Movie("movie-name-1", true),
            Movie("movie-name-2", true),
            Movie("movie-name-3", false),
            Documentary("Tegan"),
            Documentary("Peggy")
    )
    play(playList)
}

// Supertype
open class Cinema(val name: String) {
    fun play() {}
}

// Subtype
data class Movie(
        val movieName: String,
        val isNew: Boolean
): Cinema(movieName)

// Subtype
data class Documentary (val pandaName: String): Cinema(pandaName)

fun play(playList: List<Cinema>) {
    playList.forEach {
        it.play()
    }
}