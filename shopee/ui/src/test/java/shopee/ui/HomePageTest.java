package shopee.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.UserAccess;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;


public class HomePageTest extends ApplicationTest{

    private User testUser = new User("Oskar@ntnu.no", "Eksempelpassors123@");
    private FileHandeler fileHandeler = new FileHandeler("direct.json");


    private HomePageController controller = new HomePageController();
    private UserAccess dataAccess;
  
    
  
    // @BeforeAll
    // public void rigup() throws FileNotFoundException {
    //     this.testUser = exampleUser();
    // }
  
    @BeforeEach
    public void setUp() throws FileNotFoundException {
      this.dataAccess = new LocalUserAccess();
      fileHandeler.clearFileContent();
      controller.initData(this.testUser, this.dataAccess);  
  
    }



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        fxmlLoader.setLocation(this.getClass().getResource("Home.fxml"));
        final Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
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
        
        assertEquals("", lookup("#listName").queryTextInputControl().getText());

        clickOn("#listName").write("Uke 41");
        clickOn("#addList");

        assertEquals("Uke 41", this.testUser.getShopeeLists().get(1).getListName());

    }


    /**
     * Tests that the user can delete lists
     */
    @Test
    public void deleteShopeeList() {
        clickOn("#shoppingListView").type(KeyCode.DOWN);
        clickOn("#deleteList");

        //tries to get the shopeelist that is deleted, should throw
        try {
            testUser.getShopeeList("Uke 41"); //might be "Uke 40"
        } catch (Exception e) {
            assertEquals("No such list name for this user", e.getMessage());
        }  

    }


    /**
     * Tests that the user is able to modify on of its existing shopee lists
     */
    @Test
    public void modifyShopeeList() {
        clickOn("#shoppingListView").type(KeyCode.DOWN);
        clickOn("#modifyList");

        WaitForAsyncUtils.waitForFxEvents();

        Node modifyShopeeElement = lookup("#newFood").query();
        assertNotNull(modifyShopeeElement);

        clickOn("#newFood").write("fisk");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");

        clickOn("#back");
        
        Node homepageElement = lookup("#listName").query();
        assertNotNull(homepageElement);

        int lenght = testUser.getShopeeLists().get(0).getShopList().size();

        assertNotEquals(0, lenght);

    }

    /**
     * Makes a User that will be the used in the tests
     * 
     * @return User
     */
    public User exampleUser(){
        return new User("johan@ntnu.no", "Johan123@");  
     }

     


}
