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
        fishCage.moveFrom(goldFishCage) // Type Safe!

        val fish = fishCage.getFirst()
    }

    val block3 = {
        val fishCage = Cage2<Fish>()
        val goldFishCage = Cage2<GoldFish>()

        goldFishCage.put(GoldFish("금붕어"))
        goldFishCage.moveTo(fishCage) // Type Safe!
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

    // 변성을 줌: 무공변->공변(out: variance annotation)
    // out: 생산자 역할만 부여(데이터를 꺼내는 역할만 수행)
    fun moveFrom(otherCage: Cage2<out T>) {
        otherCage.getFirst()
        // otherCage.put(Carp("잉어")) // Type mismatch
        // otherCage.put(this.getFirst()) // Type mismatch
        this.animals.addAll(otherCage.animals)
    }

    // 변성을 줌: 반공변(contra-variant)
    // in: 소비자 역할만 부여(데이터를 받는 역할만 수행)
    fun moveTo(otherCage: Cage2<in T>) {
        otherCage.animals.addAll(this.animals)
    }
}
