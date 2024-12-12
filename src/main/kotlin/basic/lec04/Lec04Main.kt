package basic.lec04

fun main() {
    // >, < 연산자의 compareTo 자동 호출
    run {
        val money1 = JavaMoney(2000)
        val money2 = JavaMoney(1000)

        if (money1 > money2) {
            println("${money1}이 ${money2}보다 금액이 크다")
        }
    }

    // 동등성, 동일성
    run {
        val money1 = JavaMoney(1000)
        val money2 = money1
        val money3 = JavaMoney(1000)

        println(money1 == money2)
        println(money1 === money2)
        println(money1 === money3)
    }

    // Lazy 연산
    run {
        if (getTrue() || getFalse()) {
            println("본문 실행1")
        }

        if (getFalse() && getTrue()) {
            println("본문 실행2")
        }
    }

    // 연산자 오버로딩
    run {
        val money1 = KotlinMoney(1000)
        val money2 = KotlinMoney(2000)
        println(money1 + money2)
    }
}

fun getTrue(): Boolean {
    println("getTrue")
    return true
}

fun getFalse(): Boolean {
    println("getFalse")
    return false
}

data class KotlinMoney(val amount: Int) {
    operator fun plus(other: KotlinMoney): KotlinMoney = KotlinMoney(this.amount + other.amount)
}