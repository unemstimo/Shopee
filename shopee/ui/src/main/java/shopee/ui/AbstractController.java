package shopee.ui;

import java.io.IOException;

import shopee.ui.dataaccess.UserAccess;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {
    
    protected UserAccess dataAccess;

    /**
     * Creating an enum for the controllers. An enumeration is used to define controllers and fxml
     * files.
     * The enum includes methods for getting the controller and the fxml file.
     */
    public enum Controllers {
        LOGIN("LogIn.fxml", new LogInController()),
        HOMEPAGE("Home.fxml", new HomePageController()),
        EDITSHOPEELIST("Shopee.fxml", new ShopeeController());

        private final String fxml;
        private final AbstractController controller;

        Controllers(String fxml, AbstractController controller) {
            this.fxml = fxml;
            this.controller = controller;
        }

        public String getFxml() {
            return this.fxml;
        }

        public AbstractController getController() {
            return this.controller;
        }
    }

    public void setDataAccess(UserAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public UserAccess getDataAccess() {
        return this.dataAccess;
    }

    /**
     * This method changes the scene when beeing called upon.
     * 
     * @param controllerType
     * @param event
     * @param shopeeAccess
     */
    public void setScene(Controllers controllerType, Event event, UserAccess shopeeAccess) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            AbstractController controller = controllerType.getController();
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            loader.setLocation(ShopeeApp.class.getResource(controllerType.getFxml()));
            controller.setDataAccess(shopeeAccess);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
