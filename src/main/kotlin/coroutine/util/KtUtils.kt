package coroutine.util

fun printWithThread(action: () -> Any) {
    val result = action()
    println("[${Thread.currentThread().name}] $result")
}