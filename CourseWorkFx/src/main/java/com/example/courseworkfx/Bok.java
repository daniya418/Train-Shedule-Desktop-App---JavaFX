package com.example.courseworkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Bok {


    @FXML
    private Button bHomeBtn;

    @FXML
    void bHomeBtn(ActionEvent event) throws IOException {


        Node node =(Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene =new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
