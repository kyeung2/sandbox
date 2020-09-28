package io.flyingnimbus

import java.math.BigInteger
import java.math.BigInteger.*

fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1

tailrec fun add(a: Int, b: Int): Int {
    if (a == 0) {
        return b
    }
    return add(dec(a), inc(b))
}

fun addLoop(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (x != 0) {
        x = dec(x)
        y = inc(y)
    }
    return y
}


fun <T> List<T>.head(): T = if (this.isEmpty()) throw IllegalArgumentException("head called on empty list") else this[0]
fun <T> List<T>.tail(): List<T> =
    if (this.isEmpty()) throw IllegalArgumentException("tail called on empty list") else this.drop(1)

// not tail recursive
//fun sum(list: List<Int>): Int = if (list.isEmpty()) 0 else list.head() + sum(list.tail())


fun sum(list: List<Int>): Int {
    tailrec fun sumTail(list: List<Int>, acc: Int): Int =
        if (list.isEmpty()) // terminal condition, no more elements to sum
            acc
        else
            sumTail(
                list.tail(), // reduce the list towards the terminal condition
                acc + list.head() // add to the accumulator each recursion
            )
    return sumTail(list, 0)
}

// 2 power n calls...
fun fibonacci1(number: Int): Int =
    if (number == 0 || number == 1) 1
    else fibonacci1(number - 1) + fibonacci1(number - 2)


fun fib(x: Int): BigInteger {
    // something that is doubly recursive needs 2 accumulators
    tailrec fun fibTail(acc1: BigInteger, acc2: BigInteger, number: BigInteger): BigInteger = when {
        (number == ZERO) -> ONE // terminal condition 1
        (number == ONE) -> acc1 + acc2 // terminal condition 2, if you have "reached" 1, add the accumulators
        else -> fibTail(
            acc2, // the previous. This was not immediately obvious
            acc1 + acc2, // the next value in the sequence. This was not immediately obvious
            number - ONE // reduce number towards the terminal condition
        ) // tail recurse
    }
    return fibTail(
        acc1 = ZERO,
        acc2 = ONE, // having 0, 1 at the start gives the 1,1 sequence we are looking for
        number = valueOf(x.toLong() // having number as a BigInteger is a little overkill
        ))
}

fun main() {
    println(add(1_000_000_000, 1_000_000_000))
    (0 until 1_000).forEach { print("${fib(it)} ") }
}