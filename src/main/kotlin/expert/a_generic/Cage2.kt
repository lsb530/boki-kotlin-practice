package expert.a_generic

fun main() {
    val block1 = {
        val cage = Cage2<Carp>()
        cage.put(Carp("잉어"))
        val carp = cage.getFirst()
    }

    val block2 = {
        val goldFishCage = Cage2<GoldFish>()
        goldFishCage.put(GoldFish("금붕어"))

        val fishCage = Cage2<Fish>()
        // fishCage.moveFrom(goldFishCage) // Type mismatch!
    }

    block1()
    block2()
}

class Cage2<T : Any> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage2<T>) {
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage2<T>) {
        otherCage.animals.addAll(this.animals)
    }
}
