package at.htlgrieskirchen.nicedragbro

import com.google.gson.annotations.SerializedName
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage

data class PersonBuilder(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val pixels: List<Int>,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("vn")
    val firstName: String,
    @SerializedName("nn")
    val lastName: String,
    @SerializedName("groesse")
    val size: Double,
    @SerializedName("gewicht")
    val weight: Double,
    @SerializedName("bmi")
    val bmi: Double,
    @SerializedName("ffmi")
    val ffmi: Double
) {
    constructor(person: Person) : this(
        person.id,
        {
            val pixelReader = person.image.pixelReader
            val pixelList = mutableListOf<Int>()

            val width = person.image.width.toInt()
            val height = person.image.height.toInt()
            for (x in 0 until width) {
                for (y in 0 until height) {
                    pixelList += pixelReader.getArgb(x, y)
                }
            }

            pixelList
        }.invoke(),
        person.image.width.toInt(),
        person.image.height.toInt(),
        person.firstName,
        person.lastName,
        person.size,
        person.weight,
        person.bmi,
        person.ffmi
    )

    fun build(): Person {

        val image = if (width * height * 4 == pixels.size) {
            val image = WritableImage(width, height)
            val pixelWriter = image.pixelWriter

            pixelWriter.setPixels(
                0,
                0,
                width,
                height,
                PixelFormat.getIntArgbInstance(),
                pixels.toIntArray(),
                0,
                width * 4
            )

            image
        } else {
            val image = WritableImage(width, height)
            val pixelWriter = image.pixelWriter

            for (x in 0 until width) {
                for (y in 0 until height) {
                    pixelWriter.setArgb(x, y, pixels[y + x * width])
                }
            }

            image
        }

        return Person(id, image, firstName, lastName, size, weight, bmi, ffmi)
    }
}
