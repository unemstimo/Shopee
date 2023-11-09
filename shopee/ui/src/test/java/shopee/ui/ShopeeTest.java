package shopee.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.UserAccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * TestFX App test
 */
public class ShopeeTest extends ApplicationTest {

    
    private User testUser = new User("Oskar@ntnu.no", "Eksempelpassors123@");
    private FileHandeler fileHandeler = new FileHandeler();


    private ShopeeController controller = new ShopeeController();
    private UserAccess dataAccess;
  
    
  
    // @BeforeAll
    // public void rigup() throws FileNotFoundException {
    //     this.testUser = exampleUser();
    // }
  
    @BeforeEach
    public void setUp() throws FileNotFoundException, JsonProcessingException {
      this.dataAccess = new LocalUserAccess();
      this.fileHandeler.setFilePath("direct.json");
      fileHandeler.clearFileContent();
      this.testUser.addShopeeList(new ShopeeList("testlist"));
      this.dataAccess.addUser(testUser);
      controller.initData(this.testUser, "testlist", this.dataAccess);  
  
    }




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        fxmlLoader.setLocation(this.getClass().getResource("Shopee.fxml"));
        final Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Test for the behavior and simulate the user interactions
     * Method test if text fields is clear after adding a food item
     * Also tests if the food item written in the text field 
     * is in the shopping list
     */

    @Test
    public void testAddFoodButtonClick(){
        clickOn("#newFood").write("Pineapple");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");

        assertEquals("Pineapple", controller.getShoppingListView().getItems().get(0).getFoodName());

        clickOn("#newFood").write("Æ@@@@@#");
        clickOn("#amountNewFood").write("8");
        clickOn("#addFood");

        assertEquals("The food name can only contain letters.", controller.getErrorOutput());
        this.controller.clearInputFields();
        
        clickOn("#newFood").write("Egg");
        clickOn("#amountNewFood").write("-5");
        clickOn("#addFood");

        assertEquals("The amount of food needs to be 1 or higher", controller.getErrorOutput());
       
    }

    /**
     * This test checks if the amount of a food item is updated when 
     *  a food item that is already in the shopping list is added to the list.
     */

    @Test
    public void testNewAmountWhenAddingSameFood() {
        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");

        assertEquals(4, controller.getShoppingListView().getItems().get(0).getFoodAmount());

        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("8");
        clickOn("#addFood");

        assertEquals(8, controller.getUser().getShopeeList("testlist").getFood("Water").getFoodAmount());

    }

    @Test
    public void testMarkAsBought() {
        setUpTest();

        clickOn(LabeledMatchers.hasText("Water : 4  STK"));
        clickOn("#foodBought");

        assertEquals("Water", controller.getBoughtListView().getItems().get(0).getFoodName());
        assertEquals(2, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Bread : 8  STK"));
        clickOn("#foodBought");

        assertEquals("Bread", controller.getBoughtListView().getItems().get(1).getFoodName());
        assertEquals(1, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Egg : 12  STK"));
        clickOn("#foodBought");

        assertEquals("Egg", controller.getBoughtListView().getItems().get(2).getFoodName());
        assertEquals(0, controller.getShoppingListView().getItems().size());


    }

    @Test
    public void testDeleteItems() {
        setUpTest();

        clickOn(LabeledMatchers.hasText("Water : 4  STK"));
        clickOn("#removeFood");

        assertEquals(2, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Bread : 8  STK"));
        clickOn("#removeFood");

        assertEquals(1, controller.getShoppingListView().getItems().size());

    }


    public void setUpTest() {
        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");

        clickOn("#newFood").write("Bread");
        clickOn("#amountNewFood").write("8");
        clickOn("#addFood");

        clickOn("#newFood").write("Egg");
        clickOn("#amountNewFood").write("12");
        clickOn("#addFood");

    }


    




    
}
