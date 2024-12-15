package basic.a_variable_and_type_operator.lec03

import basic.lec03.Person

fun main() {
    // 실수변환
    run {
        val number1 = 3
        val number2 = 5
        val result = number1 / number2.toDouble()

        println(result)
    }

    // nullable 변수처리
    run {
        val number1: Int? = 3
        val number2: Long = number1?.toLong() ?: 0L
        println(number2)
    }

    // person
    run {
        val person: Person = Person("bok", 20)
        printAgeIfPerson(person)

        printAgeNotIfPerson(person)
        printAgeNotIfPerson(Any())
        printAgeIfNullablePerson(null)
        printAgeIfNullablePerson(person)
    }

    // String interpolation
    run {
        val person: Person = Person("bok", 20)
        println("이름은 ${person.name}, 나이는 ${person.age}세 입니다")
    }

    // multi-line
    run {
        val name = "이승복"
        val str = """
            ABC
            DEF
            $name
        """.trimIndent()
        println(str)
    }

    // String indexing
    run {
        val str = "ABCDEF"
        val a = str[0]
        println(a)
        println(str[str.length - 1])
    }
}

fun printAgeIfPerson(obj: Any) {
    if (obj is Person) {
        println(obj.age)
    }
}

fun printAgeNotIfPerson(obj: Any) {
    if (obj !is Person) {
        println("사람이 아님")
    }
}

fun printAgeIfNullablePerson(obj: Any?) {
    val person = obj as? Person
    println(person?.age)
}

fun unit(): Unit {

}

fun nothing(): Nothing {
    throw RuntimeException()
}

