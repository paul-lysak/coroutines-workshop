package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.*
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random


class ChatUser(val userName: String, maxMessages: Int, parent: Job) : CoroutineScope {
    private val job = Job(parent)

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatUser $userName ended")
        }

        launch {
            for (messageI in 0 until maxMessages) {
                println("$userName> ${randomMessage()} - msg $messageI")
                delay(1000)
            }
            throw RuntimeException("User $userName disconnected")
        }
    }


    companion object {
        val messages = listOf("Hello", "bye", "how are you", "wonderful", "no worries")

        fun randomMessage() = messages.get(Random.nextInt(messages.size))
    }
}
