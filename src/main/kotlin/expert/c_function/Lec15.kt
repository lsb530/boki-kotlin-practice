package expert.c_function

fun main() {
    // main함수 내부에 exec 본문까지 inline
    repeatV0(2) { println("Hello World") }

    // main함수 내부에 exec 본문을 제외하고 inline
    repeatV1(2) { println("Hello World") }

    iterateV0(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
            // return // 불가!
        }
        println(num)
    }

    println("iterateV1 시작")
    iterateV1(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
            return // 가능, 하지만 main 함수를 리턴함
        }
        println(num)
    }
    println("iterateV1 끝")

    iterateV2(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
            // return // crossline 사용으로 불가!
        }
        println(num)
    }
}

// exec 함수에 어떤 것이 들어갈지 알수없게 변경된 경우 exec까지 본문이 main에 inline되지 않음
fun noMain(exec: () -> Unit) {
    // exec.invoke();
    repeatV0(2, exec)
}

// inline함수 + 고차함수
inline fun repeatV0(
    times: Int, exec: () -> Unit,
) {
    for (i in 1..times) {
        exec()
    }
}

// inline함수 + 고차함수 with noinline
inline fun repeatV1(
    times: Int,
    noinline exec: () -> Unit,
) {
    for (i in 1..times) {
        exec()
    }
}

// 일반함수: non-local return을 사용할 수 없음
fun iterateV0(numbers: List<Int>, exec: (Int) -> Unit) {
    for (num in numbers) {
        exec(num)
    }
}

// inline함수: non-local return을 사용할 수 있게 해줌
inline fun iterateV1(numbers: List<Int>, exec: (Int) -> Unit) {
    for (num in numbers) {
        exec(num)
    }
}

// inline함수 + crossline파라미터: non-local return 금지
inline fun iterateV2(numbers: List<Int>, crossinline exec: (Int) -> Unit) {
    for (num in numbers) {
        exec(num)
    }
}

// inline 프로퍼티
class InLinePropertyPerson(val name: String) {
    inline val uppercaseName: String
        get() = this.name.uppercase() // 가능
    // get() = field.uppercase() // 불가능
}
