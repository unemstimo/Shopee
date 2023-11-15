package shopee.ui;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shopee.core.User;
import shopee.ui.dataaccess.UserAccess;

/**
 *  An abstract controller sets the scene and controller depending on the input in the 
 *  setScene method, used in all controllers.
 */
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
    * @param controllerType either LogIn-, HomePage- or ShopeeController .
    * @param event when you switch between the different pages
    * @param shopeeAccess either local or remote, depending on the server status
    */
    public void setScene(Controllers controllerType, Event event,
            UserAccess shopeeAccess, User user, String listName) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            AbstractController controller = controllerType.getController();
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            loader.setLocation(ShopeeApp.class.getResource(controllerType.getFxml()));
            Parent parent = loader.load();

            if (controller instanceof LogInController) {
                ((LogInController) controller).initData(shopeeAccess);
            }
            
            if (controller instanceof HomePageController) {
                ((HomePageController) controller).initData(user, shopeeAccess);
            } 
            
            if (controller instanceof ShopeeController) {
                ((ShopeeController) controller).initData(user, listName, shopeeAccess);
            }

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
