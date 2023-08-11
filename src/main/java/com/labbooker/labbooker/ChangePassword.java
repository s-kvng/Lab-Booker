package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private FXMLLoader roott;


    public void handleResetBtn() throws IOException {
        Alert alert;



        if( passwordField1.getText().isBlank() == false && passwordField2.getText().isBlank() == false){

            if(Objects.equals(passwordField1.getText(), passwordField2.getText())){
                saveNewPassword();
                redirectPage();


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

    public void saveNewPassword(){

        int reset = 0;

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verify = "UPDATE `LecturerAccounts` SET `password`='" + passwordField1.getText() +"',`reset_password`='"+ reset +"' WHERE `email`='"+ getLecturerData.email +"'";

        try{
            Statement statement = connectDB.createStatement();
            int queryResult = statement.executeUpdate(verify);

            Alert alert;

            if(queryResult > 0){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success message");
                alert.setHeaderText(null);
                alert.setContentText("Password successfully updated");
                alert.showAndWait();
            }
            else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info message");
                alert.setHeaderText(null);
                alert.setContentText("Password was not updated");
                alert.showAndWait();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void redirectPage() throws IOException {
        roott = new FXMLLoader(HelloApplication.class.getResource("bookingForm.fxml"));
        stage = (Stage) exitBtn.getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
}
