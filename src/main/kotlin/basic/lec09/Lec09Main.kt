package basic.lec09

fun main() {
    val person = Person("이승복", 20)
    println(person.name)
    person.age = 30
    println(person.age)

    val jPerson = JavaPerson("이승복", 10)
    println(jPerson.name)
    jPerson.age = 20
    println(jPerson.age)

    val p1 = Person("뽁이")
    println(p1.age)

    println(Person().name)
}

class PersonV1 constructor(name: String, age: Int) {
    val name = name
    var age = age
}

class Person(
    val name: String,
    var age: Int
) {
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
        println("초기화 블록")
    }

    constructor(name: String) : this(name, 1) {
        println("부 생성자 1")
    }

    constructor(): this("홍길동") {
        println("부 생성자 2")
    }

    fun isAdult1(): Boolean {
        return this.age >= 20
    }

    val isAdult2: Boolean
        get() {
            return this.age >= 20
        }

    val isAdult3: Boolean
        get() = this.age >= 20

    val uppercaseName: String
        get() = this.name.uppercase()
}

class CustomPerson(
    name: String,
    var age : Int = 1
) {
    // val name = name
    //     get() = field.uppercase()

    var name = name
        set(value) {
            field = value.uppercase()
        }
}