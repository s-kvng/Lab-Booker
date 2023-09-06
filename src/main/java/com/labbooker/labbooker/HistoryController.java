package com.labbooker.labbooker;

import com.labbooker.labbooker.models.LabBookings;
import com.labbooker.labbooker.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    public Button backBtn;
    @FXML
    private TableColumn<LabBookings, Integer> colID;
    @FXML
    private TableColumn <LabBookings, String>colLabName;

    @FXML
    private TableColumn<LabBookings, Date> colDate;

    @FXML
    private TableColumn<LabBookings, Time> colStartTime;

    @FXML
    private TableColumn<LabBookings, String> colClass;

    @FXML
    private TableColumn<LabBookings, Time> colEndTime;

    @FXML
    private TableView<LabBookings> tvBooking;

    @FXML
    private Button exitBtn;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private FXMLLoader roott;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showLabBookings();
    }

    public void handleExitButtonClick(ActionEvent actionEvent) {

        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void backPage() throws IOException {
        roott = new FXMLLoader(HelloApplication.class.getResource("bookingForm.fxml"));
        stage = (Stage) exitBtn.getScene().getWindow();
        scene = new Scene(roott.load());
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<LabBookings> getLabBookings(){
        ObservableList<LabBookings> labBookingList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "Select * FROM `lab-booking`";
        Statement st;
        ResultSet rs;

        try {
                st = connectDB.createStatement();
                rs = st.executeQuery(query);
                LabBookings labBookings;

                while(rs.next()){
                    labBookings = new LabBookings(rs.getInt("id"), rs.getString("labName"), rs.getDate("date"),rs.getTime("startTime"),rs.getString("className"), rs.getTime("endTime"));
                    labBookingList.add(labBookings);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labBookingList;
    }


    public void showLabBookings(){
        ObservableList<LabBookings> list = getLabBookings();

        colID.setCellValueFactory(new PropertyValueFactory<LabBookings, Integer>("id"));
        colLabName.setCellValueFactory(new PropertyValueFactory<LabBookings, String>("labName"));
        colDate.setCellValueFactory(new PropertyValueFactory<LabBookings, Date>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<LabBookings, Time>("startTime"));
        colClass.setCellValueFactory(new PropertyValueFactory<LabBookings, String>("className"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<LabBookings, Time>("endTime"));

        tvBooking.setItems(list);
    }


    public void handleMouseClicked(MouseEvent mouseEvent) {
    }
}
