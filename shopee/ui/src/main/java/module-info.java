module shopee.ui {
    requires shopee.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens shopee.ui to javafx.graphics, javafx.fxml;
}
