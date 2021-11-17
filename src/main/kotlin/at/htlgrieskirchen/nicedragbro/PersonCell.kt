package at.htlgrieskirchen.nicedragbro

import javafx.scene.control.ListCell
import javafx.scene.control.Tooltip
import javafx.scene.image.ImageView
import javafx.scene.paint.Color

class PersonCell : ListCell<Person>() {
    override fun updateItem(item: Person?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = null
            graphic = null
        } else {
            text = "${item.firstName} ${item.lastName}"
            graphic = ImageView(item.image)

            when {
                item.bmi in 18.5..25.0 -> {
                    textFill = Color.BLUE
                    tooltip = Tooltip("BMI ist im normalen Bereich")
                }
                item.ffmi > 21 -> {
                    textFill = Color.GREEN
                    tooltip = Tooltip("Sportler!! :-)")
                }
                else -> {
                    textFill = Color.RED
                    tooltip = Tooltip("Sie müssen etwas an Ihrer Lebensführung ändern")
                }
            }
        }
    }
}