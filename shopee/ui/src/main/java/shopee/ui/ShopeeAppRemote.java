package shopee.ui;

import java.net.URI;
import java.net.URISyntaxException;

import shopee.ui.dataaccess.RemoteShopeeAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShopeeAppRemote extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        LogInController controller = new LogInController();
        controller.setDataAccess(new RemoteShopeeAccess(uriSetup())); //This comes from AbstractController.java and RemoteShopeeAccess.java
        loader.setController(controller);
        loader.setLocation(Shopee.class.getResource("LogIn.fxml"));
        final Parent parent = loader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private URI uriSetup() {
        URI newUri = null;
        try {
          newUri = new URI("insert URI here!!");
        } catch (URISyntaxException e) {
          System.out.println(e.getMessage());
        }
        return newUri;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
