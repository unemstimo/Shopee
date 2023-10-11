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


import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * TestFX App test
 */
public class ShopeeTest extends ApplicationTest {

    
    private Parent root;
    private User testUser;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Shopee.fxml"));
        root = fxmlLoader.load();
        this.testUser = exampleUser();
        ShopeeController controller = fxmlLoader.getController();
        controller.setUser(testUser);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Parent getRootNode() {
        return root;
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

        assertEquals("", lookup("#newFood").queryTextInputControl().getText());
        assertEquals("", lookup("#amountNewFood").queryTextInputControl().getText());
        ShopeeList s = testUser.getShopeeList();
        FoodItem item = new FoodItem("Pineapple", 2);
        assertEquals(2, s.getShopList().size());
        assertEquals(item.getFoodName(), s.getShopList().get(1).getFoodName());

    }

    /**
     * This test checks if the amount of a food item is updated when 
     *  a food item that is already in the shopping list is added to the list.
     */

    @Test
    public void testNewAmountWhenAddingSameFood() {
        ShopeeList s = testUser.getShopeeList();

        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("2");
        clickOn("#addFood");

        
        clickOn("#newFood").write("Water");
        clickOn("#amountNewFood").write("19");
        clickOn("#addFood");

        //size = 2 because i have already added 'apple' to the list
        assertEquals(2, s.getShopList().size());
        assertEquals(19, s.getShopList().get(1).getFoodAmount());
    }

    /**
     * Test method tests if a food item is removed from shopping list and placed
     * in the bought shopping list if a food item is marked as bought
     */

    @Test
    public void testMarkAsBoughtButtonClick() {
        ShopeeList s = testUser.getShopeeList();

        clickOn("#newFood").write("Chocolate");
        clickOn("#amountNewFood").write("3");
        clickOn("#addFood");

        // Simulate user interactions to mark the item as bought
        clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
        clickOn("#foodBought");
        assertEquals(1, s.getBoughtList().size());
        assertEquals(1, s.getShopList().size());
        assertEquals("Chocolate", s.getBoughtList().get(0).getFoodName());
    }

    /**
     * Test method tests if a food item is removed from the list 
     */
    
    @Test
    public void testRemoveItem() {
        ShopeeList s = testUser.getShopeeList();
        // Simulate user interactions to add an item
        clickOn("#newFood").write("Cheese");
        clickOn("#amountNewFood").write("2");
        clickOn("#addFood");

        //Test to see if it gets added to the list
        assertEquals(2, s.getShopList().size());
        assertEquals("Cheese", s.getShopList().get(1).getFoodName());

        // Simulate user interactions to remove the item
        clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
        clickOn("#removeFood");

        assertEquals(1, s.getShopList().size());
    }

    /**
     * Tests if the logIn.fxml is shown when log out button is clicked
     */

    @Test
    public void testLogOut() {
        clickOn("#logOut");
        
    }


    /**
     * Method to create a user which is used in all tests
     */
    public User exampleUser(){
        FileHandeler handler = new FileHandeler();
        handler.clearFileContent();
        this.testUser = new User("johan@ntnu.no", "Johan123@");
        ShopeeList sList = new ShopeeList("Test");
        sList.addFoodShopList("Apple", 4);
        // sList.addFoodShopList("Cheese", 2);
        // sList.addFoodShopList("Bread", 1);
        testUser.setShopeeList(sList);
        return testUser; 

     }



}
