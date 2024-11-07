package coroutine.cancel

import coroutine.util.printWithThread
import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException


fun main(): Unit = runBlocking {
//    cancelEx1()
//    cancelEx2()
//    cancelEx3()
//    cancelEx4()
//    cancelEx5()
//    cancelEx6()
    cancelEx7()
}

fun cancelEx1() = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread { "Job 1" }
    }

    val job2 = launch {
        delay(1_000L)
        printWithThread { "Job 2" }
    }

    delay(100)
    job1.cancel()
}

fun cancelEx2() = runBlocking {
    val job1 = launch {
        delay(10L)
        printWithThread { "Job 1" }
    }

    delay(100)
    job1.cancel()
}

fun cancelEx3() = runBlocking {
    val job = launch {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread { "${i++}번째 출력!" }
                nextPrintTime += 1_000L
            }
        }
    }

    delay(100L)
    job.cancel()
}

fun cancelEx4() = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread { "${i++}번째 출력!" }
                nextPrintTime += 1_000L
            }

            if (!isActive) {
                throw CancellationException()
            }
        }
    }

    delay(100L)
    job.cancel()
}

fun cancelEx5() = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (isActive && i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread { "${i++}번째 출력!" }
                nextPrintTime += 1_000L
            }
        }
    }

    delay(100L)
    job.cancel()
}

fun cancelEx6() = runBlocking {
    val job = launch {
        try {
            delay(1_000L)
        } catch (e: CancellationException) {
            // Nothing
        }
        printWithThread { "delay에 의해 취소되지 않음" }
    }

    delay(100L)
    printWithThread { "취소 시작" }
    job.cancel()
}

fun cancelEx7() = runBlocking {
    val job = launch {
        try {
            delay(1_000L)
        } catch (e: CancellationException) {
            throw e
        }
        printWithThread { "delay에 의해 취소" }
    }

    delay(100L)
    printWithThread { "취소 시작" }
    job.cancel()
}