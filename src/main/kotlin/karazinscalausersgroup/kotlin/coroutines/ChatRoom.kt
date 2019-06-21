package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ChatRoom(roomName: String) : CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatRoom $roomName ended")
        }

        launch {
            ChatUser("${roomName}_user1", 5)
            ChatUser("${roomName}_user2", 5)
        }
    }

}