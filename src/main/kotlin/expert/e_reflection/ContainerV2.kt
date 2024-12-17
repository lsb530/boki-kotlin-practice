package expert.e_reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.cast

object ContainerV2 {
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

class AServiceV2 {
    fun print() {
        println("A Service 입니다")
    }
}

class BServiceV2(
    private val aServiceV2: AServiceV2,
    private val cServiceV2: CServiceV2?
) {

    constructor(aServiceV2: AServiceV2) : this(aServiceV2, null) {
        println("hello")
    }

    fun print() {
        this.aServiceV2.print()
    }
}

class CServiceV2

fun main() {
    ContainerV2.register(AServiceV2::class)
    ContainerV2.register(BServiceV2::class)

    val bService = ContainerV2.getInstance(BServiceV2::class)
    bService.print()
}
