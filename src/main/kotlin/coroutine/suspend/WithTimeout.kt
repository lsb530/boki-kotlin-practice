package coroutine.suspend

import coroutine.util.printWithThread
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    runCatching {
        // TimeoutCancellationException
        withTimeout(1_000L) {
            delay(1_500L)
            10 + 20
        }
    }.exceptionOrNull()?.printStackTrace()

    runCatching {
        // 정상실행
        val result = withTimeout(1_000L) {
            delay(500L)
            10 + 20
        }
        printWithThread { result }
    }.exceptionOrNull()?.printStackTrace()

    runCatching {
        // 예외발생시 null 반환
        val result: Int? = withTimeoutOrNull(1_000L) {
            delay(1_500L)
            10 + 20
        }
        printWithThread { result }
    }.exceptionOrNull()?.printStackTrace()

}