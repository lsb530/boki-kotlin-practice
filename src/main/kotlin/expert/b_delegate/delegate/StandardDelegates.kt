package expert.b_delegate.delegate

import kotlin.properties.Delegates

fun main() {
    val person1 = NotNullAgePerson()
    // println(person1.age) // IllegalStateException

    val person2 = ObservablePerson()
    println(person2.age)
    person2.age = 30
    person2.age = 30 // 주의(디폴트는 old/new 값에 상관 없이 onChange 함수가 호출됨)

    val person3 = VetoablePerson()
    println(person3.age) // 20
    person3.age = -10
    println(person3.age) // 20
    person3.age = 40
    println(person3.age) // 40

    val person4 = OtherPropertyDelegatePerson()
    println(person4.num) // warning message

    val person5 = MapPerson(mapOf("name" to "ABC"))
    println(person5.name)
    println(person5.age) // 예외 발생
}

class NotNullAgePerson {
    var age: Int by Delegates.notNull()
}

class ObservablePerson {
    var age: Int by Delegates.observable(20) { _, oldValue, newValue ->
        // println("옛날 값: $oldValue -> 새로운 값: $newValue")
        if (oldValue != newValue) {
            println("옛날 값: $oldValue -> 새로운 값: $newValue")
        }
    }
}

class VetoablePerson {
    var age: Int by Delegates.vetoable(20) {
        _, _, newValue -> newValue >= 1
    }
}

class OtherPropertyDelegatePerson {
    @Deprecated("age를 사용하세요!", ReplaceWith("age"))
    var num: Int = 0

    var age: Int by this::num
}

class MapPerson(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
}

class MutableMapPerson(map: MutableMap<String, Any>) {
    var name: String by map
    var age: Int by map
}
