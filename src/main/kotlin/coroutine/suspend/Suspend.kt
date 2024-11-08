package coroutine.suspend

import coroutine.util.printSuspendWithThread
import coroutine.util.printWithThread
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

fun main(): Unit = runBlocking {
//    ex1()
//    ex2()
    ex3()
}

/**
 * suspend 중단될 수 있는(아래에서는 중단되지 않음)
 */
fun ex1(): Unit = runBlocking {
    launch {
        a()
        b()
    }

    launch {
        c()
    }
}

suspend fun a() = printWithThread { "A" }
suspend fun b() = printWithThread { "B" }
suspend fun c() = printWithThread { "C" }

/**
 * 만약 다른 자바 라이브러리를 사용하고자 한다면 제약이 있다
 */
fun ex2(): Unit = runBlocking {
    val result1: Deferred<Int> = async {
        call1()
    }

    val result2 = async {
        call2(result1.await())
    }

    printSuspendWithThread { result2.await() }
}

fun call1(): Int {
    Thread.sleep(1_000L)
    return 100
}

fun call2(num: Int): Int {
    Thread.sleep(1_000L)
    return num * 2
}

fun ex3(): Unit = runBlocking {
    val result1 = call3()

    val result2 = call4(result1)

    printSuspendWithThread { result2 }
}

/**
 * call3은 코틀린, call4는 자바로 사용
 */
suspend fun call3(): Int {
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        return@async 100
    }.await()
}

suspend fun call4(num: Int): Int {
    return CompletableFuture.supplyAsync {
        Thread.sleep(1_000L)
        return@supplyAsync num * 2
    }.await()
}

interface AsyncCaller {
    suspend fun call()
}

class AsyncCallerImpl : AsyncCaller {
    // 구현체마다 다른 라이브러리 사용
    override suspend fun call() {
        TODO("Not yet implemented")
    }
}