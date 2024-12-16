package expert.b_delegate

class Person {
    // var name: String = "홍길동"
    // var name: String? = null
    lateinit var name: String
    // lateinit var age: Int // Primitive type에 사용 불가

    val isLee: Boolean
        // get() = this.name.startsWith("이")
        // get() = this.name!!.startsWith("이")
        get() = this.name.startsWith("이")

    val maskingName: String
        // get() = name[0] + (1 until name.length).joinToString("") { "*" }
        // get() = name!![0] + (1 until name!!.length).joinToString("") { "*" }
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

// 한번만 초기화가 필요할 때 요구사항을 정확히 만족하지 않는 코드들
class Person2 {
    val name: String
    // get() {
    //     Thread.sleep(2000)
    //     return "이승복"
    // }

    init {
        // Thread.sleep()은 1회 호출
        // but, name을 사용하지 않는 경우에도 sleep이 호출
        Thread.sleep(2000)
        name = "이승복"
    }
}

// 한번만 초기화가 필요할 때 요구사항을 어느정도 만족하는 코드(but, 불편함)
class Person3 {
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2000)
                this._name = "이승복"
            }
            return this._name!!
        }
}

// 한번만 초기화가 필요할 때 요구사항을 정확히 만족하는 코드
// name의 Getter가 최초 호출될때만 실행되고 Thread-safe함
// by lazy: 초기화를 get호출 전으로 지연시킨 변수
class Person4 {
    val name: String by lazy {
        Thread.sleep(2000)
        "이승복"
    }
}
