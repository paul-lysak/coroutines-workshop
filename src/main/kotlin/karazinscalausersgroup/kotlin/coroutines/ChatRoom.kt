package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ChatRoom(roomName: String, msgPerUser: Int, throwException: Boolean, parent: Job) : CoroutineScope {
    private val job = Job(parent)

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatRoom $roomName ended")
        }

        val u1 = ChatUser("${roomName}_user1", msgPerUser, throwException, job)
        val u2 = ChatUser("${roomName}_user2", msgPerUser, throwException, job)
        launch {
            u1.join()
            u2.join()
            job.cancel()
        }
    }

    suspend fun join() {
        job.join()
    }

}