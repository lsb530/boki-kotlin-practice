package expert.a_generic

fun main() {
    val cage = Cage()

    val block1 = {
        cage.put(Carp("잉어"))
        // val carp: Carp = cage.getFirst() // Type Mismatch: Animal
    }

    val block2 = {
        cage.put(GoldFish("금붕어"))
        val carp: Carp = cage.getFirst() as Carp  // 다소 위험: Runtime에 에러 포착
    }

    val block3 = {
        cage.put(Carp("잉어"))
        val carp = cage.getFirst() as? Carp ?: throw IllegalArgumentException()
    }

    // block1()
    // block2()
    // block3()
}

class Cage {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage) {
        this.animals.addAll(cage.animals)
    }
}
