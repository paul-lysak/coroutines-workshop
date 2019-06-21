package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ChatRoom(roomName: String, parent: Job) : CoroutineScope {
    private val job = Job(parent)

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatRoom $roomName ended")
        }

        launch {
            ChatUser("${roomName}_user1", 5, job)
            ChatUser("${roomName}_user2", 5, job)
        }
    }

}