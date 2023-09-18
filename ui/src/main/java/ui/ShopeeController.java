package ui;
import core.ShopeeList;
import core.FoodItem;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;

public class ShopeeController {
    
    /**
    * Using the empty constructor in the ShopeeList class to create a new shopping list. 
    * In this sprint, we dont name our shoppinglists, but instead we create one single shoppinglist to moderate. 
    * Now we can set and validate the input from the user.
    */

    private ShopeeList shopeeList = new ShopeeList(); 
    
    // Created an instance of the class HandleTxtFile to be able to read and write to file
    //private HandleTxtfile myTxtFileHandeler = new HandleTxtfile();

    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood;
    @FXML private VBox shoppingListContainer;
    @FXML private ListView<FoodItem> shoppingListView;


    /**
     * Collect the user input from food- and amount-textfield, and adds the food object to the shopping list.
     * 
     * @param event
     */
    
    @FXML
    public void handleAddFoodButtonClick(ActionEvent event) {

        String food = newFood.getText();
        int amount = Integer.parseInt(amountNewFood.getText());

        shopeeList.AddFood(food, amount);

        showShoppingList(shopeeList.getShoppingList());
        newFood.clear();
        amountNewFood.clear();
    }

    @FXML
    public void showShoppingList(List<FoodItem> listOfFoods) {
        ObservableList<FoodItem> foodsNotBought = FXCollections.observableArrayList(listOfFoods.stream().filter(l -> !l.isBought()).toList());
        shoppingListView.setItems(foodsNotBought);
    }

    @FXML 
    public void markAsBought(ActionEvent event) {
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            shopeeList.getFood(selectedIndex).setBought();
        }
        showShoppingList(shopeeList.getShoppingList());
    }

    @FXML
    public void removeItem(ActionEvent event) {
         int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            shopeeList.RemoveFood(shopeeList.getFood(selectedIndex).getFoodName());
        }
        showShoppingList(shopeeList.getShoppingList());
    }
    
}
