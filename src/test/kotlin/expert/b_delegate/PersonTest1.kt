package expert.b_delegate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonTest1 {
    private val person = Person()

     @Test
     fun isLeeTest() {
         // given
         // val person = Person("이순신")
         val person = person.apply { name = "이순신" }

         // when & then
         assertTrue(person.isLee)
     }

    @Test
    fun maskingNameTest() {
        // given
        // val person = Person("이승복")
        val person = person.apply { name = "이승복" }

        // when & then
        assertEquals("이**", person.maskingName)
    }
}
