package shopee.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * TestFX App test.
 */
public class ShopeeTest extends ApplicationTest {
    
    private User testUser = new User("Oskar@ntnu.no", "ExamplePassword123@");
    private FileHandeler fileHandeler = new FileHandeler();
    private ShopeeController controller = new ShopeeController();
    private UserAccess dataAccess;
  
        
    /**
    * Creates a local connection and sets a filepath to a file where a test user is added,
    * and a shopeeList which is used during testing adding, deleting and marking as bought. 
    *
    * @throws FileNotFoundException if files not found
    * @throws JsonProcessingException if something goes wrong when parsing to/from json.
    */
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
     * is in the shopping list.
     */
    @Test
     public void testAddFoodButtonClick() {
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

        assertEquals(8, controller.getUser()
        .getShopeeList("testlist").getFood("Water").getFoodAmount());
    }

    /*
     * This test checks if the list is updated when pressing
     * bought on a food item. 
     * The item should then be removed from the shoplist, 
     * and moved to the boght list.
     */
    @Test
    public void testMarkAsBought() {
        setUpTest();

        clickOn(LabeledMatchers.hasText("Water : 4"));
        clickOn("#foodBought");

        assertEquals("Water", controller.getBoughtListView().getItems().get(0).getFoodName());
        assertEquals(2, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Bread : 8"));
        clickOn("#foodBought");

        assertEquals("Bread", controller.getBoughtListView().getItems().get(1).getFoodName());
        assertEquals(1, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Egg : 12"));
        clickOn("#foodBought");

        assertEquals("Egg", controller.getBoughtListView().getItems().get(2).getFoodName());
        assertEquals(0, controller.getShoppingListView().getItems().size());
    }

    /*
     * This test checks if the list is updated when pressing
     * delete on a food item.
     */
    @Test
    public void testDeleteItems() {
        setUpTest();

        clickOn(LabeledMatchers.hasText("Water : 4"));
        clickOn("#removeFood");

        assertEquals(2, controller.getShoppingListView().getItems().size());

        clickOn(LabeledMatchers.hasText("Bread : 8"));
        clickOn("#removeFood");

        assertEquals(1, controller.getShoppingListView().getItems().size());
    }

    /**
    * Helper method for the other test that adds three new FoodItems to the 
    * testList added in the setUp method.
    */
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

