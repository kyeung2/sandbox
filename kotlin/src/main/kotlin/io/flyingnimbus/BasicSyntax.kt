package io.flyingnimbus


// constructor with default values
class BasicSyntax(
    private val a: String,
    private val b: String = "default value, no need for builders or constructor chaining"
) {

    private val nullableList: List<Int?> = listOf(1, 2, null, 4)
    private val intList: List<Int> = nullableList.filterNotNull()

    // explicit return type
    fun sumA(a: Int, b: Int): Int {
        return a + b
    }

    /*
    - inferred return type
    - single line function
    */
    fun sumB(a: Int, b: Int) = a + b

    /*
     - string interpolation / template
     - Unit is void in Java
     */
    fun interpolateString(a: Int, b: Int): Unit =
        print("like Javascript $a pretty nice ${a - b}")

    fun varAndVal() {
        // val and var like Scalar
        val readOnly = 12
        var reassignable: String = "You can reassign me"
        reassignable = "something else"

        println(reassignable)
    }

    fun nullableParameterAndElvisOperator(text: String?) = text?.length ?: -1

    fun iDontCare(text: String?) = text!!.length

    fun ifExpression(a: Int, b: Int) = if (a > b) a else b

    fun safeCast(a: Any){

        val aInt: Int? = a as? Int
        println("""
            multi-lined strings
            safe-cast may result in a null $aInt
        """.trimIndent())
    }

}