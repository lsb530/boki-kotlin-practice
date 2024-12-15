package basic.e_etc_and_scope.lec20

import java.util.Properties

data class Person(val name: String, var age: Int, var hobby: String = "개발공부") {
    fun growOld() {
        this.age += 1
    }
    fun isAdult(): Boolean {
        return age >= 20
    }
}

data class PersonDto(val name: String, var age: Int, var hobby: String)

/*
    < Scope Func >
    람다의 결과:  let  run   (Ext func O)
    객체 그 자체: also apply (Ext func O)
               with       (Ext func X)
 */

fun main() {
    fun printPerson(person: Person?) {
        person?.let {
            println(it.name)
            println(it.age)
        }
    }

    printPerson(null)
    printPerson(Person("이승복", 30))

    val person = Person("이승복", 30)
    val value1 = person.let {
        it.age
    }
    val value2 = person.run {
        this.age
    }
    val value3 = person.also {
        it.age
    }
    val value4 = person.apply {
        this.age
    }
    with(person) {
        println(this.name)
        println(age) // this 생략 가능
    }

    // letEx()
    // runEx()
    // applyEx()
    // alsoEx()
    // withEx()
}

/* Examples */
fun letEx() {
    // 하나 이상의 함수를 call chain 결과로 호출할 때
    listOf("APPLE", "BANANA", "CAR")
        .map { it.length }
        .filter { it > 3 }
        // .let(::println)
        .let { lengths -> println(lengths) }

    // non-null 값에 대해서만 code block을 실행시킬 때(best)
    var str: String? = null
    str = "Hello World"
    val length = str?.let {
        println(it.uppercase())
        it.length
    }

    // 일회성으로 제한된 영역에 지역변수를 만들 때
    val numbers = listOf("one", "two", "three", "four")
    val modifiedFirstItem = numbers.first()
        .let { firstItem ->
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
        }.uppercase()
    println(modifiedFirstItem)
}

fun runEx() {
    class PersonRepository {
        fun save(person: Person) {
            println("저장")
        }
    }

    // 객체 초기화와 반환값의 계산을 동시에 해야할 때(객체생성->DB저장->인스턴스활용)
    val personRepository = PersonRepository()
    val person = Person("이승복", 20)
        .run(personRepository::save)

    val person2 = Person("이승복", 20)
        .run {
            hobby = "농구"
            personRepository.save(this)
        }
    // ㄴ개인적으로는 잘 사용하지 않음. 아래의 방식 선호
    val person3 = personRepository.save(
        Person("이승복", 20)
    )
}

fun applyEx() {
    // Test Fixture를 만들 때
    fun createPerson(
        name: String,
        age: Int,
        hobby: String,
    ): Person {
        return Person(
            name = name,
            age = age,
        ).apply {
            // 회원가입 이후에 hobby가 존재하는 경우를 테스트
            this.hobby = hobby
        }
    }

    val person = Person("이승복", 30)
    person.apply {
        this.growOld()
    }.let { println(it) }
}

fun alsoEx() {
    // 객체를 수정하는 로직이 call chain 중간에 필요할 때
    mutableListOf("one", "two", "three")
        .also { println("four 추가 전의 값: $it") }
        .add("four")
}

fun withEx() {
    /*
        특정 객체를 다른 객체로 변환해야 하는 상황
        모듈간 의존성을 위해 정적 팩토리 or toClass 함수를 만들기 어려울 때
    */
    fun createPerson(person: Person) {
        return with(person) {
            PersonDto(
                name = name,
                age = age,
                hobby = hobby,
            )
        }
    }
}

fun readableVersus(person: Person?) {
    class View {
        fun showPerson(person: Person) {
            println("이름: ${person.name}, 나이:${person.age}, 취미:${person.hobby}")
        }
        fun showError() {
            println("에러가 발생했습니다 :X")
        }
    }
    val view = View()

    // 1번 코드 ✅
    if (person != null && person.isAdult()) {
        view.showPerson(person)
    }
    else {
        view.showError()
    }

    // 2번 코드
    person?.takeIf { it.isAdult() }
        ?.let { view::showPerson }
        ?: view.showError()
    // 버그 가능성
    // => view::showPerson에서 null을 반환하게 되면
    // => 맨 끝의 view.showError()까지 실행한다
}