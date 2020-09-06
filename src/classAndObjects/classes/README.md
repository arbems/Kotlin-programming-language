# Programando con lenguaje Kotlin - Clases

## Miembros de clase

Las clases pueden contener:

* Constructores y bloques inicializadores 
* Funciones 
* Propiedades 
* Clases anidadas e internas
* Declaraciones de objeto

## Constructores y bloques inicializadores

Una clase en Kotlin puede tener un **constructor principal** y uno o más **constructores secundarios**.

### Constructor principal

Si el constructor principal tiene *anotaciones* o *modificadores de visibilidad*, hay que añadir la palabra clave `constructor`.

El constructor principal no puede contener ningún código.

Las propiedades declaradas en el constructor primario pueden ser mutables `var` o de solo lectura `val`.

Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados.

Constructor primario generado sin argumentos, si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos.

La visibilidad del constructor será pública. Si no desea que su clase tenga un constructor público, debe declarar un constructor primario vacío con visibilidad no predeterminada.

### Constructores secundarios

Tienen prefijo `constructor`.

Si la clase tiene un constructor primario, cada constructor secundario necesita delegar en el constructor primario.

Los *bloques inicializadores* e *inicializadores de propiedades* se ejecuta antes del cuerpo del constructor secundario.

Si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos.

Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados.

`val` en el constructor secundario no esta permitido.

### Bloques inicializadores

El código de inicialización se puede colocar en bloques de inicialización, que tienen como prefijo la palabra clave `init`.

Los parámetros del constructor primario se pueden usar en los bloques inicializadores. El bloque de inicio siempre se llama después del constructor primario y antes que el cuerpo de constructores secundarios.

Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.

Una clase puede tener uno o más bloques de inicialización ejecutándose en serie.

### Funciones

Las funciones también llamadas métodos, no es más que un conjunto de instrucciones que realizan una determindad tarea y la podemos invocar mediante su nombre.

[Ver más sobre Funciones](https://github.com/arbems/Kotlin-programming-language/tree/master/src/functionsAndLambdas)

### Propiedades

En Kotlin, no existe en concepto de campo tal como lo conoces; en su lugar, emplea el concepto de *propiedades*. Algo maravilloso es que los getters y setters para estas propiedades son autogenerados para nosotros por el compilador Kotlin. 

Por supuesto podemos definir nuestros propios métodos gets y sets que sobrescriben a los que están por defectos o inferidos por el compilador de Kotlin.

[Ver más sobre Propiedades](https://github.com/arbems/Kotlin-Programming-Language/tree/master/src/classAndObjects/propertiesAndFields)

### Clases anidadas e internas

Si a una clase anidada le anteponemos el modificador `inner` la transformamos en interna, con lo que conseguimos que la clase pueda acceder a todos los miembros de la clase externa.

[Ver más sobre Clases anidadas](https://github.com/arbems/Kotlin-Programming-Language/tree/master/src/classAndObjects/nestedClasses)

### Declaración de objeto

Declara un objeto especificando su nombre, su tipo y, opcionalmente, una expresión que defina su valor inicial.

## Orden ejecución

Primero se ejecuta el constructor principal.

Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.

Una clase puede tener uno o más bloques de inicialización ejecutándose en serie. 

## Creando instancias de clase

Kotlin no tiene una palabra clave `new`.

## Clases abstractas

Palabra reservada `abstract`.

Las clases abstractas no pueden ser instanciadas. Su finalidad es utilizarlas como plantilla común para que otras clases la extiendan.

Las clases abstractas puede contener propiedades y funciones abstractas. Cualquier clase que extienda de clases abstractas debera implementar todos los métodos y variables abstractas de las misma.

Para la herencia no es necesario la palabra `open` porque las clase abstracta ya lo tiene por defecto.

## Companion objects

Si necesita que una función o propiedad esté vinculada a una clase en lugar de a instancias de ella, puede declararla dentro de un **Companion object**.

Una clase solo puede tener un *Companion object* y los objetos complementarios no se pueden anidar.

*Companion object* es un singleton, y se puede acceder a sus miembros directamente a través del nombre de la clase que lo contiene (aunque también puede insertar el nombre del *Companion object* si desea ser explícito sobre el acceso).
Con el englobamiento que hacemos con el Companion Object es más que suficiente para englobar todos nuestros métodos y propiedades estáticas de una sola vez.

Podemos omitir el nombre del Companion Object de la declaración.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.