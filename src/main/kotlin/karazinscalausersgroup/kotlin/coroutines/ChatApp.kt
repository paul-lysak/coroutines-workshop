package karazinscalausersgroup.kotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ChatApp: CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    init {
        job.invokeOnCompletion {
            println("ChatApp ended")
        }

        ChatRoom("room1")
    }

    suspend fun join() {
        job.join()
    }
}