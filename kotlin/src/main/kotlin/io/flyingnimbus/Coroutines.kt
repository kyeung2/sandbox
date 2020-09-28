package io.flyingnimbus

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking


/*
Not required a perfectly good example project exists from Jet Brains with all the examples I want.

https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels/01_Introduction

https://www.youtube.com/watch?v=_hfBv0a09Jc

 */

fun main() {



    val it = fibonacci.iterator()

    println(it.next())
    println(it.next())
    println(it.next())
    println(it.next())
    println(it.next())
    println(it.next())
}

val fibonacci = sequence<Int> {

    var cur = 1
    var next = 1
    while (true) {
        yield(cur)
        val tmp = cur + next
        cur = next
        next = tmp
        println("inside")
    }
}