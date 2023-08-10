package com.labbooker.labbooker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LecturerLogin {

    @FXML
    private Label emailErrorMessage;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passField;


    @FXML
    private Button exitBtn;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private FXMLLoader roott;



    public void onLoginButtonClick(){

        if( emailField.getText().isBlank() == false && passField.getText().isBlank() == false){


            validateLogin();
        }
        else{

            if(emailField.getText().isBlank() == true){
                emailErrorMessage.setText("Please enter email");
            }
            else {
                emailErrorMessage.setText("");
            }
            if(passField.getText().isBlank() == true){
                passwordErrorMessage.setText("Please enter password");
            }
            else{
                passwordErrorMessage.setText("");
            }
        }

    }


    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verify = "SELECT count(1) FROM `LecturerAccounts` WHERE email = '" + emailField.getText() + "' AND password = '"+ passField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verify);

            Alert alert;

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    lecturerData();
                    if( getLecturerData.reset_password == 1){
                        redirectPage();
                    }
                    else{
                        redirectDashboardPage();
                    }

                }
                else{

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid login. Please try again");
                    alert.showAndWait();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void handleExitButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void redirectPage() throws IOException {
        roott = new FXMLLoader(HelloApplication.class.getResource("changePassword.fxml"));
        stage = (Stage) exitBtn.getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void redirectDashboardPage() throws IOException {
        roott = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        stage = (Stage) exitBtn.getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public  void lecturerData(){


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT * FROM `LecturerAccounts` WHERE email = '" + emailField.getText() + "' AND password = '"+ passField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            getLecturerData data;

            while(rs.next()){
//
                getLecturerData.id = rs.getInt("id");
                getLecturerData.first_name = rs.getString("first_name");
                getLecturerData.last_name = rs.getString("last_name");
                getLecturerData.reset_password = rs.getInt("reset_password");
                getLecturerData.email = rs.getString("email");



            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
