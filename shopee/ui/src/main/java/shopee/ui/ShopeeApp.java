package shopee.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App  
 */
public class ShopeeApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        LogInController controller = new LogInController();
        // sets up the access to either local or remote depending if the server is running or not
        controller.setUpAccess(); 
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