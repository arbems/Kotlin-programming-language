# Programando con lenguaje Kotlin - Clases

# Clases

## Miembros de clase

Las clases pueden contener:

* Constructores y bloques inicializadores 
* Funciones 
* Propiedades 
* Clases anidadas e internas
    * Si a una clase anidada le anteponemos el modificador `inner` la transformamos en interna, con lo que conseguimos que la clase pueda acceder a todos los miembros de la clase externa.
* Declaraciones de objeto

## Constructores y bloques inicializadores

Una clase en Kotlin puede tener un **constructor principal** y uno o más **constructores secundarios**.

### Constructor principal

Si el constructor principal no tiene anotaciones ni modificadores de visibilidad, se puede omitir la palabra clave `constructor`.

El constructor principal no puede contener ningún código.

Las propiedades declaradas en el constructor primario pueden ser mutables `var` o de solo lectura `val`.

### Constructores secundarios

Tienen prefijo `constructor`.

Si la clase tiene un constructor primario, cada constructor secundario necesita delegar en el constructor primario.

Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario.

Si una clase no abstracta no declara ningún constructor (primario o secundario), tendrá un constructor primario generado sin argumentos.

Si todos los parámetros del constructor principal tienen valores predeterminados, el compilador generará un constructor sin parámetros adicional que utilizará los valores predeterminados.

`val` en el constructor secundario no esta permitido.

### Bloques inicializadores

El código de inicialización se puede colocar en bloques de inicialización, que tienen como prefijo la palabra clave `init`.

Los parámetros del constructor primario se pueden usar en los bloques inicializadores. El bloque de inicio siempre se llama después del constructor primario y antes que el cuerpo de constructores secundarios.

Los bloques inicializadores e inicializadores de propiedades se ejecuta antes del cuerpo del constructor secundario

## Instancias de clase

Kotlin no tiene una palabra clave `new`.


## Clases abstractas

## Companion objects



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.