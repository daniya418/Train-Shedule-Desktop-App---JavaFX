package com.example.courseworkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {



    @FXML
    private TextField pwrdTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button registerBtn;



    @FXML
    private Button loginBtn;
    private void alert(String alertTitle, String alertContent, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContent);
        alert.show();
    }
//initializing Connection PreparedStatement ResultSet

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    @FXML

    void setRegisterBtn(ActionEvent event) throws IOException {
        Node node =(Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene =new Scene(FXMLLoader.load(getClass().getResource("register.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void setLoginBtn(ActionEvent event)
    {
        //get text from text feild
        String userName = usernameTF.getText();
        String password = pwrdTF.getText();
        //check username and password is not null
        if ((userName.equals("")) || (password.equals("")))
        {
            alert("User name and Password required", "Please enter the user name or password", Alert.AlertType.ERROR);
        }
        else
        {
            try {
                //Connecting Database
                Class.forName("com.mysql.jdbc.Driver");
                connection=DriverManager.getConnection("jdbc:mysql://localhost/userlogin?serverTimezone=UTC","root","123456789");
                //find the username password from database
                preparedStatement =connection.prepareStatement("select * from userdetails where userName=? and password=?");
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,password);
                rs = preparedStatement.executeQuery();

                if(rs.next())
                {
                    Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Login Successful");
                    alert.setContentText("Enjoy your journey");
                    Optional <ButtonType> Ok = alert.showAndWait();
                    if(Ok.get() == ButtonType.OK)
                    {
                        Node node =(Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene =new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    }




                }
                else
                {
                    alert("Error Login", "Something went wrong! please try again", Alert.AlertType.ERROR);
                }
            }
            catch (ClassNotFoundException | SQLException e)
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null ,e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }




}
