package ui;
import core.ShopeeList;
import core.User;
import core.Storage.ReadFromFile;
import core.FoodItem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;

public class ShopeeController implements Initializable {
    
    /**
    * Using the empty constructor in the ShopeeList class to create a new shopping list. 
    * In this sprint, we dont name our shoppinglists, but instead we create one single shoppinglist to moderate. 
    * Now we can set and validate the input from the user.
    */
    
    private ReadFromFile fileReader = new ReadFromFile();
    private ShopeeList shopeeList = fileReader.filereader();
    private User user;

    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood;
    @FXML private VBox shoppingListContainer;
    @FXML private ListView<FoodItem> shoppingListView;
    @FXML private ListView<FoodItem> boughtListView;

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showShoppingList(shopeeList.getShopList());
        showBoughtList(shopeeList.getBoughtList());
    }


    /**
     * Collect the user input from food- and amount-textfield, and adds the food object to the shopping list.
     * 
     * @param event
     */
    @FXML
    public void handleAddFoodButtonClick(ActionEvent event) {

        String food = newFood.getText();
        int amount = Integer.parseInt(amountNewFood.getText());

        shopeeList.addFoodShopList(food, amount);

        showShoppingList(shopeeList.getShopList());
        newFood.clear();
        amountNewFood.clear();
    }


    /**
     * Updates the shopping list- ListView with the list-parameter in the UI
     * 
     * @param listOfFoods
     */
    @FXML
    public void showShoppingList(List<FoodItem> listOfFoods) {
        ObservableList<FoodItem> foodList = FXCollections.observableArrayList(listOfFoods);
        shoppingListView.setItems(foodList);
    }

    /**
     * Updates the bought list- ListView with the list-parameter in the UI
     * 
     * @param listOfBoughtFoods
     */
    @FXML
    public void showBoughtList(List<FoodItem> listOfBoughtFoods) {
        ObservableList<FoodItem> foodList = FXCollections.observableArrayList(listOfBoughtFoods);
        boughtListView.setItems(foodList);
    }

    /**
     * Sets the selected food object from the shopping list as bought and moves it to the bought list
     * 
     * @param event
     */
    @FXML 
    public void markAsBought(ActionEvent event) {
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            FoodItem foodItem = shopeeList.getFood(selectedIndex);
            shopeeList.addFoodBoughtList(foodItem);
        }
        showShoppingList(shopeeList.getShopList());
        showBoughtList(shopeeList.getBoughtList());
    }

    /**
     * Removes selected food item from the shopping list
     * 
     * @param event
     */
    @FXML
    public void removeItem(ActionEvent event) {
         int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            shopeeList.removeFood(shopeeList.getFood(selectedIndex).getFoodName());
        }
        showShoppingList(shopeeList.getShopList());
    }

    
}
