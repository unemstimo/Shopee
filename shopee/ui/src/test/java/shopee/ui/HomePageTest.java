package shopee.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.UserAccess;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import com.fasterxml.jackson.core.JsonProcessingException;


public class HomePageTest extends ApplicationTest{

    private User testUser = new User("Oskar@ntnu.no", "Eksempelpassors123@");
    private FileHandeler fileHandeler = new FileHandeler("direct.json");


    private HomePageController controller = new HomePageController();
    private UserAccess dataAccess;
  
    
  
    
  
    @BeforeEach
    public void setUp() throws FileNotFoundException, JsonProcessingException {
      this.dataAccess = new LocalUserAccess();
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
     * Tests that the user creates and adds new lists
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
    }


    /**
     * Tests that the user can delete lists
     * @throws JsonProcessingException
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

    }

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

}
