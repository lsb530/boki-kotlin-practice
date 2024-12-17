package expert.c_function

/**
 * SAM: Single Abstract Method
 */
fun main() {
    // 익명클래스
    val javaFilter1: StringFilter = object : StringFilter {
        override fun predicate(str: String): Boolean {
            return str.startsWith("A")
        }
    }

    // 오류 발생
    /*
    val javaFilter2: StringFilter = { str ->
        str.startsWith("A")
    }
    */

    // SAM 생성자
    val javaFilter3 = StringFilter { str ->
        str.startsWith("A")
    }

    // 변수가 아닌 파라미터에는 람다식 사용 가능
    consumeFilter({ str -> str.startsWith("A") })
    consumeFilter { str -> str.startsWith("A") }

    // consumeFilter라는 메서드가 2개일 경우에는 SAM 생성자 사용해서 명확하게 호출
    consumeFilter(StringFilter { str -> str.startsWith("A") })
    consumeFilter(Filter<String> { str -> str.startsWith("A") })
    consumeFilter(Filter { str: String -> str.startsWith("A") })

    // { str: String -> str.startsWith("A") }
    KStringFilter { it.startsWith("A") } // SAM constructor

    // 변수에 함수를 할당하는 방법 => 1: 람다식, 2: 익명함수
    val add1 = { a: Int, b: Int -> a + b }
    val add2 = fun(a: Int, b: Int) = a + b

    // 실제로 있는 함수를 변수로 할당할 경우에 메소드참조(메소드 레퍼런스) 사용
    // 용어: Callable Reference

    // method ref
    val add3 = ::add
    // 생성자에 대한 callable ref
    val dogConstructorRef = ::Dog
    // 프로퍼티에 대한 callable ref
    val propertyRef = Dog::name.getter

    // 인스턴스화된 클래스
    val dog = Dog(name = "마루", color = "white")
    // binding된 호출 가능 참조
    val bindingNameGetter = dog::color.getter
    println(bindingNameGetter) // String

    // 확장함수
    val plus = Int::addOne
    println(plus) // fun(Int)

    /* Method Reference
       ㄴReturn Type: Reflection Object
     */
    println(StringFilter::predicate)
    println(KStringFilter::predicate)
    println(Lec16Java::getHello)
}

fun consumeFilter(filter: StringFilter) {}

fun <T> consumeFilter(filter: Filter<T>) {}

// Kotlin's SAM interface
fun interface KStringFilter {
    fun predicate(str: String): Boolean
}

fun add(a: Int, b: Int) = a + b

class Dog(
    val name: String,
    val color: String,
)

fun Int.addOne(): Int = this + 1
