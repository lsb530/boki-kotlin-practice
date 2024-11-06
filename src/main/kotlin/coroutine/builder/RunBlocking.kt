package coroutine.builder

import coroutine.util.printWithThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        printWithThread { "START" }
        launch {
            delay(2_000)
            printWithThread { "LAUNCH END" }
        }
    }
    printWithThread { "END" }
}