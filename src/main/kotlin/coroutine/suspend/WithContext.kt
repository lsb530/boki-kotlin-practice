package coroutine.suspend

import coroutine.util.printSuspendWithThread
import coroutine.util.printWithThread
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    printWithThread { "START" }
    printSuspendWithThread { calculateResult2() }
    printWithThread { "END" }
}

suspend fun calculateResult2(): Int = withContext(Dispatchers.Default) {
    val num1 = async {
        delay(1_000L)
        10
    }

    val num2 = async {
        delay(1_000L)
        20
    }

    num1.await() + num2.await()
}
