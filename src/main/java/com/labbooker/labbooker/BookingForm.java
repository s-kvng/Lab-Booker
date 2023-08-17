package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingForm implements Initializable {

    @FXML
    private Button exitBtn;

    @FXML
    private Label lecturerName;





    public void fetchLecturerName(){

       if(!getLecturerData.first_name.isEmpty() ){

           lecturerName.setText(getLecturerData.first_name + " " + getLecturerData.last_name);
       }
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            lecturerName.setText(getLecturerData.first_name + " " + getLecturerData.last_name);
        }
}
