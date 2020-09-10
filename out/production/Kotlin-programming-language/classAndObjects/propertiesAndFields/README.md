# Programando con lenguaje Kotlin - Propiedades y campos

## Declarando propiedades

Las propiedades en las clases de Kotlin se pueden declarar como mutables usando la palabra clave `var` o como de solo lectura usando la palabra clave `val`.

Las propiedades deben ser obligatoriamente inicializadas de manera explícita o ser abstractas.

## Getters y Setters

La sintaxis completa de una declaración de propiedad de solo lectura difiere de una mutable de dos maneras: 
* comienza con `val` en lugar de `var` 
* y no permite un setter.

En kotlin las propiedades ya poseen **por defecto un getter** para propiedades `val` y un getter y setter para `var`. Por lo tanto la implementación de estos es opcional.

Para las propiedades no mutables `val` se puede añadir un getter personalizado que se llamará cada vez que accedamos a la propiedad, esto nos permite implementar una propiedad calculada.
En el caso de `var` se puede añadir un getter y también un setter personalizado que se llamará cada vez que asignemos un valor a la propiedad.

Si necesita cambiar la **visibilidad** de una propiedad o anotarla, pero no necesita cambiar la implementación predeterminada, puede definir el setter sin definir su cuerpo.

### Campos de respaldo (Backing field)
### Propiedades de respaldo (Backing property)

## Constantes de tiempo de compilación
## Propiedades y variables de inicialización tardía (`lateinit var`)
## Comprobando si un `lateinit var` está inicializado *(desde 1.2)*
## Sobrescribiendo propiedades
## Propiedades delegadas

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.