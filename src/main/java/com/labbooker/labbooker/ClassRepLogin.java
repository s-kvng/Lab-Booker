package com.labbooker.labbooker;

import com.labbooker.labbooker.utils.CheckPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ClassRepLogin {

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

    CheckPosition position = CheckPosition.getInstance();

    LoginData repData = new LoginData();


    public void onLoginButtonClick(ActionEvent actionEvent) {

        if (emailField.getText().isBlank() == false && passField.getText().isBlank() == false) {


            validateLogin();
        } else {

            if (emailField.getText().isBlank() == true) {
                emailErrorMessage.setText("Please enter email");
            } else {
                emailErrorMessage.setText("");
            }
            if (passField.getText().isBlank() == true) {
                passwordErrorMessage.setText("Please enter password");
            } else {
                passwordErrorMessage.setText("");
            }
        }

    }

    private void validateLogin() {

        classRepData();


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verify = "SELECT count(1) FROM `repAccounts` WHERE email =?  AND password =?";

        try {
            PreparedStatement ps = connectDB.prepareStatement(verify);
            ps.setString(1, repData.getEmail());
            ps.setString(2, repData.getPassword());
            ResultSet queryResult = ps.executeQuery();

            Alert alert;



            while (queryResult.next()) {
                System.out.println(queryResult.getInt(1));
                if (queryResult.getInt(1) == 1) {
//                    lecturerData();
                    if (repData.getReset_password() == 1) {


                        position.setPosition("class rep");
                        position.setEmail(repData.getEmail());
                        redirect("changePassword.fxml");

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success message");
                        alert.setHeaderText(null);
                        alert.setContentText("Redirect to change password");
                        alert.showAndWait();

                    } else {
//                        redirectDashboardPage();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success message");
                        alert.setHeaderText(null);
                        alert.setContentText("Redirect to dashboard");
                        alert.showAndWait();
                    }

                } else {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid login. Please try again");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void redirect(String page) throws IOException {

        roott = new FXMLLoader(HelloApplication.class.getResource(page));
        stage = (Stage) exitBtn.getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public  void classRepData(){


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT * FROM `repAccounts` WHERE email = '" + emailField.getText() + "' AND password = '"+ passField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);


            while(rs.next()){

                System.out.println("works");

                repData.setEmail(rs.getString("email"));
                repData.setPassword(passField.getText());
                repData.setReset_password(rs.getInt("reset_password"));
//


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
}
