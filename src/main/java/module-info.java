module com.example.grocery2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.grocery2 to javafx.fxml;
    exports com.example.grocery2;
}