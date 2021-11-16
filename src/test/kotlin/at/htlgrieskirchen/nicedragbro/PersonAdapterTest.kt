package at.htlgrieskirchen.nicedragbro

import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.FileReader

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonAdapterTest {
    val gson = GsonBuilder()
        .registerTypeAdapter(Person::class.java, PersonAdapter())
        .create()

    @Test
    fun `Deserialization works as expected`() {
        val person = gson.fromJson(FileReader("task/data/john.json"), Person::class.java)
        println("Height: " + person.image.height)
        println("Width: " + person.image.width)

        assertEquals(1, person.id)
        assertEquals("John", person.firstName)
        assertEquals("Mustermann", person.lastName)
        assertEquals(183.0, person.size)
        assertEquals(94.0, person.weight)
        assertEquals(28.0, person.bmi)
        assertEquals(23.95, person.ffmi)
    }
}