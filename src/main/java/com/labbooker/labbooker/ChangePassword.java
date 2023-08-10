package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChangePassword {

    public Button resetBtn;
    @FXML
    private Button exitBtn;


    public void handleResetBtn(){
        LecturerLogin lec = new LecturerLogin();

        System.out.println(getLecturerData.email);
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
}
