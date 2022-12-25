package com.example.courseworkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Booking extends HelloController{

    @FXML
    private Button bHomeBtn;

    @FXML
    void bHomeBtn(ActionEvent event) {
        Parent root;
        try {
            root = new FXMLLoader(Booking.class.getResource("hello-view.fxml")).load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Sri Lankan Train Schedule");
            stage.setScene(new Scene(root, 780, 531));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}