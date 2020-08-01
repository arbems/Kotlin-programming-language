package classAndObjects.enumClasses

/**
 * Enum Classes
 * */

fun main() {
    // Acceso al valor de color de un tipo de tarjeta específico
    val color = CardType2.SILVER.color // gray

    // Invoca los métodos anulados de las clases constantes anónimas
    val cashbackPercent = CardType3.SILVER.calculateCashbackPercent() // 0.25f

    // Acceso a funciones
    val creditLimit = CardType4.PLATINUM.getCreditLimit()
    var cardActive = CardType4.PLATINUM.getActive()

    // // Obtiene una constante enum por su nombre
    getEnumByName()

    // Iterando a través de las constantes Enum
    iteratorEnum()
    printAllValues<CardType>()

    // Invoca función estatica
    val cardType = CardType5.getCardTypeByName("SILVER")
}

/** Enum */
/** Definiendo Enums */
enum class CardType {
    SILVER, GOLD, PLATINUM // Enum con 3 constantes que describen los tipos de tarjetas de crédito
}


/** Inicializando Constantes Enum */
enum class CardType2(val color: String) { // Las enumeraciones en Kotlin, pueden tener un constructor
    SILVER("gray"), // Las constantes son instancias de enum class, las constantes se pueden inicializar pasando valores específicos al constructor
    GOLD("yellow"),
    PLATINUM("black")
}


/** Constantes de Enum como clases anónimas */
enum class CardType3 {
    // constantes enum:
    SILVER {
        override fun calculateCashbackPercent() = 0.25f
    },
    GOLD {
        override fun calculateCashbackPercent() = 0.5f
    },
    PLATINUM {
        override fun calculateCashbackPercent() = 0.75f
    }; // separe las definiciones constantes enum de las definiciones de miembro con un punto y coma

    // miembros enum
    abstract fun calculateCashbackPercent(): Float
}


/** Enums que implementan interfaces */
// Una clase enum puede implementar una interfaz pero no derivar de una clase
interface ICardLimit {
    fun getCreditLimit(): Int
    fun getActive(): Boolean
}
// implementa interfaz
enum class CardType4 : ICardLimit {
    SILVER {
        override fun getCreditLimit() = 100000
        override fun getActive() = true
    },
    GOLD {
        override fun getCreditLimit() = 200000
        override fun getActive() = true
    },
    PLATINUM {
        override fun getCreditLimit() = 300000
        override fun getActive() = true
    }
}


/** Construcciones Enum comunes */
// Obtiene una constante enum por su nombre
fun getEnumByName() {
    val names: Array<CardType> = CardType.values()
    names.forEach {
        val cardType = CardType.valueOf(it?.toString().toUpperCase()) // método estático valueOf, arroja un IllegalArgumentExceptionsi el nombre especificado no coincide con ninguna de las constantes enum definidas en la clase.
        println("Name: $cardType")
    }
}

// Iterando a través de las constantes Enum
fun iteratorEnum() {
    for (cardType2 in CardType2.values()) {
        println(cardType2.color)
    }
}

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}


/** Métodos Estáticos */
// Para agregar una función "estática" a una enumeración, podemos usar un  objeto complementario
// Kotlin no tiene un concepto de  métodos estáticos
enum class CardType5(val color: String) {
    SILVER("gray"),
    GOLD("yellow"),
    PLATINUM("black");

    companion object {
        fun getCardTypeByName(name: String) = valueOf(name.toUpperCase())
    }
}
