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

### Propiedades

### Clases anidadas e internas

Si a una clase anidada le anteponemos el modificador `inner` la transformamos en interna, con lo que conseguimos que la clase pueda acceder a todos los miembros de la clase externa.

### Declaración de objeto

## Orden ejecución

Primero se ejecuta el constructor principal.

Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.

Una clase puede tener uno o más bloques de inicialización ejecutándose en serie.

## Creando instancias de clase

Kotlin no tiene una palabra clave `new`.

## Clases abstractas


## Companion objects



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.