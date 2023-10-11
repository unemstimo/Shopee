module ui {
    requires com.fasterxml.jackson.databind;
    
    requires core;
    requires javafx.controls;
    requires javafx.fxml;

    opens shopee.ui to javafx.graphics, javafx.fxml;
}
