/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

val messages = listOf("Hello", "bye", "how are you", "wonderful", "no worries")

fun randomMessage() = messages.get(Random.nextInt(messages.size))

suspend fun createChatUser(name: String, maxMessages: Int) {
    for (messageI in 0 until maxMessages) {
        println("$name> ${randomMessage()}")
        delay(1000)
    }
}

fun main(args: Array<String>) {
    runBlocking {
        async { createChatUser("user1", 10) }
        async { createChatUser("user2", 10) }
    }
}
