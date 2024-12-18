package example

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.system.measureTimeMillis

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
open class SequenceTest {

    private val fruits = mutableListOf<Fruit>()

    @Setup
    fun init() {
        (1..2_000_000).forEach { _ -> fruits.add(Fruit.random()) }
    }

    @Benchmark
    fun kotlinSequence() {
        val avg = fruits.asSequence()
            .filter { it.name == "사과" }
            .map { it.price }
            .take(10_000)
            .average()
    }

    @Benchmark
    fun kotlinIterator() {
        val avg = fruits
            .filter { it.name == "사과" }
            .map { it.price }
            .take(10_000)
            .average()

        /* JMH가 안되는 경우 시간 측정 */
        // val time = measureTimeMillis {
        //     val avg = fruits
        //         .filter { it.name == "사과" }
        //         .map { it.price }
        //         .take(10_000)
        //         .average()
        // }
        // println("소요 시간 : ${time}ms")
    }
}

data class Fruit(
    val name: String,
    val price: Long, // 1,000원부터 20,000원 사이
) {
    companion object {
        private val NAME_CANDIDATES = listOf("사과", "바나나", "수박", "채리", "오렌지")
        fun random(): Fruit {
            val randNum1 = Random.nextInt(0, 5)
            val randNum2 = Random.nextLong(1000, 20001)
            return Fruit(
                name = NAME_CANDIDATES[randNum1],
                price = randNum2
            )
        }
    }
}
