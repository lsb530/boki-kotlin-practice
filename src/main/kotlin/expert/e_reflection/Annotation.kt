package expert.e_reflection

import kotlin.reflect.KClass

// @Retention(AnnotationRetention.RUNTIME) // 기본값: RUNTIME
annotation class Example

@[AnnotationA AnnotationB]
class World

annotation class AnnotationA
annotation class AnnotationB

// field
@Person("Lee",20)
class PersonA

// named argument
@Person(name = "Kim", age = 20)
class PersonB

annotation class Person(
    val name: String,
    val age: Int,
    // val clazz: KClass<*>
)

class Bok(@Handsome val name: String)
/*
    1. 생성자의 파라미터, 2. name Property
    3. name Field       4. name Getter
    => 위치 모호
    => param > property > field
 */

// use-site target
class Baby1(@property:Handsome val name: String)
class Baby2(@field:Handsome val name: String)
class Baby3(@get:Handsome val name: String)
class Baby4(@set:Handsome var name: String)
class Baby5(@param:Handsome val name: String)

annotation class Handsome

@Shape(["C"])
@Shape(["A", "B"])
@Shape(arrayOf("A", "B"))
class Annotation

@Repeatable
@Target(AnnotationTarget.CLASS)
annotation class Shape(
    val texts: Array<String>
)

fun main() {
    val clazz: KClass<Annotation> = Annotation::class
}
