package shopee.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class ShopeeApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        LogInController controller = new LogInController();
        controller.setUpAccess(); //sets up the access to either local or remote depending if the server is running or not
        fxmlLoader.setController(controller);
        fxmlLoader.setLocation(this.getClass().getResource("LogIn.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(ShopeeApp.class, args);
    }
}