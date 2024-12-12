package basic.lec05

fun main() {
    println(getPassOrFail(10))
    println(getPassOrFail(60))
    println(getGrade(93))
    val result1 = runCatching {
        validateScore(200)
    }
    if (result1.isFailure) {
        println(result1.exceptionOrNull()?.message)
    }
    val result2 = runCatching {
        validateScoreIsNotNegative(-10)
    }
    if (result2.isFailure) {
        println(result2.exceptionOrNull()?.message)
    }

    println(getGradeWithWhen(72))
    println(getGradeWithWhenV2(80))

    println(startsWithA("ATOM"))
    judgeNumber(2)
    judgeNumber(0)

    judgeNumber2(0)
    judgeNumber2(20)
    judgeNumber2(15)
}

// if is Expression, not Statement
fun getPassOrFail(score: Int): String {
    return if (score >= 50)
        "P"
    else
        "F"
}

fun getGrade(score: Int): String {
    return if (score >= 90) "A"
    else if (score >= 80) "B"
    else if (score >= 70) "C"
    else "F"
}

// rangeTo, until
fun validateScore(score: Int) {
    if (score !in 0..100) {
        throw IllegalArgumentException("Score 범위는 0부터 100입니다")
    }
}

fun validateScoreIsNotNegative(score: Int) {
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }
}

fun getGradeWithWhen(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "F"
    }
}

fun getGradeWithWhenV2(score: Int): String {
    return when (score) {
        in 90..100 -> "A"
        in 80 until 90 -> "B"
        in 70 until 80 -> "C"
        else -> "F"
    }
}

// when & is
fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

private fun judgeNumber(number: Int) {
    when (number) {
        -1, 0, 1 -> println("어디서 많이 본 숫자입니다")
        else -> println("-1, 0, 1이 아닙니다")
    }
}

private fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수입니다")
        else -> println("주어진 숫자는 홀수입니다")
    }
}
