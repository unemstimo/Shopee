package shopee.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class HomePageTest extends ApplicationTest{

    private Parent root;
    private User testUser;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Home.fxml"));
        root = fxmlLoader.load();
        this.testUser = exampleUser();
        HomePageController controller = fxmlLoader.getController();
        controller.setUser(testUser);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Tests that the user creates and adds new lists
     */
    @Test
    public void createNewShopeeList() {
        assertEquals("", lookup("#listName").queryTextInputControl().getText());

        clickOn("#listName").write("Uke 40");
        clickOn("#addList");

        assertEquals("Uke 40", this.testUser.getShopeeLists().get(0).getListName());
    }


    public User exampleUser(){
        FileHandeler handler = new FileHandeler();
        handler.clearFileContent();
        this.testUser = new User("johan@ntnu.no", "Johan123@");
        return testUser; 
     }


}
