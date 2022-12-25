package com.example.courseworkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SearchWindow extends HelloController{



    @FXML
    private Label arrivelLbl;
    @FXML
    public Button backBtnSw;
    @FXML
    private Label tempLabel;


    @FXML
    private TableView<dataList> tblView;

    @FXML
    private TableColumn<dataList, String> tblcolArr;

    @FXML
    private TableColumn<dataList, String> tblcolAva;

    @FXML
    private TableColumn<dataList, Integer> tblcolAvaseat;

    @FXML
    private TableColumn<dataList, String> tblcolDep;

    @FXML
    private TableColumn<dataList, String> tblcolbook;

    ObservableList<dataList> listM;




    @FXML
    void initialize()  {
//set text for label
        tempLabel.setText(SearchData.getDepartureStation());
        arrivelLbl.setText(SearchData.getArrivalStation());
//setting value for our cell
        tblcolArr.setCellValueFactory(new PropertyValueFactory<dataList,String>("arrivalTime"));
        tblcolAva.setCellValueFactory(new PropertyValueFactory<dataList,String>("availlable"));
        tblcolAvaseat.setCellValueFactory(new PropertyValueFactory<dataList,Integer>("avaSeats"));
        tblcolDep.setCellValueFactory(new PropertyValueFactory<dataList,String>("departureTime"));
        tblcolbook.setCellValueFactory(new PropertyValueFactory<dataList,String>("booking"));

//get user from database
        listM =sqlconnect.getDatausers();
//Add details to table view
        tblView.setItems(listM);

//when user press the backbutton it will go back to hello applicattion
        backBtnSw.setOnAction(event -> {
            backBtnSw.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/courseworkfx/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }




}
