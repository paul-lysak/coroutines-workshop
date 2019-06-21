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

        val r1 = ChatRoom("room1", 5, true, job)
        val r2 = ChatRoom("room2", 10, false, job)

        launch {
            r1.join()
            r2.join()
            job.cancel()
        }
    }

    suspend fun join() {
        job.join()
    }
}