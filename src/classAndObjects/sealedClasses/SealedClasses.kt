package classAndObjects.sealedClasses

/**
 * Sealed Classes
 * */

/*
  Las clases selladas se usan para representar jerarquías de clases restringidas.
  Una clase sellada puede tener subclases, pero todas deben declararse en el mismo archivo que la clase sellada.
  Una clase sellada es abstracta en sí misma, no puede ser instanciada directamente y puede tener miembros abstractos.
  Las clases selladas no pueden tener constructores non-private (sus constructores son privados por defecto).
  Tenga en cuenta que las clases que amplían las subclases de una clase sellada (herederos indirectos) se pueden colocar en cualquier lugar, no necesariamente en el mismo archivo.
*/

fun main() {

}

/** Sealed Classes */
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

// El beneficio clave de usar clases selladas entra en juego cuando las usas en una when expresion. Si es posible verificar que la declaración cubra todos los casos, no necesita agregar una elsecláusula a la declaración.
// Sin embargo, esto solo funciona si se usa whencomo una expresión (usando el resultado) y no como una declaración.
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // la clausula `else` no es requerida porque tiene cubierto todos los casos
}