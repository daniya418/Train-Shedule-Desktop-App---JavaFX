module com.example.courseworkfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.courseworkfx to javafx.fxml;
    exports com.example.courseworkfx;
}