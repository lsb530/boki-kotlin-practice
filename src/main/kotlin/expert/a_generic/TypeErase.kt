package expert.a_generic

fun main() {
    val num = 3
    num.toSuperString() // "Int: 3"

    val str = "ABC"
    println("${str::class.java.name}: $str")
    str.toSuperString() // "String: ABC"

    val numbers = listOf(1, 2f, 3.0)
    numbers.filterIsInstance<Float>() // [2f] // inline + reified

    val cage = CageShadow<GoldFish>()
    cage.addAnimal(GoldFish("금붕어"))
    cage.addAnimal(Carp("잉어"))
}

fun checkStringList(data: Any) {
    // if (data is List<String>) { // Cannot check for instance of erased type: List<String>

    // }

    // star projection
    if (data is List<*>) {
        val element: Any? = data[0]
    }

    // if (data is MutableList<*>) {
    //     data.add(3); // 타입소거때문에 추가 불가
    // }
}

fun checkMutableList(data: Collection<String>) {
    if (data is MutableList<String>) {
        data.add("Hello World")
    }
}

fun <T> T.invalidToSuperString() {
    // println("${T::class.java.name}: $this") // error(Runtime때 T에 대해 무엇인지 알 수 없다)
}

inline fun <reified T> T.toSuperString() {
    println("${T::class.java.name}: $this")
}

// 각 타입별로 만드는 방법
fun List<*>.hasAnyInstanceOfString(): Boolean {
    return this.any { it is String }
}
fun List<*>.hasAnyInstanceOfInt(): Boolean {
    return this.any { it is Int }
}

// inline(코드의 본문을 호출 지점으로 이동시켜 컴파일) + reified
// reified 키워드 한계: T의 인스턴스를 만들거나 T의 companion object를 가져올 수 없다
inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}

class TypeErase<T, R, B> {
}

// Type Parameter Shadowing
class CageShadow<T : Animal> {
    fun <T : Animal> addAnimal(animal: T) {

    }
}

class OtherCage<T : Animal> {
    fun <R: Animal> addAnimal(animal: R) {

    }
}

open class CageV1<T : Animal> {
    open fun addAnimal(animal: T) {

    }
}

class CageV2<T : Animal> : CageV1<T>() {
    override fun addAnimal(animal: T) {
        super.addAnimal(animal)
    }
}

class GoldFishCageV2 : CageV1<GoldFish>() {
    override fun addAnimal(animal: GoldFish) {
        super.addAnimal(animal)
    }
}
