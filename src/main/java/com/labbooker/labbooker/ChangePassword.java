package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.util.Objects;

public class ChangePassword {

    @FXML
    private Button resetBtn;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button exitBtn;


    public void handleResetBtn(){
        Alert alert;



        if( passwordField1.getText().isBlank() == false && passwordField2.getText().isBlank() == false){

            if(Objects.equals(passwordField1.getText(), passwordField2.getText())){
                System.out.println("done");

            }
            else{

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("The passwords do not match!!");
                alert.showAndWait();
            }

        }
        else{

            if(passwordField1.getText().isBlank() == true){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter password in first field");
                alert.showAndWait();
            }
            else if(passwordField2.getText().isBlank() == true){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter password in second field");
                alert.showAndWait();
            }

        }


//        System.out.println(getLecturerData.email);
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
}
