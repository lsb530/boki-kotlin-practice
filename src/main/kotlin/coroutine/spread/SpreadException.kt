package coroutine.spread

import coroutine.util.printWithThread
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

fun main(): Unit = runBlocking {
//    ex1()
//    ex2()
//    ex3()
    ex4()
}

fun ex1() = runBlocking {
    launch {
        delay(500L)
        printWithThread { "A" }
    }

    launch {
        delay(600L)
        throw IllegalArgumentException("코루틴 실패!")
    }
}

fun ex2() = runBlocking {
    launch {
        delay(600L)
        printWithThread { "A" }
    }

    launch {
        delay(500L)
        throw IllegalArgumentException("코루틴 실패!")
    }
}

fun ex3() = runBlocking {
    val job = launch {
        delay(600L)
        printWithThread { "A" }

        launch {
            printWithThread { "B" }
        }
    }
    delay(100L)
    job.cancel()
}

fun ex4() = runBlocking {
    launch {
        delay(600L)
        printWithThread { "A" }
    }

    launch {
        delay(500L)
        printWithThread { "B" }
        throw CancellationException("코루틴 실패!")
    }
}