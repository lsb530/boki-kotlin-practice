package basic.lec11

public val a = 3

fun add(a: Int, b: Int) = a + b

data class Dog(val name: String)

internal val dog = Dog("몽이")

open class SuperCat protected constructor(val name: String)

class Car(
    internal val name: String,
    private var owner: String,
    _price: Int
) {
    var price = _price
        private set
}