package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.*
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random


class ChatUser(val userName: String, maxMessages: Int, throwException: Boolean, parent: Job) : CoroutineScope {
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
            if (throwException)
                throw RuntimeException("User $userName disconnected")
            else
                job.cancel()
        }
    }

    suspend fun join() {
        job.join()
    }


    companion object {
        val messages = listOf("Hello", "bye", "how are you", "wonderful", "no worries")

        fun randomMessage() = messages.get(Random.nextInt(messages.size))
    }
}
