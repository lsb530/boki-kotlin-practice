package expert.b_delegate.delegate

fun main() {
    val greenApple = GreenApple()
    println(greenApple.name)
    println(greenApple.color)
    println(greenApple.bite())

    println()

    val greenApple2 = GreenApple4(Apple())
    println(greenApple2.name)
    println(greenApple2.color)
    println(greenApple2.bite())
}

interface Fruit {
    val name: String
    val color: String
    fun bite()
}

open class Apple : Fruit {
    override val name: String
        get() = "사과"
    override val color: String
        get() = "빨간색"

    override fun bite() {
        print("사과 톡톡~ 아삭~")
    }
}

class GreenApple : Fruit {
    override val name: String
        get() = "사과"
    override val color: String
        get() = "초록색"

    override fun bite() {
        print("사과 톡톡~ 아삭~")
    }
}

class GreenApple2 : Apple() {
    override val color: String
        get() = "초록색"
}

class GreenApple3(
    private val apple: Apple
) : Fruit {
    override val color: String
        get() = "초록색"
    override val name: String
        get() = apple.name
    override fun bite() = apple.bite()
}

class GreenApple4(
    private val apple: Apple
) : Fruit by apple {
    override val color: String
        get() = "초록색"
}
