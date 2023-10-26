package shopee.ui;


import java.io.IOException;
import java.util.ArrayList;
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
    private int index; 

    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood, back;
    @FXML private VBox shoppingListContainer;
    @FXML private ListView<FoodItem> shoppingListView;
    @FXML private ListView<FoodItem> boughtListView;

    public void setUser(User user, int index){
        this.user = user;
        this.index = index;
        showShoppingList(this.getShopeeList().getShopList());
        showBoughtList(this.getShopeeList().getBoughtList());

    }
    

    public ShopeeList getShopeeList(){
        return this.user.getShopeeLists().get(this.index);
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

        this.getShopeeList().addFoodShopList(food, amount);

        showShoppingList(this.getShopeeList().getShopList());
       
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
            FoodItem foodItem = this.getShopeeList().getShopList().get(selectedIndex);
            this.getShopeeList().addFoodBoughtList(foodItem);
            jsonFile.writeToFile(user);
        }
        showShoppingList(this.getShopeeList().getShopList());
        showBoughtList(this.getShopeeList().getBoughtList());
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
            this.getShopeeList().removeFood(this.getShopeeList().getShopList().get(selectedIndex).getFoodName());
        }
        jsonFile.writeToFile(user);
        showShoppingList(this.getShopeeList().getShopList());
    }

    /**
     * This method handles the action when user clicks on "go back" button
     * go back to home page where user can log in if wanted
     * @param actionEvent
     * loads Home.fxml
     */
    

    public void backToShoppingList(ActionEvent actionEvent) {
    try{ 
        showShoppingList(new ArrayList<>());
        showBoughtList(new ArrayList<>());
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Scene homeScene = new Scene(loader.load());

        HomePageController homepage = loader.getController();
        homepage.setUser(this.user);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(homeScene);
        
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
