package expert.e_reflection

import kotlin.reflect.KClass

object ContainerV1 {
    // 등록한 클래스 보관! = KClass를 보관!
    private val registeredClasses = mutableSetOf<KClass<*>>()

    fun register(clazz: KClass<*>) {
        registeredClasses.add(clazz)
    }

    fun <T : Any> getInstance(type: KClass<T>): T {
        return registeredClasses.firstOrNull { clazz -> clazz == type }
            ?.let { clazz -> clazz.constructors.first().call() as T }
            ?: throw IllegalArgumentException("해당 인스턴스 타입을 찾을 수 없습니다")
    }
}

class AServiceV1 {
    fun print() {
        println("A Service 입니다")
    }
}

fun main() {
    ContainerV1.register(AServiceV1::class)
    val aService = ContainerV1.getInstance(AServiceV1::class)
    aService.print()
}
