package com.example.courseworkfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class sqlconnect {



        static Connection connection =null;
        public static Connection Connectiondb()
        {
            try {
 //setting database connection
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost/userlogin?serverTimezone=UTC","root","123456789");
                return connection;
            }
            catch (ClassNotFoundException | SQLException e)
            {
                System.out.println("Driver not found");
                return null;
            }

        }
//get value from database
        public static ObservableList<dataList> getDatausers(){
            Connection connection =Connectiondb();
            ObservableList<dataList> list = FXCollections.observableArrayList();
            try {
                PreparedStatement stmt = connection.prepareStatement("select * from timedb");
                ResultSet rs = stmt.executeQuery();

                while (rs.next())
                {
                    list.add(new dataList(rs.getString("departureTime"), rs.getString("arrivalTime"),
                            rs.getString("availlable"),
                            rs.getString("booking"),
                            rs.getInt("avaSeats")));
                    System.out.println("DONEUH");

                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return list;
        }




}
