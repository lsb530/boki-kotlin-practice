package expert.a_generic

fun main() {
    val fishCage: Cage4<Fish> = Cage4()

    fishCage.put(GoldFish("금붕어"))
    fishCage.put(Carp("잉어"))

    val fishes = listOf(GoldFish("금붕어"), Carp("잉어"))
    fishCage.putAll(fishes)
}

class Cage4<in T> {
    private val animals: MutableList<T> = mutableListOf()

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun putAll(animals: List<T>) {
        this.animals.addAll(animals)
    }
}
