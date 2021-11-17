package at.htlgrieskirchen.nicedragbro

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.chart.BarChart
import javafx.scene.chart.XYChart
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import javafx.scene.input.TransferMode
import java.io.FileReader
import java.net.URL
import java.util.*

class HelloController : Initializable {
    @FXML
    private lateinit var lbl_id: Label

    @FXML
    private lateinit var lbl_first_name: Label

    @FXML
    private lateinit var lbl_last_name: Label

    @FXML
    private lateinit var lbl_height: Label

    @FXML
    private lateinit var lbl_weight: Label

    @FXML
    private lateinit var lbl_bmi: Label

    @FXML
    private lateinit var lbl_ffmi: Label

    @FXML
    private lateinit var barchart_bmi_ffmi: BarChart<String, Double>

    @FXML
    private lateinit var barchart_height: BarChart<String, Double>

    @FXML
    private lateinit var barchart_gewicht: BarChart<String, Double>

    @FXML
    private lateinit var listView_personen: ListView<Person>

    @FXML
    private lateinit var imgview_person: ImageView

    @FXML
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        listView_personen.setCellFactory { PersonCell() }
        listView_personen.setOnMouseClicked {
            displayPerson(listView_personen.selectionModel.selectedItem)
        }

        imgview_person.setOnDragOver {
            if (it.dragboard.hasFiles()) {
                it.acceptTransferModes(*TransferMode.ANY)
            }
            it.consume()
        }
        imgview_person.setOnDragDropped {
            val files = it.dragboard.files
            val file = files.first()

            val person = gson.fromJson(FileReader(file), Person::class.java)
            listView_personen.items += person

            it.consume()
        }
    }

    private fun displayPerson(person: Person) {
        barchart_bmi_ffmi.data.clear()
        barchart_height.data.clear()
        barchart_gewicht.data.clear()

        lbl_id.text = person.id.toString()
        lbl_first_name.text = person.firstName
        lbl_last_name.text = person.lastName
        lbl_height.text = person.size.toString()
        lbl_weight.text = person.weight.toString()
        lbl_bmi.text = person.bmi.toString()
        lbl_ffmi.text = person.ffmi.toString()

        barchart_gewicht.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data("", person.weight)
        })
        barchart_height.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data("", person.size)
        })
        barchart_bmi_ffmi.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data(" ", person.bmi)
            data += XYChart.Data("", person.ffmi)
        })
    }
}