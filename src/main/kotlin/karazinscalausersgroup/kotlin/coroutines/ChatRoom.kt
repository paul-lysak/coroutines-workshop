package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ChatRoom(roomName: String, msgPerUser: Int, throwException: Boolean, parent: Job) : CoroutineScope {
    private val job = Job(parent)

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatRoom $roomName ended")
        }

        val outputChannel = Channel<ChatMessage>()

        val u1 = ChatUser("user1", msgPerUser, throwException, outputChannel, job)
        val u2 = ChatUser("user2", msgPerUser, throwException, outputChannel, job)
        launch {
            u1.join()
            u2.join()
            job.cancel()
        }
        launch {
            for(message in outputChannel)
                println("${message.from}@$roomName> ${message.body}")
        }
    }

    suspend fun join() {
        job.join()
    }
}
