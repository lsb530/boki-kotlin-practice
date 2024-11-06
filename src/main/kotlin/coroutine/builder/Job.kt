package coroutine.builder

import coroutine.util.printWithThread
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch { }
 * 결과 반환 X. Job으로 제어 가능
 * ------------------------
 * Job
 * start(): 시작
 * cancel(): 취소
 * join(): 코루틴이 완료될때까지 대기
 */
fun main(): Unit = runBlocking {
//    launchExample()
//    cancelExample()
    joinExample()
}

fun launchExample() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread { "Hello launch" }
    }

    delay(1_000L)
    job.start()
}

fun cancelExample() = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread { "$it" }
            delay(500L)
        }
    }

    delay(1_000L)
    job.cancel()
}

fun joinExample() = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread { "Job 1" }
    }

    job1.join()

    val job2 = launch {
        delay(1_000L)
        printWithThread { "Job 2" }
    }
}