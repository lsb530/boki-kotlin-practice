package expert.c_function

fun main() {
    // 함수 리터럴 호출
    val add = { a: Int, b: Int -> a + b }
    add.invoke(1, 2)
    add(1, 2)

    val add2 = fun Int.(other: Long): Int = this + other.toInt()
    5.add(3L)
    5.add2(3L)

    // Decompile: 고차함수 -> FuntionN 클래스로 변환
    computeEx(5, 3) { n1, n2 -> n1 + n2 }

    // Decompile: 고차함수 + Closure
    //  -> 코틀린 람다식이 외부 변수를 가리키면 Ref객체로 감싸진다
    var num = 5
    num += 1
    // val plusOne = { num += 1 }
    val plusOne: () -> Unit = { num += 1 }
}

/**
 * 고차함수의 타입
 */
// (Int, Int, (Int, Int) -> Int) -> Int
fun computeEx(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

// () -> (Int, Int) -> Int
fun opGenerator(): (Int, Int) -> Int {
    return { a, b -> a + b }
}

/**
 * 확장함수의 타입: 수신객체가 있는 함수 리터럴
 */
// Int.(Long) -> Int
fun Int.add(other: Long): Int = this + other.toInt()
// Int: 수신객체 타입 / this: 수신객체
