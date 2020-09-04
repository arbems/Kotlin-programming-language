# Programando con lenguaje Kotlin - Herencia
 
## Herencia

### Superclass Any

Todas las clases en kotlin heredan de **Any** por defecto.

### open

Por defecto en Kotlin las clases son finales no se pueden heredar, para heredar de una clase hay que indicarlo con **open**

### Sobreescribiendo métodos (override, final override)

Una subclase sobreescribe un método de su superclase cuando define un método con las mismas características (nombre, número y tipo de argumentos) que el método de la superclase.

Para sobreescribir un método hay que indicar **open** el método de la superclase.

Si se indica **final override** ya no se puede sobreescribir en una clase que extienda de esta.

### Sobreescribiendo propiedades

Para sobreescribir una propiedad en una subclase hay que indicar **open** la propiedad de la superclase.

### Clase derivada y orden inicialización

Al diseñar una clase base, debe evitar el uso de miembros **open** en los constructores, inicializadores de propiedades y bloques de inicio.

### Llamando a la implementación de la superclase (super)

El código en una clase derivada puede llamar a sus funciones de superclase y a implementaciones de accesos de propiedad usando la palabra clave **super**

### Sobreescribiendo reglas

En Kotlin, la herencia está regulada por la siguiente regla: si una clase hereda múltiples implementaciones del mismo miembro de sus superclases inmediatas, debe anular este miembro y proporcionar su propia implementación (tal vez, usando una de las heredadas). 
Para denotar el supertipo del que se toma la implementación heredada, usamos supercalificado por el nombre del supertipo entre paréntesis angulares, por ejemplo `super<Base>`.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.