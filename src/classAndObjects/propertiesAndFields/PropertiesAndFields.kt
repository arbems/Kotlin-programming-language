package classAndObjects.propertiesAndFields

/**
 * Propiedades y campos
 */

fun main() {}

/**
 * Declarando propiedades
 */

class Demo01 {
    // val p1 // Error! Property must be initialized or be abstract
    val p2 = 0 // type inferred, default getter implied
    val p3: Int = 0 // explicit type, default getter implied

    init {
        // p2 = 5 // Error! Val cannot be reassigned
    }

    /*
        Nullable
     */
    // val p3: String? // Error! Property must be initialized or be abstract
    val p4: String? = null
    val p5: String? = ""

    /*
        const
     */
    // const val p30: Int = 0 // Error! Const 'val' are only allowed on top level or in objects
    companion object {
        const val p6: Int = 0
    }
}

class Demo02 {
    // var p1 // Error! Property must be initialized or be abstract
    // var p2: Int? // Error! explicit initializer required
    var p3 = 0 // type inferred, default getter and setter implied
    var p4: Int = 0 // explicit type

    /*
        Nullable
     */
    // var p5: String? // Error! Property must be initialized or be abstract
    var p6: String? = null
    var p7: String? = ""

    init {
        p6 = ""
        p7 = null
    }

    /*
        const
     */
    // Modifier 'const' is not applicable to 'vars'
}


/**
 * Getters y Setters
 */

class Demo03 {
    val p1 = 0 // getter is optional. Property type is optional if it can be inferred from the initializer.
    val p2 = 0 // custom getter
        get() {
            return field
        }
    // set(value) {} // Error! A 'val'-property cannot have a setter

    val p3 get() = 0

    val p4: String
        get() {
            return ""
        }

    /*
        Calculated property
     */
    val size = 1
    val p5 = 0
        get() {
            return field + size
        }
}

class Demo04 {
    var p1 = 0 // default getter and setter, are optional. Property type is optional if it can be inferred from the initializer.
    var p2: String? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var p3: String? = null
        get() = field
        set(value) { // custom setter
            field = value
        }

    private var p4: String = ""
    var p5: String
        get() {
            return p4
        }
        set(value) {
            p4 = value
        }

    /*
        Visibility
     */
    var p6: String = "abc"
        private set // the setter is private and has the default implementation

    var p7: Any? = null
        @Inject set // annotate the setter with Inject

    /*
        Calculated property
     */
    val size = 1
    var p8 = 0
        get() {
            return field + size
        }
        set(value) { // custom setter
            field = value - size
        }

    val p9 get() = this.size == 0 // Boolean, type inferred
}

annotation class Inject

/**
 * backing field
 */
// Los campos no se pueden declarar directamente en las clases de Kotlin.
// Sin embargo, cuando una propiedad necesita un campo de respaldo, Kotlin lo proporciona automáticamente.
// Se puede hacer referencia a este campo de respaldo en el setter utilizando el identificador de 'field'
var name: String = "alberto" // Nota: el inicializador asigna el campo de respaldo directamente
    get() = field.capitalize() // obtiene 'Alberto'
    set(value) {
        if (value.isNotBlank())
            field = value
    }

/**
 * backing property
 */
// Si desea hacer algo que no encaja en este esquema de "campo de respaldo implícito", siempre puede recurrir a tener una propiedad de respaldo:
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap() // Type parameters are inferred
        }
        return _table ?: throw AssertionError("Establecido en nulo por otro hilo")
    }

/**
 * Compile-Time Constants
 */
// Si el valor de una propiedad de solo lectura se conoce en el momento de la compilación, márquelo como una constante de tiempo de compilación utilizando el modificador const
// Requerimientos:
// Nivel superior, o miembro de una declaración de objeto o un Companion object.
// Inicializado con un valor de tipo String o un tipo primitivo
// Sin getter personalizado
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"


/**
 * Propiedades y variables de inicialización tardía (Late-initialized)
 */
// En el siguiente código básicamente la inicialización se da posteriormente. Habitualmente cuando una variable no es de un tipo que acepte null requiere ser inicializada en el constructor;
// sin embargo, no siempre aplica esto como, por ejemplo, en situaciones donde la inicialización de las variables se da a través de la inyección de dependencias o en la función setup dentro de nuestras pruebas unitarias.
// Para manejar este tipo de situaciones, sobretodo para evitar las verificaciones de valores nulos podemos marcar la propiedad con el modificador lateinit
// var adapter: MyAdapter // error
lateinit var adapter: MyAdapter // adapter.isInitialized

class MyAdapter() {}
// Es bueno recalcar que la aplicación de lazy aplica para propiedades de tipo val (inmutables) y lateinit para propiedades de tipo var (mutables)
// siempre que no tenga un setter o getter personalizado ni que sea un tipo primitivo.

/**
 * Initialization by Lazy
 */
// La inicialización de la variable adapter solo se realizará durante la primera vez en que se haga uso de la variable adapter.
// Por definición lazy es una función que durante la primera invocación ejecuta el lambda que se le haya pasado y en posteriores invocaciones retornará el valor computado inicialmente.
val adapter2: MyAdapter by lazy { initializeAdapter() }
fun initializeAdapter(): MyAdapter {
    return MyAdapter()
}

