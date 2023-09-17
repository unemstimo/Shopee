package ui;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import core.ShopeeList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ShopeeController {
    
    /**
    * Using the empty constructor in the ShopeeList class to create a new shopping list. 
    * In this sprint, we dont name our shoppinglists, but instead we create one single shoppinglist to moderate. 
    * Now we can set and validate the input from the user.
    */

    private ShopeeList shopeeList = new ShopeeList(); 
    
    // Created an instance of the class HandleTxtFile to be able to read and write to file
    private HandleTxtFile myTxtFileHandeler = new HandleTxtFile();

    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood;
    @FXML private TextArea displayShoppingList;
    @FXML private VBox roomContainer;

    /**
     * Collect the user input from food- and amount-textfield, and adds the food item to the shopping list.
     * 
     * @param event
     */
    
    @FXML
    public void handleAddFoodButtonClick(ActionEvent event) {

        String food = newFood.getText();
        int amount = Integer.parseInt(amountNewFood.getText());

        shopeeList.AddFood(food, amount);

        showShoppingList();
    }

    @FXML
    public void showShoppingList() {

    }

    @FXML 
    public void handleFoodBoughtButtonClick(ActionEvent event) {

        
    }
    
}
