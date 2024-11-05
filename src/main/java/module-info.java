module com.example.cointosserjavafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.cointosserjavafx to javafx.fxml;
    exports com.example.cointosserjavafx;
}