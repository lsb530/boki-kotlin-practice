package basic.c_oop.lec12

import basic.lec12.Movable

fun main() {
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)

    moveSomething(object : Movable {
        override fun move() {
            println("move")
        }
        override fun fly() {
            println("fly")
        }
    })
}

class PersonWithJvmStaticObject private constructor(
    val name: String,
    val age: Int,
) {
    companion object {
        private const val MIN_AGE = 1
        @JvmStatic
        fun newBaby(name: String): PersonWithJvmStaticObject {
            return PersonWithJvmStaticObject(name, MIN_AGE)
        }
    }
}

class PersonWithNoNameObject private constructor(
    val name: String,
    val age: Int,
) {
    companion object : Log {
        private const val MIN_AGE = 1
        fun newBaby(name: String): PersonWithNoNameObject {
            return PersonWithNoNameObject(name, MIN_AGE)
        }

        override fun log() {
            println("Person class의 동행 객체 Factory")
        }
    }
}

class Person private constructor(
    val name: String,
    val age: Int,
) {
    companion object Factory : Log {
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun log() {
            println("Person class의 동행 객체 Factory")
        }
    }
}

interface Log {
    fun log()
}

object Singleton {
    var a: Int = 0
}

private fun moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}