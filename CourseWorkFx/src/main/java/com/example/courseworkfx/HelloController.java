package com.example.courseworkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

public class HelloController {

    @FXML
    private Button aboutBtn;

    @FXML
    private Button bookOnlineBtn;
    @FXML
    private TextField arrivalStation;

    @FXML
    private ListView<String> dropDownStop;
    @FXML
    private TextField departureStation;
    @FXML
    private ListView<String> dropDownStart;

    @FXML
    private Button searchTrainsBtn;

    @FXML
    protected void searchTrainsBtnFun(ActionEvent event) {

    }

    @FXML
    void aboutBtn(ActionEvent event) {

    }

    @FXML
    void bookOnlineBtn(ActionEvent event) throws IOException {


            Node node =(Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("bok.fxml")));
            stage.setScene(scene);
            stage.show();



    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView mainWindow;

    @FXML
    private AnchorPane formHolder;

    @FXML
    void arrivalStation(ActionEvent event) {

    }

    @FXML
    void departureStation(ActionEvent event) {
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        datePicker.setValue(java.time.LocalDate.now());
        Stations stations = new Stations();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        stations.stations
                );

        //Setup drop-downs for the search
        dropDowns(options);

        dropDownStart.setOnMouseClicked(event -> {
            dropDownStart.setVisible(false);
            departureStation.setText(dropDownStart.getSelectionModel().getSelectedItem());
        });

        dropDownStop.setOnMouseClicked(event -> {
            dropDownStop.setVisible(false);
            arrivalStation.setText(dropDownStop.getSelectionModel().getSelectedItem());
        });

        //Search functionality

        //For the start destination
        departureStation.textProperty().addListener((observable, oldValue, newValue) -> {

            //Filter elements by the user input and get them into the dropdown menu
            dropDownStart.setItems(search(stations.stations, newValue));

        });

        //For the end destination
        arrivalStation.textProperty().addListener((observable, oldValue, newValue) -> {
            //Filter elements by the user input and get them into the dropdown menu
            dropDownStop.setItems(search(stations.stations, newValue));

        });

        searchTrainsBtn.setOnAction(event -> {
            //Get the user input
            String start = departureStation.getText();
            String end = arrivalStation.getText();
            LocalDate date = datePicker.getValue();
            //Check if the user input is valid
            if (start.equals("")) {
                alert("Invalid input", "Please enter a valid start station", Alert.AlertType.ERROR);
            } else if (end.equals("")) {
                alert("Invalid input", "Please enter a valid end station", Alert.AlertType.ERROR);
            } else if (!Arrays.asList(stations.stations).contains(start)) {
                //If the user input is valid, then search for the trains
                alert("Invalid train station", start + " is Not a valid train station", Alert.AlertType.ERROR);
            } else if (!Arrays.asList(stations.stations).contains(end)) {
                alert("Invalid train station", end + " is Not a valid train station", Alert.AlertType.ERROR);
            } else if (start.equals(end)) {
                alert("Invalid input", "Start and end stations cannot be the same", Alert.AlertType.ERROR);
            }
            //If the user input is valid, then search for the trains
            else {
                SearchData.setDepartureStation(start);
                SearchData.setArrivalStation(end);
                SearchData.setDate(date);

                Parent root;
                try {
                    root = new FXMLLoader(HelloController.class.getResource("search-window.fxml")).load();
                    Stage stage = new Stage();
                    stage.setTitle("Booking");
                    stage.setScene(new Scene(root, 780, 531));
                    stage.setResizable(false);
                    stage.show();
                    // Hide this current window (if this is what you want)
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


        aboutBtn.setOnAction(event -> {

            Parent root;
            try {
                root = new FXMLLoader(HelloController.class.getResource("about.fxml")).load();
                Stage stage = new Stage();
                stage.setTitle("About");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 780, 531));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    private ObservableList<String> search(String[] arrayToBeSearched, String currentTypedString) {
        String[] filteredObj = new String[0];
        for (String currentString : arrayToBeSearched) {
            if (currentString.toLowerCase().contains(currentTypedString.toLowerCase())) {
                filteredObj = addToArray(currentString, filteredObj);
            }
        }
        ObservableList<String> currOptions =
                FXCollections.observableArrayList(
                        filteredObj
                );
        dropDownStart.setItems(currOptions);
        return currOptions;
    }

    private String[] addToArray(String element, String[] sourceArray) {
        String[] tempArray = new String[sourceArray.length + 1];
        System.arraycopy(sourceArray, 0, tempArray, 0, sourceArray.length);
        tempArray[sourceArray.length] = element;
        return tempArray;
    }

    private void dropDowns(ObservableList<String> options) {
        departureStation.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                dropDownStart.setVisible(true);
                dropDownStop.setVisible(false);
                dropDownStart.setItems(options);
            }
        });

        arrivalStation.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                dropDownStop.setVisible(true);
                dropDownStart.setVisible(false);
                dropDownStop.setItems(options);
            }
        });

        showDropDowns(options);

        //Hide dropdowns when out of focus
        hideDropDowns();
    }

    private void alert(String alertTitle, String alertContent, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContent);
        alert.show();
    }
    private void hideDropDowns() {
        mainWindow.setOnMouseClicked(event -> {
            dropDownStart.setVisible(false);
            dropDownStop.setVisible(false);
        });

        formHolder.setOnMouseClicked(mouseEvent -> {
            dropDownStart.setVisible(false);
            dropDownStop.setVisible(false);
        });
    }

    private void showDropDowns(ObservableList<String> options) {
        departureStation.setOnMouseClicked(actionEvent -> {
            dropDownStart.setVisible(true);
            dropDownStart.setItems(options);
        });

        arrivalStation.setOnMouseClicked(actionEvent -> {
            dropDownStop.setVisible(true);
            dropDownStop.setItems(options);
        });
    }

}