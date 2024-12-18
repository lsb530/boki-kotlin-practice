package expert.e_reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.cast
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation

// JVM
fun main() { // KClass<T>: Kotlin's Reflection, Class<T>: Java's
    val kClass: KClass<Reflection> = Reflection::class

    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class

    val kClass3: KClass<out Any> = Class.forName("expert.e_reflection.Reflection").kotlin
    kClass.java // Class<Reflection>
    kClass.java.kotlin // KClass<Reflection>

    // KType: 타입 표현(ex: Int?)
    val kType = GoldFish::class.createType()

    // Reflection Function call
    val goldFish = GoldFish("금붕이")
    goldFish::class.members.firstOrNull { it.name == "print" }?.call(goldFish)

    executeAll(Reflection())

    val kFunction2 = ::add // Reflection 객체
}

@Target(AnnotationTarget.CLASS)
annotation class Executable

@Executable
class Reflection {
    fun a() {
        println("A입니다")
    }

    fun b(n: Int) {
        println("B입니다")
    }
}

class GoldFish(val name: String) {
    fun print() {
        println("금붕어 이름은 ${name}입니다")
    }
}
fun castToGoldFish1(obj: Any): GoldFish = obj as GoldFish
fun castToGoldFish2(obj: Any): GoldFish = GoldFish::class.cast(obj)

fun executeAll(obj: Any) {
    val kClass = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }

    callableFunctions.forEach { function ->
        // function.call(kClass.createInstance())
        function.call(obj)
    }
}

fun add(a: Int, b: Int) = a + b
