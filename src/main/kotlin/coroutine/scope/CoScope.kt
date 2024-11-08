package coroutine.scope

import coroutine.util.printWithThread
import kotlinx.coroutines.*

fun main() {
    newRoutine()
    Thread.sleep(1_500L)
}

//suspend fun main() {
//    join()
//}

fun newRoutine() = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread { "Job 1" }
}

suspend fun join() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread { "Job 1" }
    }

    job.join()
}

class AsyncLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun doSomething() {
        scope.launch {
            // 무언가 코루틴이 시작되어 작업
        }
    }

    fun destroy() {
        scope.cancel()
    }
}