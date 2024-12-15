package basic.c_oop.lec10

fun main() {
    Derived(300)
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)
    }
}

class Derived(
    override val number: Int = 200
) : Base(number) {
    init {
        println("Derived Class")
    }
}