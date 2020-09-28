package io.flyingnimbus

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


internal class CoRoutinesKtTest{

    @Test
    fun test(){
        println("a test")
    }

    @Test
    fun testCoroutine() = runBlocking {
        delay(1000L)
        println("a test")
    }
}