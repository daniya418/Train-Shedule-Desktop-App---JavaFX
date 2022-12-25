package com.example.courseworkfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchData {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet rs;
    private static String departureStation;
    private static String arrivalStation;

//    private static String departureStationTime;
//    private static String arrivalStationTime;
//    private static String arrTime;
//
//    public static String getDepartureStationTime() {
//        return departureStationTime;
//    }

//    public static void setDepartureStationTime(String departureStationTime) {
//        SearchData.departureStationTime = departureStationTime;
//    }

//    public static String getArrivalStationTime() {
//        return arrivalStationTime;
//    }
//
//    public static void setArrivalStationTime(String arrivalStationTime) {
//        SearchData.arrivalStationTime = arrivalStationTime;
//    }
//
//    public SearchData(String departureStationTime , String arrivalStationTime) {
//        this.departureStationTime=departureStationTime;
//        this.arrivalStationTime=arrivalStationTime;





    private static LocalDate date;



    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        SearchData.date = date;
    }

    public static String getDepartureStation() {
        return departureStation;
    }

    public static String getArrivalStation() {
        return arrivalStation;
    }


    public static void setArrivalStation(String arrivalStation) {
        SearchData.arrivalStation = arrivalStation;
    }


    public static void setDepartureStation(String departureStation) {
        SearchData.departureStation = departureStation;
    }
}
