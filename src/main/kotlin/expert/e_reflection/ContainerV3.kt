package expert.e_reflection

import org.reflections.Reflections
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.cast

object ContainerV3 {
    // 등록한 클래스 보관! = KClass를 보관!
    private val registeredClasses = mutableSetOf<KClass<*>>()
    private val cachedInstances = mutableMapOf<KClass<*>, Any>()

    fun register(clazz: KClass<*>) {
        registeredClasses.add(clazz)
    }

    fun <T : Any> getInstance(type: KClass<T>): T {
        if (type in cachedInstances) {
            return type.cast(cachedInstances[type])
        }

        val instance = registeredClasses.firstOrNull { clazz -> clazz == type }
            ?.let { clazz -> instantiate(clazz) as T }
            ?: throw IllegalArgumentException("해당 인스턴스 타입을 찾을 수 없습니다")
        cachedInstances[type] = instance
        return instance
    }

    private fun <T : Any> instantiate(clazz: KClass<T>): T {
        val constructor = findUsableConstructor(clazz)
        val params = constructor.parameters
            .map { param -> getInstance(param.type.classifier as KClass<*>) }
            .toTypedArray()
        return constructor.call(*params)
    }

    // clazz의 consturcotr들 중, 사용할 수 있는 constructor 찾기
    // consturctor에 넣어야 하는 타입들이 모두 등록된 경우 (컨테이너에서 관리하고 있는 경우)
    private fun <T : Any> findUsableConstructor(clazz: KClass<T>): KFunction<T> {
        return clazz.constructors
            .firstOrNull { constructor -> constructor.parameters.isAllRegistered }
            ?: throw IllegalArgumentException("사용할 수 있는 생성자가 없습니다")
    }

    private val List<KParameter>.isAllRegistered: Boolean
        get() = this.all { it.type.classifier in registeredClasses }
}

annotation class MyClass

@MyClass
class AServiceV3 {
    fun print() {
        println("A Service 입니다")
    }
}

@MyClass
class BServiceV3(
    private val aServiceV3: AServiceV3,
    private val cServiceV3: CServiceV3?
) {

    constructor(aServiceV3: AServiceV3) : this(aServiceV3, null) {
        println("hello")
    }

    fun print() {
        this.aServiceV3.print()
    }
}

class CServiceV3

fun start(clazz: KClass<*>) {
    val reflections = Reflections(clazz.packageName)
    val jClasses = reflections.getTypesAnnotatedWith(MyClass::class.java)
    jClasses.forEach { jClass -> ContainerV3.register(jClass.kotlin) }
}

private val KClass<*>.packageName: String
    get() {
        val qualifiedName = this.qualifiedName
            ?: throw IllegalArgumentException("익명 객체입니다!")
        val hierarchy = qualifiedName.split(".")
        return hierarchy.subList(0, hierarchy.lastIndex).joinToString(".")
    }

class DI

fun main() {
    // val reflections = Reflections("expert.e_reflection")
    // val jClasses = reflections.getTypesAnnotatedWith(MyClass::class.java)
    // println(jClasses)

    start(DI::class)
    val bService = ContainerV3.getInstance(BServiceV3::class)
    bService.print()
}
