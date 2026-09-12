module com.example.shapes {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.shapes to javafx.fxml;
    exports com.example.shapes;
}