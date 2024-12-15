package basic.d_fp.lec15

fun main() {

}

fun arrayEx() {
    val array = arrayOf(100, 200)

    for (i in array.indices) {
        println("$i, ${array[i]}")
    }

    val newArray = array.plus(300)

    for ((index, value) in newArray.withIndex()) {
        println("$index, $value")
    }
}

fun collectionEx() {
    val numbers = listOf(100, 200)
    val emptyList = emptyList<Int>()
    printNumbers(emptyList())

    println(numbers[0])

    for (number in numbers) {
        println(number)
    }

    for ((idx, num) in numbers.withIndex()) {
        println("$idx, $num")
    }
}

fun mutableEx() {
    val numbers = mutableListOf(100, 200)
    numbers.add(300)
    println(numbers[1])

    val set = mutableSetOf(100, 200)

    mapOf(1 to "MONDAY", 2 to "TUESDAY")

    val oldMap = mutableMapOf<Int, String>()
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"

    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((key, value) in oldMap) { // oldMap.entries와 동일
        println(key)
        println(value)
    }
}

private fun printNumbers(numbers: List<Int>) { // 타입추론

}
