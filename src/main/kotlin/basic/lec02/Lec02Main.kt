package basic.lec02

fun main() {
    var str: String? = "ABC"
    println(str?.length)
    str = null
    println(str?.length ?: 0)

//    println(startsWithA1(null))
    println(startsWithA1("ABC"))

    println(startsWithA2(null))
    println(startsWithA2("ABC"))

    println(startsWithA3(null))
    println(startsWithA3("ABC"))

    println(startsWith("ABC"))
//    println(startsWith(null))

    val person = Person("developer")
    startsWithA(person.name)
}

fun startsWithA1(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null이 들어왔습니다")
}

fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

fun startsWith(str: String?): Boolean {
    return str!!.startsWith("A")
}

fun startsWithA(str: String): Boolean {
    return str.startsWith("A")
}