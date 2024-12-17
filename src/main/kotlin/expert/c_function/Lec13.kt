package expert.c_function

fun main() {
    // 람다식
    compute(5, 3, { a, b -> a + b })
    compute(5, 3) { a, b -> a + b }

    // 익명함수
    compute(5, 3, fun(a, b): Int {
        return a + b
    })
    compute(5, 3, fun(a, b) = a + b)

    // 익명함수
    iterate(listOf(1, 2, 3, 4, 5), fun(num) {
        if (num == 3) {
            return
        }
        println(num)
    })

    // 람다1: with return
    iterate(listOf(1, 2, 3, 4, 5)) {
        if (it == 3) {
            // return // non-local return(비지역적 반환)
            return@iterate
        }
        println(it)
    }
    // 람다2: without return
    iterate(listOf(1, 2, 3, 4, 5)) { num ->
        if (num != 3) {
            println(num)
        }
    }

    println("ABC")

    val result1 = calculateV2(3, 4, OperatorV2.PLUS)
    println(result1)

    val result2 = OperatorV2.PLUS(3, 4)
    println(result2)
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

// default parameter with 함수타입 파라미터
fun compute2(
    num1: Int,
    num2: Int,
    op: (Int, Int) -> Int = fun(a, b) = a + b
): Int {
    return op(num1, num2)
}

fun calculateV0(num1: Int, num2: Int, op: Char): Int {
    return when (op) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> {
            if (num2 == 0)
                throw ArithmeticException("0으로 나눌 수 없습니다")
            else
                num1 / num2
        }

        else -> throw IllegalArgumentException("올바르지 않은 연산자(${op})입니다!")
    }
}

fun calculateV1(num1: Int, num2: Int, operator: OperatorV1): Int = operator.calcFun(num1, num2)

enum class OperatorV1(
    private val op: Char,
    val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('-', { a, b -> a * b }),
    DIVIDE('-', { a, b ->
        if (b == 0)
            throw ArithmeticException("0으로 나눌 수 없습니다")
        else
            a / b
    }),
    ;

    operator fun invoke(num1: Int, num2: Int): Int {
        return this.calcFun(num1, num2)
    }

}

fun calculateV2(num1: Int, num2: Int, operator: OperatorV2): Int = operator(num1, num2)

enum class OperatorV2(
    private val op: Char,
    private val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('-', { a, b -> a * b }),
    DIVIDE('-', { a, b ->
        if (b == 0)
            throw ArithmeticException("0으로 나눌 수 없습니다")
        else
            a / b
    }),
    ;

    operator fun invoke(num1: Int, num2: Int): Int {
        return this.calcFun(num1, num2)
    }

}
