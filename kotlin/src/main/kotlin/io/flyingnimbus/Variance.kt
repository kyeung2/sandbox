package io.flyingnimbus


// in, contravariant, consumed only, declaration-site variance
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}


// out, covariant, subtyping of generic type parameter T retained.
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {

    // since this is covariant, subtype relationship retained, so Source<String> is a subtype of Source<Any>
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter


    // same as just base types, String is a subtype of Any
    val string: String = ""
    val any: Any = string


}