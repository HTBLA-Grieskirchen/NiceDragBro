package at.htlgrieskirchen.nicedragbro

import javafx.fxml.FXML
import javafx.scene.chart.BarChart
import javafx.scene.chart.XYChart
import javafx.scene.control.Label

class HelloController {
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
    private lateinit var barchart_weight: BarChart<String, Double>

    private fun displayPerson(person: Person) {
        barchart_bmi_ffmi.data.removeAll()
        barchart_height.data.removeAll()
        barchart_weight.data.removeAll()

        lbl_id.text = person.id.toString()
        lbl_first_name.text = person.firstName
        lbl_last_name.text = person.lastName
        lbl_height.text = person.size.toString()
        lbl_weight.text = person.weight.toString()
        lbl_bmi.text = person.bmi.toString()
        lbl_ffmi.text = person.ffmi.toString()

        barchart_weight.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data("Weight", person.weight)
        })
        barchart_height.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data("Height", person.size)
        })
        barchart_bmi_ffmi.data.add(XYChart.Series<String, Double>().apply {
            data += XYChart.Data("BMI", person.bmi)
            data += XYChart.Data("FFMI", person.ffmi)
        })
    }
}