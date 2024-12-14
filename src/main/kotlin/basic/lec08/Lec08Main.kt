package basic.lec08

fun main() {
    repeat("Hello World")
    // repeat("Hello World", useNewLine = false)

    val person = Person.PersonBuilder()
        .setName("Bok")
        .setGender("Man")
        .build()

    println(person)

    printNameAndGender("남", "이승복")
    printNameAndGender(name = "이승복", gender = "남")

    // Java의 함수를 Kotlin에서 호출시에는 named argument 불가능
    // Lec08Main.repeat(str = "Hello World", num = 3, useNewLine = false)

    printAll("A", "B", "C", "D")
    val array = arrayOf("A", "B", "C", "D")
    printAll(*array)
}

fun max(a: Int, b: Int) = if (a > b) a else b

fun repeat(str: String, num: Int = 3, useNewLine: Boolean = true) {
    for (i in 1..num) {
        if (useNewLine) println(str)
        else print(str)
    }
}

data class Person(val name: String, val gender: String) {
    class PersonBuilder {
        private var name: String? = null
        private var gender: String? = null

        fun setName(name: String): PersonBuilder {
            this.name = name
            return this
        }

        fun setGender(gender: String): PersonBuilder {
            this.gender = gender
            return this
        }

        fun build(): Person {
            return Person(this.name!!, this.gender!!)
        }
    }
}

fun printNameAndGender(name: String, gender: String) {
    print(name)
    println(", $gender")
}

fun printAll(vararg strings: String) {
    strings.forEach {
        print(it)
    }
    println()
}