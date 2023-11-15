package shopee.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.UserAccess;

/**
 *  Test for the HomepageController, the second controller out of three in ui.
 */
public class HomePageTest extends ApplicationTest {

    private User testUser = new User("Oskar@ntnu.no", "Eksempelpassors123@");
    private FileHandeler fileHandeler = new FileHandeler();
    private HomePageController controller = new HomePageController();
    private UserAccess dataAccess;  
  
    /**
    * Sets up a the controller to be linked with the local stored file, just for testing
    * and adds a user to the file used in tests.
    *
    * @throws JsonProcessingException if theres a problem when parsing to/from json
    * @throws FileNotFoundException if files not found
    */
    @BeforeEach
    public void setUp() throws JsonProcessingException, FileNotFoundException  {
        this.dataAccess = new LocalUserAccess();
        this.fileHandeler.setFilePath("direct.json");
        fileHandeler.clearFileContent();
        this.dataAccess.addUser(testUser);
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
     * Tests that the user creates and adds new lists.
     */
    @Test
    public void createNewShopeeList() {
        assertEquals("", lookup("#listName").queryTextInputControl().getText());

        clickOn("#listName").write("Uke 40");
        clickOn("#addList");


        ShopeeList list1 = this.dataAccess.getAllUsers().get(0).getShopeeLists().get(0);
        assertEquals("Uke 40", list1.getListName());

        clickOn(LabeledMatchers.hasText("Back"));

        assertEquals("", lookup("#listName").queryTextInputControl().getText());

        clickOn("#listName").write("Uke 41");
        clickOn("#addList");


        ShopeeList list2 = this.dataAccess.getAllUsers().get(0).getShopeeLists().get(1);
        assertEquals("Uke 41", list2.getListName());

        clickOn(LabeledMatchers.hasText("Back"));

        clickOn("#listName").write("Uke@@@@@@");
        clickOn("#addList");

    }

    /**
    * Tests that the user can delete lists.
    *
    * @throws JsonProcessingException if theres an issue when parsing to/from json
    */
    @Test
    public void deleteShopeeList() throws JsonProcessingException {
        setUpDeletetest();

        List<ShopeeList> list1 = dataAccess.getAllUsers().get(0).getShopeeLists();
        assertEquals(4, list1.size());

        clickOn(LabeledMatchers.hasText("Middag"));
        clickOn("#deleteList");
        
        List<ShopeeList> list2 = dataAccess.getAllUsers().get(0).getShopeeLists();
        assertEquals(3, list2.size());

        clickOn(LabeledMatchers.hasText("Lunsj"));
        clickOn("#deleteList");
        
        List<ShopeeList> list3 = dataAccess.getAllUsers().get(0).getShopeeLists();
        assertEquals(2, list3.size());

        clickOn(LabeledMatchers.hasText("Frokost"));
        clickOn("#deleteList");
        
        List<ShopeeList> list4 = dataAccess.getAllUsers().get(0).getShopeeLists();
        assertEquals(1, list4.size());

        clickOn("#shoppingListView");
        clickOn("#deleteList");

    }

    /**
    * helper method for the test deleteShopeeList().
    */
    public void setUpDeletetest() {
        clickOn("#listName").write("Middag");
        clickOn("#addList");
        clickOn(LabeledMatchers.hasText("Back"));
        clickOn("#listName").write("Lunsj");
        clickOn("#addList");
        clickOn(LabeledMatchers.hasText("Back"));
        clickOn("#listName").write("Dessert");
        clickOn("#addList");
        clickOn(LabeledMatchers.hasText("Back"));
        clickOn("#listName").write("Frokost");
        clickOn("#addList");
        clickOn(LabeledMatchers.hasText("Back"));
    }

    @Test
    public void modifyList() {
        clickOn("#listName").write("Middag");
        clickOn("#addList");
        clickOn(LabeledMatchers.hasText("Back"));

        clickOn(LabeledMatchers.hasText("Middag"));
        clickOn("#modifyList");
        clickOn(LabeledMatchers.hasText("Back"));

        clickOn("#shoppingListView");
        clickOn("#modifyList");
    }

    @Test
    public void logouttest() {
        clickOn("#logOut");
    }
}


