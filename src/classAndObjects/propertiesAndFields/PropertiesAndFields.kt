package classAndObjects.propertiesAndFields

import classAndObjects.classes.Inject

/**
* Propiedades y campos
* */

fun main(args: Array<String>) {

}

/**
 * Declarando propiedades
 */
class Address {
    var name: String = "Holmes, Sherlock" // propiedad mutable con var y no mutable con val
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}
fun copyAddress(address: Address): Address {
    val result = Address() // 'new' no se usa en Kotlin
    result.name = address.name
    result.street = address.street
    // ...
    return result
}

/**
 * Getters y Setters
 * */
// El inicializador, getter y setter son opcionales
// El tipo de propiedad es opcional si se puede inferir del inicializador
// var allByDefault: Int? // error: se requiere inicializador explícito, default getter y setter implícito
var allByDefault: Int? = 0
var initialized = 1 // tiene tipo Int, default getter y setter

// La sintaxis completa de una declaración de propiedad de solo lectura(val) difiere de una mutable(var) de dos maneras:
// comienza con val en lugar de var
// y no permite un establecedor
val simple: Int? // tiene tipo Int, default getter, debe ser inicializado en constructor
    get() {
        TODO()
    }
val inferredType = 1 // tiene tipo Int y un default getter

// Podemos definir getters personalizados para una propiedad. Si definimos un getter personalizado, se llamará cada vez que accedamos a la propiedad (esto nos permite implementar una propiedad calculada).
// Aquí hay un ejemplo de un getter personalizado:
const val size = 0
val isEmpty: Boolean
    get() = size == 0
val isFill get() = size != 0  // tipo boolean inferido

// Si definimos un setter personalizado, se llamará cada vez que asignemos un valor a la propiedad
var stringRepresentation: String
    get() {
        return stringRepresentation
    }
    set(value) {
        setDataFromString(value)
    }
private fun setDataFromString(value: String) {
    stringRepresentation = value.substring(4)
}

// Si necesita cambiar la visibilidad o anotarlo, pero no necesita cambiar la implementación predeterminada, puede definir el descriptor de acceso sin definir su cuerpo:
var setterVisibility: String = "abc"
    private set // setter es private y tiene la implementación por defecto

var setterWithAnnotation: Any? = null
    @Inject set // anotar el setter con Inject

/**
 * Campos de respaldo
 * */
// Los campos no se pueden declarar directamente en las clases de Kotlin.
// Sin embargo, cuando una propiedad necesita un campo de respaldo, Kotlin lo proporciona automáticamente.
// Se puede hacer referencia a este campo de respaldo en los accesores utilizando el identificador de 'field'
var name = "alberto" // Nota: el inicializador asigna el campo de respaldo directamente
    get() = field.capitalize() // obtiene 'Alberto'
    set(value) {
        if (value.isNotBlank())
            field = value
    }
// no backing field
val isSuccess: Boolean
    get() = size == 1

/**
 * Propiedades de respaldo
 * */
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
 * */
// Si el valor de una propiedad de solo lectura se conoce en el momento de la compilación, márquelo como una constante de tiempo de compilación utilizando el modificador const
// Requerimientos:
// Nivel superior, o miembro de una declaración de objeto o un Companion object.
// Inicializado con un valor de tipo String o un tipo primitivo
// Sin getter personalizado
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"


/**
 * Propiedades y variables de inicialización tardía (Late-initialized)
 * */
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
 * */
// La inicialización de la variable adapter solo se realizará durante la primera vez en que se haga uso de la variable adapter.
// Por definición lazy es una función que durante la primera invocación ejecuta el lambda que se le haya pasado y en posteriores invocaciones retornará el valor computado inicialmente.
val adapter2: MyAdapter by lazy { initializeAdapter() }
fun initializeAdapter(): MyAdapter {
    return MyAdapter()
}
