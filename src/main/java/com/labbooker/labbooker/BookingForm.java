package com.labbooker.labbooker;

import com.labbooker.labbooker.models.BookingData;
import com.labbooker.labbooker.models.GetLecturerData;
import com.labbooker.labbooker.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookingForm implements Initializable {

    @FXML
    private TextField endTime;
    @FXML
    private Button bookbtn;
    @FXML
    private ComboBox<String> labNameComboBox;

    @FXML
    private ComboBox<String> classNameComboBox1;

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

    Alert alert;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private FXMLLoader roott;

    GetLecturerData lecturerData = GetLecturerData.getInstance();


    private ObservableList<String> labName = FXCollections.observableArrayList(
            "Lab 1" , "Lab 2" , "Lab 3"
    );

    private ObservableList<String> classNameList = FXCollections.observableArrayList(
            "NKA100" , "NKB100" , "NKC100" , "NKD100" ,"NKA200" , "NKB200", "NKC200", "NKD200"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(lecturerData.getFirst_name() != null ){

            lecturerName.setText(lecturerData.getFirst_name() + " " + lecturerData.getLast_name());
        }

        labNameComboBox.setItems(labName);

        classNameComboBox1.setItems(classNameList);

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


        classNameComboBox1.setConverter(new StringConverter<String>() {
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

       if(!lecturerData.getFirst_name().isEmpty() ){

           lecturerName.setText(lecturerData.getFirst_name() + " " + lecturerData.getLast_name());
       }
    }



    public void handleExitButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }


    public void handleHistoryButtonClick(ActionEvent actionEvent) throws IOException {

       redirect("history.fxml");
    }


    public void handleBookBtn(ActionEvent actionEvent){

        BookingData data = new BookingData(labNameComboBox.getValue(), date.getValue(), time.getText() , endTime.getText() ,  classNameComboBox1.getValue());

        DatabaseConnection connection = new DatabaseConnection();
        Connection conn = connection.getConnection();

        boolean hasConflict = checkForConflictsInDatabase(conn , data);

        String query = "INSERT INTO `lab-booking`(`labName`, `date`, `startTime`, `className`, `endTime`) VALUES (? , ? , ? ,? ,?)";

        if (!hasConflict) {
            // Add the booking to the database

            try{

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, data.getLab());
                ps.setDate(2,data.getDate());
                ps.setTime(3, data.getStartTime());
                ps.setString(4, data.getClassName());
                ps.setTime(5, data.getEndTime());

                ps.executeUpdate();



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            showSuccessAlert("You have successfully booked the lab for "+ data.getStartTime() + "on "+ data.getDate());
        } else {
            showErrorAlert("Booking conflict! Choose a different time.");
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


    public boolean checkForConflictsInDatabase(Connection conn, BookingData data) {

        String query = "SELECT * FROM `lab-booking` WHERE `labName` = ? AND `startTime` <= ? AND `endTime` >= ? ";



            try{
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, data.getLab());
                ps.setTime(2, data.getStartTime());
                ps.setTime(3 , data.getEndTime());

                ResultSet resultSet = ps.executeQuery();

                return resultSet.next(); // Conflict found if any row is returned

            } catch (SQLException e) {
                e.printStackTrace();
                return true; // Handle the error appropriately
        }
    }


    private void showSuccessAlert(String message) {

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

//ResultSet resultSet = statement.executeQuery()