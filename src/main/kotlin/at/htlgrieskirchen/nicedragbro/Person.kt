package at.htlgrieskirchen.nicedragbro

import javafx.scene.image.Image

data class Person(
    val id: Int,
    val image: Image,
    val firstName: String,
    val lastName: String,
    val size: Double,
    val weight: Double,
    val bmi: Double,
    val ffmi: Double
)
