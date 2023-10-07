package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;


import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import core.FoodItem;

/**
 * TestFX App test
 */
public class ShopeeTest extends ApplicationTest {

    
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Shopee.fxml"));
        root = fxmlLoader.load();
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
        clickOn("#newFood").write("Apple");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");

        assertEquals("", lookup("#newFood").queryTextInputControl().getText());
        assertEquals("", lookup("#amountNewFood").queryTextInputControl().getText());

        ListView<FoodItem> shoppingListView = lookup("#shoppingListView").query();
        assertEquals(1, shoppingListView.getItems().size());
        assertEquals("Apple", shoppingListView.getItems().get(0).getFoodName());

    }

    /**
     * This test checks if the amount of a food item is updated when 
     *  a food item that is already in the shopping list is added to the list.
     */

    @Test
    public void testNewAmountWhenAddingSameFood() {
        
        clickOn("#newFood").write("Apple");
        clickOn("#amountNewFood").write("2");
        clickOn("#addFood");

        
        clickOn("#newFood").write("Apple");
        clickOn("#amountNewFood").write("1");
        clickOn("#addFood");

        
        ListView<FoodItem> shoppingListView = lookup("#shoppingListView").query();
        assertEquals(1, shoppingListView.getItems().size()); // Check if there is only one entry
        assertEquals("Apple", shoppingListView.getItems().get(0).getFoodName());
        assertEquals(3, shoppingListView.getItems().get(0).getFoodAmount()); // Check if the amount is updated to 3
    }

    /**
     * Test method tests if a food item is removed from shopping list and placed
     * in the bought shopping list if a food item is marked as bought
     */

    @Test
    public void testMarkAsBoughtButtonClick() {
        clickOn("#newFood").write("Chocolate");
        clickOn("#amountNewFood").write("3");
        clickOn("#addFood");

        // Simulate user interactions to mark the item as bought
        clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
        clickOn("#foodBought");

        
        ListView<FoodItem> shoppingListView = lookup("#shoppingListView").query();
        ListView<FoodItem> boughtListView = lookup("#boughtListView").query();
        assertEquals(1, shoppingListView.getItems().size()); // Check if item is removed from shopping list
        assertEquals(1, boughtListView.getItems().size()); // Check if item is added to the bought list
        assertEquals("Chocolate", boughtListView.getItems().get(0).getFoodName());
    }

    /**
     * Test method tests if a food item is removed from the list 
     */
    
    @Test
    public void testRemoveItem() {
        // Simulate user interactions to add an item
        clickOn("#newFood").write("Cheese");
        clickOn("#amountNewFood").write("2");
        clickOn("#addFood");

        // Simulate user interactions to remove the item
        clickOn("#shoppingListView").type(KeyCode.DOWN); // Select the added item
        clickOn("#removeFood");

        // Verify the result
        ListView<FoodItem> shoppingListView = lookup("#shoppingListView").query();
        assertEquals(0, shoppingListView.getItems().size()); // Check if item is removed from shopping list
    }

    /**
     * Tests if the logIn.fxml is shown when log out button is clicked
     */

    @Test
    public void testLogOut() {
        clickOn("#logOut");
        verifyThat("#usernameInput", isVisible()); 
        verifyThat("#passwordInput", isVisible()); 
        verifyThat("#signIn", isVisible()); 
        verifyThat("#signUp", isVisible());
        
    }




}
