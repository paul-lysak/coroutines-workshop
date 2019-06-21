/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger


suspend fun produceMessages(counter: AtomicInteger) {
    for (messageI in 0 until 5) {
        counter.incrementAndGet()
        delay(1000)
    }
}

fun main(args: Array<String>) {
    val messagesCount = AtomicInteger(0)
    runBlocking {
        for (coroutineI in 0 until 100_000) {
            launch {
                produceMessages(messagesCount)
            }
        }
    }
    println("Messages count: $messagesCount")
}
