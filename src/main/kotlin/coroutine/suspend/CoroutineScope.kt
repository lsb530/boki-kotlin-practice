package coroutine.suspend

import coroutine.util.printSuspendWithThread
import coroutine.util.printWithThread
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    printWithThread { "START" }
    printSuspendWithThread { calculateResult() }
    printWithThread { "END" }
}

suspend fun calculateResult(): Int = coroutineScope {
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

