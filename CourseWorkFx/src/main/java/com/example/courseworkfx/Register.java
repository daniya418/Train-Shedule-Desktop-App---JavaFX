package com.example.courseworkfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register {




    @FXML
    private TextField pwrdTF;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    private TextField conpwrdTF;
    @FXML
    private Button backBtn;


    //Craete connections
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    private void alert(String alertTitle, String alertContent, Alert.AlertType alertType)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setContentText(alertContent);
        alert.show();
    }
    @FXML
    void setBack(ActionEvent event) throws IOException {
        Node node =(Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene =new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        //get username and password from text feild
        String userName = usernameTF.getText();
        String password = pwrdTF.getText();
        String confirmPassword =conpwrdTF.getText();
        //check password and confirmpassword
        if ((userName.equals("")) || (password.equals("")) || (confirmPassword.equals("")))
        {
            alert("User name and Password required", "Please enter the all feild", Alert.AlertType.ERROR);

        }
       else if (!password.equals(confirmPassword))
        {
            alert("Password mismatch", "Please enter the correct password", Alert.AlertType.ERROR);
        }
       //add username and password to database
      user =  addUserToDatabase(userName,password);
       if(user != null)
       {
           Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Registered Successful");
           alert.setContentText("Continue");
           Optional<ButtonType> Ok = alert.showAndWait();
           Node node =(Node) event.getSource();
           Stage stage = (Stage) node.getScene().getWindow();
           stage.close();
       }
       else
       {
           alert("Try Again", "failed to register", Alert.AlertType.ERROR);
       }
        Node node =(Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.show();





    }
    //create user class user object
    public User user;

    private User addUserToDatabase(String userName, String password)
    {
        User user = null;

        try {
            //create connection
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/userlogin?serverTimezone=UTC","root","123456789");
            String sqlUrl = "INSERT INTO  userdetails  (userName,password)" + "VALUES(?,?)";
            preparedStatement =connection.prepareStatement(sqlUrl);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            //insert into table
            int addRows = preparedStatement.executeUpdate();
            if(addRows>0)
            {
                user = new User();
                user.userName=userName;
                user.password=password;
                usernameTF.clear();
                pwrdTF.clear();
                conpwrdTF.clear();
                usernameTF.requestFocus();

            }


            preparedStatement.close();
            connection.close();


        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        return user;
    }


}
