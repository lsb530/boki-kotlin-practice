package basic.e_etc_and_scope.lec18

fun main() {
    val fruits = listOf(
        Fruit(1, "사과", 1000, 1500),
        Fruit(2, "사과", 1200, 1700),
        Fruit(3, "사과", 1200, 1700),
        Fruit(4, "사과", 1500, 2200),
        Fruit(5, "바나나", 2500, 3200),
        Fruit(6, "바나나", 3000, 3500),
        Fruit(7, "바나나", 3200, 3700),
        Fruit(8, "수박", 10_000, 12_000),
    )

    val apples = fruits.filter { it.name == "사과" }
    val apples2 = fruits.filterIndexed() { idx, fruit ->
        println(idx)
        idx % 2 == 0 && fruit.name == "사과"
    }
    val applePrices = fruits
        .filter { it.name == "사과" }
        .map { it.currentPrice }
    val applePrices2 = fruits
        .filter { it.name == "사과" }
        .mapIndexed { idx, fruit ->
            println(idx)
            fruit.currentPrice
        }
    val values = fruits.filter { it.name == "사과" }
        .mapNotNull { fruit -> fruit.nullOrValue() }

    val isAllApple = fruits.all { it.name == "사과" }
    val isNoApple = fruits.none { it.name == "사과" }
    val hasTenThousandApple = fruits.any { it.factoryPrice >= 10000 }
    val fruitCount = fruits.count()
    val orderByCurrentPriceAsc = fruits.sortedBy { it.currentPrice }
    val orderByCurrentPriceDesc = fruits.sortedByDescending { it.currentPrice }
    val distinctFruitNames = fruits.distinctBy { it.name }.map { it.name }
    val firstFruit = fruits.first()
    val firstFruitOrNull = fruits.firstOrNull()
    val lastFruit = fruits.last()
    val lastFruitOrNull = fruits.lastOrNull()

    val fruitsMap = fruits.groupBy { it.name }
    val distinctFruitMap = fruits.associateBy { it.id }
    // key, value 동시 처리
    val nameToPricesMap = fruits.groupBy({ it.name }, { it.factoryPrice })
    val idToPricesMap = fruits.groupBy({ it.id }, { it.factoryPrice })
    val idToPriceMap = fruits.associateBy({ it.id }, { it.factoryPrice })

    val appleListMap = fruits
        .groupBy { it.name }
        .filter { (k, v) -> k == "사과" }

    // 중첩 컬렉션
    val fruitsInList = listOf(
        listOf(
            Fruit(1, "사과", 1000, 1500),
            Fruit(2, "사과", 1200, 1500),
            Fruit(3, "사과", 1200, 1500),
            Fruit(4, "사과", 1500, 1500),
        ),
        listOf(
            Fruit(5, "바나나", 3000, 3200),
            Fruit(6, "바나나", 3200, 3200),
            Fruit(7, "바나나", 2500, 3200)
        ),
        listOf(
            Fruit(8, "수박", 10_000, 10_000)
        )
    )

    val samePriceFruits1 = fruitsInList.flatMap { list ->
        list.filter { it.factoryPrice == it.currentPrice }
    }
    val samePriceFruits2 = fruitsInList.flatMap { it.samePriceFilter }

    val flattenFruits = fruitsInList.flatten()
}

data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long
) {
    fun nullOrValue(): Fruit? {
        return if (currentPrice > factoryPrice) this else null
    }

    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice
}

private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    // return fruits.filter({ 람다 })
    return fruits.filter(filter)
}

fun String.hello(): Char {
    return this[0]
}

val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)

fun List<Fruit>.samePriceFilter(): List<Fruit> {
    return this.filter(Fruit::isSamePrice)
}
