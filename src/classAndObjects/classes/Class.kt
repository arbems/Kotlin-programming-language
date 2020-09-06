package classAndObjects.classes

/**
 * Clases
 */

fun main() {

}

/**
 * Miembros de clase
 */

/*
    Constructores
    Bloques inicializadores
    Funciones
    Propiedades
    Clases anidadas e internas
    Declaraciones de objeto
 */

class Demo(private var p1: String = "default value") { /* constructor principal */

    /* Propiedades */
    val p2: String = ""

    var p3: String = ""
        get() {
            return field.capitalize()
        }
        set(value) {
            field = value.trim()
        }

    /* constructores secundarios */
    constructor(p1: String, p2: String) : this(p1) {
        //...
    }

    constructor(p1: String, p2: String, p3: String) : this(p1) {
        //...
    }

    /* Declaración de objeto */
    val obj = object {
        var x: Int = 0
        var y: Int = 0
    }

    /* bloques inicializadores */
    init {
        p1 = "text"
    }

    init {
        //...
    }

    /* Funciones */
    fun function01(value: String) {
        p1 = value
    }

    fun function02() = p1.toUpperCase()


    // Clase anidada
    class Anidada {
        // fun funcion() = p1 // error
    }

    // Clase interna
    inner class Interna {
        fun function() = p1
    }
}


/**
 * Constructores y bloques inicializadores
 */

/** Constructor principal */

class Demo01(p1: String, val p2: String, private var p3: Int = 0) {

    init {
        println("Bloque de inicio se ejecuta después del constructor principal")
    }
}

/* Si el constructor principal tiene anotaciones o modificadores de visibilidad, hay que añadir la palabra clave `constructor`. */
class Demo02 constructor(p1: String) { /*...*/ }
class Demo03(p1: String) { /*...*/ }
class Demo04 private constructor(p1: String) { /*...*/ }
class Demo05 @Inject constructor(p1: String) { /*...*/ }
class Demo06 @Inject public constructor(p1: String) { /*...*/ }
annotation class Inject

/* Las propiedades declaradas en el constructor primario pueden ser mutables `var` o de solo lectura `val` */
class Demo07(val p1: String, val p2: String, var p3: Int) { /*...*/ }

/* Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados */
class Demo08(val p1: String = "", val p2: String = "")

/* Constructor primario generado sin argumentos, si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos. */
class Demo09

/* La visibilidad del constructor será pública. Si no desea que su clase tenga un constructor público, debe declarar un constructor primario vacío con visibilidad no predeterminada. */
class Demo10 private constructor() { /* ... */ }


/** Constructores secundarios */

class Demo11 {
    var list: MutableList<String> = mutableListOf()

    constructor(brand: String) {
        list.add(brand)
        println("Constructor secundario")
    }

    constructor(brand: String, color: String) {
        list.add(brand)
        println("Constructor secundario")
    }

    init {
        println("Bloque de inicio se ejecuta antes que el cuerpo de constructores secundarios")
    }
}

/* Si la clase tiene un constructor primario, cada constructor secundario necesita delegar en el constructor primario. */
class Demo12(val p1: String) {
    var list = mutableListOf<Demo12>()

    constructor(p1: String, parent: Demo12) : this(p1) {
        parent.list.add(this)
    }
}

/* Delegar al constructor primario */
// Si la clase tiene un constructor primario, cada constructor secundario necesita delegar al constructor primario, ya sea directa o indirectamente a través de otro(s) constructor(es) secundario(s).
class Demo13(val p1: String, val p2: String) {
    var list: MutableList<Demo13> = mutableListOf()

    constructor(p1: String, p2: String, parent: Demo13) : this(p1, p2) {
        parent.list.add(this)
    }
}

/**
 * Orden de ejecución
 */

/* Primero se ejecuta el constructor principal.
   Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.
   Una clase puede tener uno o más bloques de inicialización ejecutándose en serie. */
class Demo14(p1: Int, p2: String) {
    var p3: Double = 2.0

    val p1 = "Primera propiedad: $p1".also(::println)

    init {
        println("Bloque inicio 1, p1: $p1 p2: $p2 p3: $p3") // p3 vacío
    }

    constructor(p1: Int, p2: String, p3: Double) : this(p1, p2) {
        this.p3 = p3
        println("Constructor secundario, p1: $p1 p2: $p2 p3: ${this.p3}")
    }

    val p2 = "Segunda propiedad: ${p2.length}".also(::println)

    init {
        println("Bloque inicio 2, p1: $p1 p2: $p2 p3: $p3") // p3 vacío
    }
}


/**
 * Creando instancias de clase
 */
val p1 = Demo14(1, "alberto", 3.0)


/**
 * Clases abstractas
 */
/* Las clases abstractas no pueden ser instanciadas. Su finalidad es utilizarlas como plantilla común para que otras clases la extiendan. */
open class Demo15 {
    open fun function01() {}
}

/* Las clases abstractas puede contener propiedades y funciones abstractas. */
abstract class Demo16 : Demo15() {
    abstract val p1: String
    abstract val p2: Int
    abstract val p3: Boolean

    abstract override fun function01()
}

/* Cualquier clase que extienda de clases abstractas debera implementar todos los métodos y variables abstractas de las misma. */
class Demo17(override val p1: String, override val p2: Int, override val p3: Boolean) : Demo16() {
    override fun function01() {
        TODO("Not yet implemented")
    }
}

/**
 * Companion objects
 */

class Demo18 {
    companion object Companion01 {
        val p1: String? = null
    }

    // Error! una clase solo puede contener un Companion object
    // companion object Companion02 {}
}

/* Si necesita escribir una función a la que se pueda llamar sin tener una instancia de clase pero necesita acceso a las partes internas de una clase,
   puede escribirla como miembro de una declaración de objeto dentro de esa clase.
   Aún más específicamente, si declara un objeto complementario dentro de su clase, puede acceder a sus miembros usando solo el nombre de la clase como calificador. */
val p2 = Demo18.Companion01.p1

/* Companion object es un singleton, y se puede acceder a sus miembros directamente a través del nombre de la clase que lo contiene */
class Demo19(val p1: Int) {
    companion object Companion02 {
        val list = mutableListOf<Demo19>()

        fun function01(p1: Int): Demo19 {
            val p2 = Demo19(p1)
            list.add(p2)
            return p2
        }
    }
}

val p3 = Demo19.function01(150)
val p4 = Demo19.Companion02.list.size

/* Podemos omitir el nombre del Companion Object de la declaración */
class Demo20 {
    companion object {
        fun function01(p1: Int) {}
    }
}

val p5 = Demo20.function01(22)