# Programando con lenguaje Kotlin - Herencia

## Superclass Any

Todas las clases en kotlin heredan de **Any** por defecto, esa es la super clase predeterminada para una clase sin supertipos declarados.

## open

Por defecto en Kotlin las clases son finales no se pueden heredar, para heredar de una clase hay que indicarlo con **open**.

## Sobrescribiendo métodos (override, final override)

Una subclase sobrescribe un método de su super clase cuando define un método con las mismas características (nombre, número y tipo de argumentos) que el método de la super clase.

Para sobrescribir un método hay que indicar **open** el método de la super clase.

Si se indica **final override** ya no se puede sobrescribir en una clase que extienda de esta.

## Sobrescribiendo propiedades

Funciona de manera similar a los métodos que al sobrescribir métodos.

Para sobrescribir una propiedad en una subclase hay que indicar **open** la propiedad de la super clase.

Puedes sobrescribir propiedad en constructor primario.

También puede anular una propiedad val con una propiedad var, pero no al revés.

## Clase derivada y orden inicialización

Al diseñar una clase base, debe evitar el uso de miembros **open** en los constructores, inicializadores de propiedades y bloques de inicio.

## Llamando a la implementación de la super clase (super)

El código en una clase derivada puede llamar a sus funciones de super clase y a implementaciones de accesos de propiedad usando la palabra clave **super**

## Sobrescribiendo reglas

En Kotlin, la herencia está regulada por la siguiente regla: si una clase hereda múltiples implementaciones del mismo miembro de sus super clases inmediatas, debe anular este miembro y proporcionar su propia implementación (tal vez, usando una de las heredadas). 
Para denotar el supertipo del que se toma la implementación heredada, usamos supercalificado por el nombre del supertipo entre paréntesis angulares, por ejemplo `super<Base>`.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.