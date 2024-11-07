package coroutine.ex

import coroutine.util.printWithThread
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
//    ex1()
//    ex2()
//    ex3()
//    ex4()
//    ex5()
//    ex6()
//    ex7()
//    ex8()
//    ex9()
}

fun ex1(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread { "Job 1" }
    }

    val job2 = launch {
        delay(1_000L)
        printWithThread { "Job 2" }
    }
}

fun ex2(): Unit = runBlocking {
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread { "Job 1" }
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        delay(1_000L)
        printWithThread { "Job 2" }
    }

    job1.join()
    job2.join()
}

fun ex3(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalArgumentException()
    }

    delay(1_000L)
}

fun ex4(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(1_000L)
}

fun ex5(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(1_000L)
    job.await()
}

fun ex6(): Unit = runBlocking {
    val job = async {
        throw IllegalArgumentException()
    }

    delay(1_000L)
}


fun ex7(): Unit = runBlocking {
    val job = async(SupervisorJob()) {
        throw IllegalArgumentException()
    }

    delay(1_000L)
//    job.await()
}

/* 예외를 다루는 방법 */
fun ex8(): Unit = runBlocking {
    val job = launch {
        try {
            throw IllegalArgumentException()
        } catch (e: IllegalArgumentException) {
            printWithThread { "정상 종료" }
        }
    }
}

fun ex9(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        printWithThread { "예외" }
        throw throwable
    }
    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException()
    }
    delay(1_000L)
}