package com.labbooker.labbooker;

import com.labbooker.labbooker.models.LabBookings;
import com.labbooker.labbooker.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RepDashboard implements Initializable {


    @FXML
    private TextField tfID;
    @FXML
    private ComboBox<String>  labF;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField tfStartTime;
    @FXML
    private TextField tfEndTime;
    @FXML
    private TableView<LabBookings> tvLabBookings;
    @FXML
    private TableColumn<LabBookings, Integer> colID;
    @FXML
    private TableColumn<LabBookings, String> colLab;
    @FXML
    private TableColumn<LabBookings, Date> colDate;

    @FXML
    private TableColumn<LabBookings, Time> colStartTime;

    @FXML
    private TableColumn<LabBookings, String> colClass;

    @FXML
    private TableColumn<LabBookings, Time> colEndTime;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;


    private ObservableList<String> labName = FXCollections.observableArrayList(
            "Lab 1" , "Lab 2" , "Lab 3"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showLabBookings();

        labF.setItems(labName);
    }

    public void handleBtnAction(ActionEvent event){
        System.out.println("Event");

        if(event.getSource() == btnUpdate){
            update();
        }
    }


    public ObservableList<LabBookings> getLabBookings(){
        ObservableList<LabBookings> labBookingList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT * FROM `lab-booking` WHERE `className`= ?";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = connectDB.prepareStatement(query);
            ps.setString(1, "NKAB-100");
            rs = ps.executeQuery();
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
        colLab.setCellValueFactory(new PropertyValueFactory<LabBookings, String>("labName"));
        colDate.setCellValueFactory(new PropertyValueFactory<LabBookings, Date>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<LabBookings, Time>("startTime"));
        colClass.setCellValueFactory(new PropertyValueFactory<LabBookings, String>("className"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<LabBookings, Time>("endTime"));

        tvLabBookings.setItems(list);
    }


    public void update(){

        String query = "UPDATE `lab-booking` SET `labName`=?,`date`= ?,`startTime`= ?,`endTime`= ? WHERE `id` = ?";

        executeQuery(query);
        showLabBookings();
    }


    public void executeQuery(String query){
        LabBookings getLabBookings = new LabBookings();
        getLabBookings.setId(Integer.parseInt(tfID.getText()));
        getLabBookings.setLabName(labF.getValue());
        getLabBookings.setStartTime(Time.valueOf(tfStartTime.getText()));
        getLabBookings.setEndTime(Time.valueOf(tfEndTime.getText()));
        getLabBookings.setDate(Date.valueOf(dateF.getValue()));


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = connectDB.prepareStatement(query);
            ps.setString(1, getLabBookings.getLabName());
            ps.setDate(2, getLabBookings.getDate());
            ps.setTime(3, getLabBookings.getStartTime());
            ps.setTime(4, getLabBookings.getEndTime());
            ps.setInt(5, getLabBookings.getId());
            int re =  ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleMouseAction(MouseEvent mouseEvent) {

        LabBookings bookings = tvLabBookings.getSelectionModel().getSelectedItem();
        System.out.println("id " + bookings.getId());
        System.out.println("id " + bookings.getClassName());

        tfID.setText(String.valueOf(bookings.getId()));
        labF.setValue(bookings.getLabName());
        dateF.setValue(bookings.getDate().toLocalDate());
        tfStartTime.setText(String.valueOf(bookings.getStartTime()));
        tfEndTime.setText(String.valueOf(bookings.getEndTime()));

    }
}


