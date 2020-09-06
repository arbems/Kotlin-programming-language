package classAndObjects.classes

/**
 * Clases
 */

fun main() {
    val v1 = Demo14(1, "")
}

/** Miembros de clase */

/*
    Constructores
    Bloques inicializadores
    Funciones
    Propiedades
    Clases anidadas e internas
    Declaraciones de objeto
 */

class Demo(private var v1: String = "") { /* constructor principal */

    /* Propiedades y variables */
    val v2: String = ""

    var p1: String = ""
        get() {
            return field.capitalize()
        }
        set(value) {
            field = value.trim()
        }

    /* constructores secundarios */
    constructor(v1: String, v2: String) : this(v1) {
        //...
    }

    constructor(v1: String, v2: String, v3: String) : this(v1) {
        //...
    }

    /* Declaración de objeto */
    val obj = object {
        var x: Int = 0
        var y: Int = 0
    }

    /* bloques inicializadores */
    init {
        v1 = "text"
    }

    init {
        //...
    }

    /* Funciones */
    fun function01(value: String) {
        v1 = value
    }

    fun function02() = v1.toUpperCase()


    // Clase anidada
    class Anidada {
        // fun funcion() = p1 // error
    }

    // Clase interna
    inner class Interna {
        fun function() = p1
    }
}


/** Constructores y bloques inicializadores */

/**
 * Constructor principal
 */

class Demo01(v1: String, val v2: String, private var v3: Int = 0) {

    init {
        println("Bloque de inicio se ejecuta después del constructor principal")
    }
}

/* Si el constructor principal tiene anotaciones o modificadores de visibilidad, hay que añadir la palabra clave `constructor`. */
class Demo02 constructor(v1: String) { /*...*/ }
class Demo03 (v1: String) { /*...*/ }
class Demo04 private constructor(v1: String) { /*...*/ }
class Demo05 @Inject constructor(v1: String) { /*...*/ }
class Demo06 @Inject public constructor(v1: String) { /*...*/ }
annotation class Inject

/* Las propiedades declaradas en el constructor primario pueden ser mutables `var` o de solo lectura `val` */
class Demo07(val v1: String, val v2: String, var v3: Int) { /*...*/ }

/* Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados */
class Demo08(val v1: String = "", val v2: String = "")

/* Constructor primario generado sin argumentos, si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos. */
class Demo09

/* La visibilidad del constructor será pública. Si no desea que su clase tenga un constructor público, debe declarar un constructor primario vacío con visibilidad no predeterminada. */
class Demo10 private constructor() { /* ... */ }


/**
 * Constructores secundarios
 */

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
class Demo12(val v1: String) {
    var list = mutableListOf<Demo12>()
    constructor(v1: String, parent: Demo12) : this(v1) {
        parent.list.add(this)
    }
}

/* Delegar al constructor primario */
// Si la clase tiene un constructor primario, cada constructor secundario necesita delegar al constructor primario, ya sea directa o indirectamente a través de otro(s) constructor(es) secundario(s).
class Demo13(val v1: String, val v2: String) {
    var list: MutableList<Demo13> = mutableListOf()

    constructor(v1: String, v2: String, parent: Demo13) : this(v1, v2) {
        parent.list.add(this)
    }
}

/**
 *  Orden de ejecución
 */
/* Primero se ejecuta el constructor principal.
   Después los bloque inicializadores, los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.
   Una clase puede tener uno o más bloques de inicialización ejecutándose en serie. */
class Demo14(v1: Int, v2: String) {
    var v3: Double = 0.0

    val p1 = "Primera propiedad: $v1".also(::println)

    init {
        println("Bloque inicio 1, v1: $v1 v2: $v2 v3: $v3") // v3 vacío
    }

    constructor(v1: Int, v2: String, v3: Double) : this(v1, v2) {
        this.v3 = v3
        println("Constructor secundario, v1: $v1 v2: $v2 v3: ${this.v3}")
    }

    val p2 = "Segunda propiedad: ${v2.length}".also(::println)

    init { //
        println("Bloque inicio 2, v1: $v1 v2: $v2 v3: $v3") // v3 vacío
    }
}


/**
 * Creando instancias de clase
 */


/**
 * Clases abstractas
 */
// Una clase y algunos de sus miembros pueden ser declarados abstractos. Un miembro abstracto no tiene una implementación en su clase
open class Demo15 {
    open fun draw() {}
}

abstract class Demo16 : Demo15() {
    abstract override fun draw()
}

/**
 * Companion objects
 */
// Si necesita escribir una función a la que se pueda llamar sin tener una instancia de clase pero necesita acceso a las partes internas de una clase,
// puede escribirla como miembro d  e una declaración de objeto dentro de esa clase.
// Aún más específicamente, si declara un objeto complementario dentro de su clase, puede acceder a sus miembros usando solo el nombre de la clase como calificador.
class Demo17 {
    companion object Named {}
}