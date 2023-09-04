package com.labbooker.labbooker;

import com.labbooker.labbooker.models.GetLecturerData;
import com.labbooker.labbooker.utils.CheckPosition;
import com.labbooker.labbooker.utils.DatabaseConnection;
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
import java.sql.PreparedStatement;
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

    String query;

    CheckPosition position = CheckPosition.getInstance();


    public void handleResetBtn() throws IOException {
        Alert alert;



        if( passwordField1.getText().isBlank() == false && passwordField2.getText().isBlank() == false){

            if(Objects.equals(passwordField1.getText(), passwordField2.getText())){
                saveNewPassword();
                if(position.getPosition().equals("class rep")){
                    redirectPage("repDashboard.fxml");
                }
                else{
                    redirectPage("bookingForm.fxml");
                }


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

        if(position.getPosition().equals("class rep")){
             query = "UPDATE `repAccounts` SET `password`= ? ,`reset_password`= ? WHERE `email`= ?";
        } else if (position.getPosition().equals("lecturer")) {
            query = "UPDATE `LecturerAccounts` SET `password`= ? ,`reset_password`= ? WHERE `email`=? ";
        }

        try{


            PreparedStatement ps = connectDB.prepareStatement(query);
            ps.setString(1,passwordField1.getText());
            ps.setInt(2, reset);
            if (position.getPosition().equals("class rep")){
                ps.setString(3, position.getEmail());
            }
            else{
                ps.setString(3, position.getEmail());
            }
            int queryResult = ps.executeUpdate();

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

    public void redirectPage(String page) throws IOException {
        roott = new FXMLLoader(HelloApplication.class.getResource(page));
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
