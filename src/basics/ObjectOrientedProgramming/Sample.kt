package basics.ObjectOrientedProgramming

fun main() {
    run {
        val lordOfTheRings = Movie("La comunidad del anillo", duration = 3)
        lordOfTheRings.play()

        val breakingBad = Serie("Breaking bad", duration = 2)
        breakingBad.totalEpisodes = 5
        breakingBad.play()
    }

    run {
        fun playMedia(media: Media) {
            media.play()
        }

        val lordOfTheRings = Movie("La comunidad del anillo", duration = 3)
        playMedia(lordOfTheRings)

        val breakingBad = Serie("Breaking bad", duration = 2)
        breakingBad.totalEpisodes = 5
        playMedia(breakingBad)
    }

}

open class Media(var name: String, var duration: Int, var trailer: Trailer?, var available: Boolean = true) {
    companion object {
        private const val FRAME_RATE_HD = 60
        private const val FRAME_RATE_SD = 24
        private const val SECONDS_IN_A_MINUTE = 60

        fun getTotalFrames(duration: Int, isHd: Boolean): Int {
            return if (isHd) FRAME_RATE_HD * SECONDS_IN_A_MINUTE * duration
            else FRAME_RATE_SD * SECONDS_IN_A_MINUTE * duration
        }
    }

    open fun play(): Boolean {
        return if (available) {
            if (duration > 0) {
                for (i in 0 until duration) {
                    println("Playing movie $name")
                }
                true
            }
            else {
                println("Movie $name duration is less than zero")
                false
            }
        }
        else {
            println("Movie $name is not available")
            false
        }
    }

    fun pause() {

    }
}

class Movie(name: String, duration: Int, trailer: Trailer? = null, available: Boolean = true) : Media(name, duration, trailer, available) {
    var wonOscar: Boolean = false

    override fun toString(): String {
        return "$name: $duration min"
    }
}

class Serie(name: String, duration: Int, trailer: Trailer? = null, available: Boolean = true) : Media(name, duration, trailer, available) {
    var totalEpisodes = 0
    var totalSeasons = 0
    var episodeDuration = 0

    init {
        episodeDuration = duration
    }

    override fun play(): Boolean {
        val realDuration = episodeDuration * totalEpisodes
        return if (available) {
            if (realDuration > 0) {
                for (i in 0 until realDuration) {
                    println("Playing serie $name")
                }
                true
            }
            else {
                println("Serie $name duration is less than zero")
                false
            }
        }
        else {
            println("Serie $name is not available")
            false
        }
    }
}

class Trailer { }