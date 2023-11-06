package shopee.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shopee.core.FoodItem;
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

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * TestFX App test
 */
public class ShopeeTest extends ApplicationTest {

    
    private User testUser = new User("Oskar@ntnu.no", "Eksempelpassors123@");
    private FileHandeler fileHandeler = new FileHandeler("direct.json");


    private ShopeeController controller = new ShopeeController();
    private UserAccess dataAccess;
  
    
  
    // @BeforeAll
    // public void rigup() throws FileNotFoundException {
    //     this.testUser = exampleUser();
    // }
  
    @BeforeEach
    public void setUp() throws FileNotFoundException, JsonProcessingException {
      this.dataAccess = new LocalUserAccess();
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

        assertEquals("Pineapple", controller.getShoppingListView().getItems().get(0));

        clickOn("#newFood").write("Pineapple");
        clickOn("#amountNewFood").write("8");
        clickOn("#addFood");

        assertEquals(8, controller.getUser().getShopeeList("testlist").getFood("Pineapple").getFoodAmount());

        clickOn("#newFood").write("Æ@@@@@#");
        clickOn("#amountNewFood").write("8");
        clickOn("#addFood");

        assertEquals("Food is not valid", controller.getErrorOutput());
        
        //Teste negativt tall?
    }

    /**
     * This test checks if the amount of a food item is updated when 
     *  a food item that is already in the shopping list is added to the list.
     */

    @Test
    public void testNewAmountWhenAddingSameFood() {
        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("2");
        clickOn("#addFood");

        ShopeeList list1 = this.dataAccess.getAllUsers().get(0).getShopeeList("testlist");
        assertEquals(2, list1.getFood("Water").getFoodAmount());

        
        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("19");
        clickOn("#addFood");

        ShopeeList list2 = this.dataAccess.getAllUsers().get(0).getShopeeList("testlist");
        assertEquals(19, list2.getFood("Water").getFoodAmount());
    }

    /**
     * Test method tests if a food item is removed from shopping list and placed
     * in the bought shopping list if a food item is marked as bought
     */

    // @Test
    // public void testMarkAsBoughtButtonClick() {
    //     ShopeeList s = testUser.getShopeeList();

    //     clickOn("#newFood").write("Chocolate");
    //     clickOn("#amountNewFood").write("3");
    //     clickOn("#addFood");

    //     // Simulate user interactions to mark the item as bought
    //     clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
    //     clickOn("#foodBought");
    //     assertEquals(1, s.getBoughtList().size());
    //     assertEquals(1, s.getShopList().size());
    //     assertEquals("Chocolate", s.getBoughtList().get(0).getFoodName());
    // }

    // /**
    //  * Test method tests if a food item is removed from the list 
    //  */
    
    // @Test
    // public void testRemoveItem() {
    //     ShopeeList s = testUser.getShopeeList();
    //     // Simulate user interactions to add an item
    //     clickOn("#newFood").write("Cheese");
    //     clickOn("#amountNewFood").write("2");
    //     clickOn("#addFood");

    //     //Test to see if it gets added to the list
    //     assertEquals(2, s.getShopList().size());
    //     assertEquals("Cheese", s.getShopList().get(1).getFoodName());

    //     // Simulate user interactions to remove the item
    //     clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
    //     clickOn("#removeFood");

    //     assertEquals(1, s.getShopList().size());
    // }

    

    /**
     * Method to create a user which is used in all tests
     */
    // public User exampleUser(){
    //     FileHandeler handler = new FileHandeler();
    //     handler.clearFileContent();
    //     this.testUser = new User("johan@ntnu.no", "Johan123@");
    //     ShopeeList sList = new ShopeeList("Test");
    //     sList.addFoodShopList("Apple", 4);
    //     // sList.addFoodShopList("Cheese", 2);
    //     // sList.addFoodShopList("Bread", 1);
    //     testUser.setShopeeList(sList);
    //     return testUser; 

    //  }



}
