package io.flyingnimbus

data class Person(val name: String, var age: Int = 0)

/**
 * - Kotlin scope functions create a temporary scope to execute code with in the object's context
 *
 * - provide a lambda for each {}
 * - they differ in:
 *      - The way to refer to the context object.
 *      - The return value:
 *          - lambda argument, it
 *          - lambda receiver, this
 * - scoped functions:
 *      - let{it} = result
 *      - run{this} = result
 *      - run{} = result
 *      - with{this} = result
 *      - apply{this} = context object
 *      - also{it} = context object
 */
class ScopeFunctionsExample {

    fun letExample() {
        val result: String = Person("Kye", 999).let {
            it.age = it.age - 10
            "result"
        }
    }

    fun runExample() {
        val numbers = mutableListOf("one", "two", "three")
        val result = numbers.run {
            add("four")
            add("five")
            count { it.endsWith("e") }
        }
    }

    fun runNoContextExample(){
        val hexNumberRegex = run {
            val digits = "0-9"
            val hexDigits = "A-Fa-f"
            val sign = "+-"

            Regex("[$sign]?[$digits$hexDigits]+")
        }
    }

    fun  withExample(){
        val numbers = mutableListOf("one", "two", "three")

        // with this object, do the following. Don't care about the result
        with(numbers) {
            val firstItem = first()
            val lastItem = last()
            println("First item: $firstItem, last item: $lastItem")
        }
    }

    fun applyExample() {
        // apply the following assignments to the object.
        val context: Person = Person("Isabel").apply {
            this.age = 4 // this can be omitted
        }
        // .also()... can chain as the context is returned
    }

    fun alsoExample() {
        val context: Person = Person("Isabel").also { value -> //explicit name for clarity
            print("getRandomInt() generated value $value")
        }
        // .also()... can chain as the context is returned
    }

}
