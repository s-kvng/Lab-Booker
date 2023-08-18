package com.labbooker.labbooker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingForm implements Initializable {

    @FXML
    private ComboBox<String> labNameComboBox;

    @FXML
    private TextField className;

    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private Button exitBtn;

    @FXML
    private Label lecturerName;


    private ObservableList<String> labName = FXCollections.observableArrayList(
            "Lab 1" , "Lab 2" , "Lab 3"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(getLecturerData.first_name != null ){

            lecturerName.setText(getLecturerData.first_name + " " + getLecturerData.last_name);
        }

        labNameComboBox.setItems(labName);

        labNameComboBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return s;
            }

            @Override
            public String fromString(String s) {
                return s;
            }
        });

    }



    public void fetchLecturerName(){

       if(!getLecturerData.first_name.isEmpty() ){

           lecturerName.setText(getLecturerData.first_name + " " + getLecturerData.last_name);
       }
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }





}
