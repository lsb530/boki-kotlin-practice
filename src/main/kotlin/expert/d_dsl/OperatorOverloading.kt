package expert.d_dsl

import java.time.LocalDate

fun main() {
    var point1 = Point(20, -10)
    println(point1.zeroPointSymmetry())
    println(-point1)
    println(point1.increase())
    println(++point1)

    // 2023-01-04
    LocalDate.of(2023, 1, 1).plusDays(3)

    // 2023-01-04
    LocalDate.of(2023, 1, 1) + Days(3)

    // 2023-12-29 <- 조금 문제가 있다~~
    LocalDate.of(2023, 1, 1) + 3.d

    val mutableList = mutableListOf("A", "B", "C")
    mutableList += "D" // plusAssign: this.add(element)
    println(mutableList)

    var immutableList = listOf("A", "B", "C")
    immutableList += "D" // list = list + D
    /*
        val result = ArrayList<T>(size + 1)
        result.addAll(this)
        result.add(element)
        return result
     */
    println(immutableList)

    val list = listOf("A", "B", "C")
    list[2]
    val map = mutableMapOf(1 to "A")
    map[2] = "B"
}

data class Point(val x: Int, val y: Int) {
    fun zeroPointSymmetry() = Point(-x, -y)
    operator fun unaryMinus() = Point(-x, -y)

    fun increase() = Point(x + 1, y + 1)
    operator fun inc() = Point(x + 1, y + 1)
}

data class Days(val day: Long)

operator fun LocalDate.plus(days: Days): LocalDate {
    return this.plusDays(days.day)
}

val Int.d: Days
    get() = Days(this.toLong())



