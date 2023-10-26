package shopee.ui;


import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import javafx.collections.FXCollections;

public class ShopeeController extends AbstractController{
    
    /**
    * Using the empty constructor in the ShopeeList class to create a new shopping list. 
    * In this sprint, we dont name our shoppinglists, but instead we create one single shoppinglist to moderate. 
    * Now we can set and validate the input from the user.
    */
    
   
    private User user;
    private FileHandeler jsonFile = new FileHandeler();

    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood, logOut;
    @FXML private VBox shoppingListContainer;
    @FXML private ListView<FoodItem> shoppingListView;
    @FXML private ListView<FoodItem> boughtListView;

    public void setUser(User user){
        this.user = user;
        showShoppingList(user.getShopeeList().getShopList());
        showBoughtList(user.getShopeeList().getBoughtList());

    }
    

    public ShopeeList getShopList(){
        return this.user.getShopeeList();
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

        this.user.getShopeeList().addFoodShopList(food, amount);

        showShoppingList(this.user.getShopeeList().getShopList());
       
        newFood.clear();
        amountNewFood.clear();
        jsonFile.writeToFile(this.user); 
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
            FoodItem foodItem = user.getShopeeList().getFood(selectedIndex);
            user.getShopeeList().addFoodBoughtList(foodItem);
            jsonFile.writeToFile(user);
        }
        showShoppingList(user.getShopeeList().getShopList());
        showBoughtList(user.getShopeeList().getBoughtList());
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
            user.getShopeeList().removeFood(user.getShopeeList().getFood(selectedIndex).getFoodName());
        }
        jsonFile.writeToFile(user);
        showShoppingList(user.getShopeeList().getShopList());
    }

    /**
     * This method handles the action when user clicks on "log out" button
     * go back to first page where user can log in if wanted
     * @param actionEvent
     * loads LogIn.fxml
     */
    public void backToLogInPage(ActionEvent actionEvent) {
        try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        
        Scene logInScene = new Scene(loader.load());

        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setScene(logInScene);
        
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
