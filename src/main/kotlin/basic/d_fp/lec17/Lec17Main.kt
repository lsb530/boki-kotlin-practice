package basic.d_fp.lec17

import basic.lec17.Fruit

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 2500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("수박", 10_000),
    )

    // 람다 호출 1
    isApple(fruits[0])
    // 람다 호출 2
    isApple2.invoke(fruits[0])

    // 함수를 파라미터로 넘기는 방법
    filterFruits(fruits, isApple)
    filterFruits(fruits, isApple2)
    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과" })
    filterFruits(fruits) { fruit -> fruit.name == "사과" }
    filterFruits(fruits) { a -> a.name == "사과" }
    filterFruits(fruits) { it.name == "사과" }
}

// lambda(익명 함수): 이름만 없는 함수
val isApple = fun(fruit: Fruit): Boolean {
    return fruit.name == "사과"
}

// lambda(익명 함수) use arrow
val isApple2: (Fruit) -> Boolean = { fruit: Fruit -> fruit.name == "사과" }

private fun filterFruits(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean
): List<Fruit> {
    val result = mutableListOf<Fruit>()
    for (fruit in fruits) {
        /* 방법1 */
        // if (filter(fruit)) {
        //     result.add(fruit)
        // }

        /* 방법2 */
        if (filter.invoke(fruit)) {
            result.add(fruit)
        }
    }
    return result
}