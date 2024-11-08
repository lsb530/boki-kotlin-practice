package coroutine.util

fun printWithThread(action: () -> Any?) {
    val result = action()
    println("[${Thread.currentThread().name}] $result")
}

suspend fun printSuspendWithThread(action: suspend () -> Any) {
    val result = action()
    println("[${Thread.currentThread().name}] $result")
}