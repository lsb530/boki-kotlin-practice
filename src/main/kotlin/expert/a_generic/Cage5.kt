package expert.a_generic

fun main() {
    val sparrow = Sparrow()
    val eagle = Eagle()

    val cage5 = Cage5(
        mutableListOf(eagle, sparrow)
    )

    cage5.printAfterSorting()
}

abstract class Bird(
    name: String,
    private val size: Int,
) : Animal(name), Comparable<Bird> {
    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

class Sparrow : Bird("참새", 100)
class Eagle : Bird("독수리", 500)

// class Cage5<T : Animal>(
class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf(),
) where T : Animal, T : Comparable<T> {

    fun printAfterSorting() {
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage5<T>) {
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage5<T>) {
        otherCage.animals.addAll(this.animals)
    }
}

// non generic
fun List<String>.hasIntersection1(other: List<String>): Boolean {
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}

// generic func
fun <T> List<T>.hasIntersection(other: List<T>): Boolean {
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}
