package expert.f_extra

import kotlin.system.measureTimeMillis

// TODO 이걸 해야 한다
fun main() {
    repeat(3) {
        println("Hello World")
    }

    val measureTimeMillis = measureTimeMillis {
        val a = 1
        val b = 2
        val result = a + b
    }
    println(measureTimeMillis)
    acceptOnlyTwo(2)
    // acceptOnlyTwo(3)

    val person = Person()
    person.sleep()
    person.changeStatus()
    // person.sleep()

    val result: Result<Int> = runCatching { 1 / 0 }
    println(result)
    TODO("main 함수 구현")
}

fun acceptOnlyTwo(num: Int) {
    println("num = ${num}")
    require(num == 2) { "2만 허용!" } // IllegalArgumentException
}

data class Person(
    var status: PersonStatus = PersonStatus.PLAYING
) {
    fun sleep() {
        println("sleep status = $status")
        // IllegalStateException
        check(this.status == PersonStatus.PLAYING) { "PLAYING 상태만 sleep호출 가능" }
    }

    fun changeStatus() {
        this.status = when (status) {
            PersonStatus.PLAYING -> PersonStatus.SLEEPING
            PersonStatus.SLEEPING -> PersonStatus.SLEEPING
        }
    }

    enum class PersonStatus {
        PLAYING, SLEEPING
    }
}
