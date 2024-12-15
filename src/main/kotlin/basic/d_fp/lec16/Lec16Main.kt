package basic.d_fp.lec16

import basic.lec16.Person

fun main() {
    val str = "ABCD"
    println(str.lastChar())

    val person = Person("승복", "이", 30)
    println(person.nextYearAge()) // 멤버함수 먼저 호출

    val train = Train()
    train.isExpensive() // Train

    val srt1: Train = Srt()
    srt1.isExpensive() // Train

    val srt2: Srt = Srt()
    srt2.isExpensive() // Srt

    println(3.add(4))
    println(3.add2(4))
    println(3 add2 4)

    println(5.add3(7))

    createPerson("bok", "")
}

// Extended Function
fun String.lastChar(): Char {
    return this[this.length - 1]
}

// Extended Property
val String.lastChar: Char
    get() = this[this.length - 1]

fun Person.nextYearAge(): Int {
    println("확장 함수")
    return this.age + 1
}

open class Train(
    val name: String = "새마을기차",
    val price: Int = 5000
)
fun Train.isExpensive(): Boolean {
    println("Train.isExpensive")
    return this.price >= 10_000
}

class Srt : Train("SRT", 40000)
fun Srt.isExpensive(): Boolean {
    println("Srt.isExpensive")
    return this.price >= 10000
}

/* infix */
fun Int.add(other: Int) = this + other
infix fun Int.add2(other: Int) = this + other

/* inline */
inline fun Int.add3(other: Int) = this + other

/* local */
fun createPerson(firstName: String, lastName: String): Person {
    fun validateName(name: String, fieldName: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("${fieldName}은 비어있을 수 없습니다! 현재 값: ${name}")
        }
    }
    validateName(firstName, "firstName")
    validateName(lastName, "lastName")

    return Person(firstName, lastName, 20)
}