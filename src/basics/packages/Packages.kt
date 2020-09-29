package basics.packages

/** Packages */

/** Por defecto, se importan varios paquetes en cada archivo Kotlin: */

// kotlin.*
// kotlin.annotation.*     API para las anotaciones
// kotlin.collections.*    Tipos de colección, como Iterable, Collection, List, Set, Map y relacionadas de nivel superior y extensión de funciones.
// kotlin.comparisons.* (since 1.1)    Funciones de ayuda para crear instancias de Comparator.
// kotlin.io.*     API para trabajar con archivos y secuencias.
// kotlin.ranges.*      Rangos, progresiones y funciones relacionadas de nivel superior y extensión.
// kotlin.sequences.*    Tipo de secuencia que representa colecciones evaluadas perezosamente. Funciones de nivel superior para instanciar secuencias y funciones de extensión para secuencias.
// kotlin.text.*     Funciones para trabajar con texto y expresiones regulares.

/** Se importan paquetes adicionales según la plataforma de destino: */
// JVM:
    // java.lang.*
    // kotlin.jvm.*    Funciones y anotaciones específicas de la plataforma Java.
// JS:
    // kotlin.js.*     Funciones y otras API específicas de la plataforma JavaScript.



/** Un archivo fuente puede comenzar con una declaración de paquete: */
//package org.example

//fun printMessage() { /* ... */ }
//class Message { /* ... */ }

/** Importar paquetes */
//import org.example.Message
// o todos los contenidos accesibles de un ámbito (paquete, clase, objeto, etc.):
//import org.example.*
// Si hay un choque de nombres, podemos desambiguar usando una palabra clave para cambiar el nombre local de la entidad en conflicto:
//import org.example.Message // Message is accessible
//import org.test.Message as testMessage // testMessage stands for 'org.test.Message'

// import palabra clave no está restringida a importar clases; También puede usarlo para importar otras declaraciones:
// funciones y propiedades de nivel superior
// funciones y propiedades declaradas en declaraciones de objeto
// constantes enum
