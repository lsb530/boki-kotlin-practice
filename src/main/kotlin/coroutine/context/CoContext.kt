package coroutine.context

import coroutine.util.printWithThread
import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
//    CoroutineName("나만의 코틀린") + SupervisorJob()
//    CoroutineName("나만의 코루틴") + Dispatchers.Default
    asCoroutineDispatcherEx()
}

fun scopeEx() = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread { "Job 1" }
    coroutineContext + CoroutineName("이름")
    coroutineContext.minusKey(CoroutineName.Key)
}

fun asCoroutineDispatcherEx() = runBlocking {
    val threadPool = Executors.newSingleThreadExecutor()
    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        printWithThread { "새로운 코루틴" }
    }
}