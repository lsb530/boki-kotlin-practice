package basic.e_etc_and_scope.lec19

// as import
import basic.e_etc_and_scope.lec19.a.printHelloWorld as printHelloWorldA
import basic.e_etc_and_scope.lec19.b.printHelloWorld as printHelloWorldB

fun main() {
    printHelloWorldA()
    printHelloWorldB()

    val person = Person("이승복", 20)

    // val (name, age) = person
    val name = person.component1()
    val age = person.component2()
    println("이름: $name, 나이: $age")

    // val person1 = NotDataPerson("이승복", 20)
    val (name1, age1) = NotDataPerson("보키", 30)
    println("이름: $name1, 나이: $age1")

    val numbers = listOf(1, 2, 3)
    numbers.map { it + 1 }
        .forEach { println(it) }

    for (number in numbers) {
        if (number == 2) {
            break
        }
    }

    // as-is: for-each with break/continue 안됨
    /*
    numbers.forEach { number ->
        if (number == 2) {
            break // error
        }
        println(number)
    }
    numbers.forEach { number ->
        if (number == 3) {
            continue // error
        }
        println(number)
    }
    */

    // to-be: run/return@run으로 해결(label return)
    run {
        println("return@run")
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach { number ->
            if (number %2 == 0) {
                return@run // break
            }
            println(number)
        }
    }

    run {
        println("return@forEach")
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach { number ->
            if (number %2 == 0) {
                return@forEach // continue
            }
            println(number)
        }
    }

    // label
    println("no label")
    for (i in 1..10) {
        for (j in 1..10) {
            if (j == 2) {
                break
            }
            print("i=$i j=$j, ")
        }
    }

    println()
    println("label&jump")
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            if (j == 2) {
                break@loop
            }
            print("i=$i j=$j, ")
        }
    }
}

/* Type Alias */
data class Fruit(val name: String)

typealias FruitFilter = (Fruit) -> Boolean

fun filterFruits(fruits: List<Fruit>, filter: FruitFilter) {

}

data class UltraSuperGuardianTribe(val name: String)

typealias USGTMap = Map<String, UltraSuperGuardianTribe>

data class Person(
    val name: String,
    val age: Int
)

class NotDataPerson(val name: String, val age: Int) {
    operator fun component1() = name
    operator fun component2() = age
}

fun getNumberOrNull(number: Int): Int? {
    return if (number <= 0)
        null
    else
        number
}

fun getNumberOrNullV2(number: Int): Int? {
    return number.takeIf { it > 0 }
}

fun getNumberOrNullV3(number: Int): Int? {
    return number.takeUnless { it <= 0 }
}