package basic.lec13

fun main() {
    
}

class House(
    private val address: String,
    private val livingRoom: LivingRoom,
) {
    class LivingRoom(
        private var area: Double,
    )
}

class InnerHouse(
    private val address: String,
    private val livingRoom: LivingRoom,
) {
    inner class LivingRoom(
        private var area: Double,
    ) {
        val address: String
            get() = this@InnerHouse.address
    }
}