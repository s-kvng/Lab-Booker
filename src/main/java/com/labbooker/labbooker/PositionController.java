package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PositionController {

    @FXML
    private Button exitBtn;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private FXMLLoader roott;


    public void handleExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }


    public void handleClickLecturerBtn(ActionEvent e) throws IOException {

        changePage("lecturerLogin.fxml", e);

    }


    public void changePage(String page , ActionEvent e) throws IOException {

        roott = new FXMLLoader(HelloApplication.class.getResource(page));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void handleClassRepBtn(ActionEvent e) throws IOException {
        changePage("classRepLogin.fxml", e);
    }
}