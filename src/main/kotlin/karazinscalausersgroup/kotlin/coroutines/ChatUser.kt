package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random


class ChatUser(val userName: String, maxMessages: Int) : CoroutineScope {
    private val job = Job()

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
            job.cancel()
        }
    }


    companion object {
        val messages = listOf("Hello", "bye", "how are you", "wonderful", "no worries")

        fun randomMessage() = messages.get(Random.nextInt(messages.size))
    }
}
