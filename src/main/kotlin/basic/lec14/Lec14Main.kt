package basic.lec14

import basic.lec14.car.Avante
import basic.lec14.car.Grandeur
import basic.lec14.car.HyundaiCar
import basic.lec14.car.Sonata

fun main() {
    val dto1 = PersonDto("이승복", 100)
    val dto2 = PersonDto("보키", 100)
    println(dto1 == dto2)
    println(dto1)
}

private fun handleCar(car: HyundaiCar) {
    when (car) {
        is Avante -> TODO()
        is Grandeur -> TODO()
        is Sonata -> TODO()
    }
}