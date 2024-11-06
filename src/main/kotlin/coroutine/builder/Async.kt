package coroutine.builder

import coroutine.util.printWithThread
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async { }
 * 결과 반환 O. Deferred로 제어 가능
 * ------------------------
 */
fun main(): Unit = runBlocking {
//    asyncExample()
//    asyncExample2()
//    asyncExample3()
//    asyncExample4()
    asyncExample5()
}

fun asyncExample() = runBlocking {
    val job = async {
        3 + 5
    }
    val result = job.await()
    printWithThread { result }
}

fun asyncExample2() = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2() }
        val result = job1.await() + job2.await()
        printWithThread { "$result" }
    }
    printWithThread { "소요 시간: $time ms" }
}

suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_000L)
    return 2
}

fun asyncExample3() = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2(job1.await()) }
        val result = job2.await()
        printWithThread { "$result" }
    }
    printWithThread { "소요 시간: $time ms" }
}

suspend fun apiCall2(num : Int): Int {
    delay(1_000L)
    return num + 2
}

fun asyncExample4() = runBlocking {
    val time = measureTimeMillis {
        val job1 = async(start = CoroutineStart.LAZY) { apiCall1() }
        val job2 = async(start = CoroutineStart.LAZY) { apiCall2() }
        val result = job1.await() + job2.await()
        printWithThread { "$result" }
    }
    printWithThread { "소요 시간: $time ms" }
}

fun asyncExample5() = runBlocking {
    val time = measureTimeMillis {
        val job1 = async(start = CoroutineStart.LAZY) { apiCall1() }
        val job2 = async(start = CoroutineStart.LAZY) { apiCall2() }

        job1.start()
        job2.start()
        val result = job1.await() + job2.await()
        printWithThread { "$result" }
    }
    printWithThread { "소요 시간: $time ms" }
}