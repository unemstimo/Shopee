module ui {
    requires com.fasterxml.jackson.databind;
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive core;

    exports shopee.ui;
    exports shopee.ui.dataaccess; 

    opens shopee.ui to javafx.graphics, javafx.fxml;
}
