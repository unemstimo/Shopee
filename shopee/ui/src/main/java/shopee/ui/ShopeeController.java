package shopee.ui;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.ui.dataaccess.UserAccess;
import javafx.collections.FXCollections;

public class ShopeeController extends AbstractController{
    
    /**
    * Using the empty constructor in the ShopeeList class to create a new shopping list. 
    * In this sprint, we dont name our shoppinglists, but instead we create one single shoppinglist to moderate. 
    * Now we can set and validate the input from the user.
    */
    
    @FXML private TextField newFood, amountNewFood; 
    @FXML private Button addFood, foodBought, removeFood, back;
    @FXML private VBox shoppingListContainer;
    @FXML private ListView<FoodItem> shoppingListView;
    @FXML private ListView<FoodItem> boughtListView;
    @FXML private Label outPut;

    private User user;
    private UserAccess dataAccess;
    private ShopeeList shopeeList;

    /**
     * Method for inserting the date read from file to this user if there is any data
     *
     * @throws FileNotFoundException  if file is not found
     */
    public void initData(User user, String Listname, UserAccess access) {
        this.user = user;
        this.dataAccess = access;
        this.shopeeList = user.getShopeeList(Listname);
        this.showShoppingList(shopeeList.getShopList());
        this.showBoughtList(shopeeList.getBoughtList());
    }
    
    /**
     * Collect the user input from food- and amount-textfield, and adds the food object to the shopping list.
     * 
     * @param event
     */
    @FXML
    public void handleAddFoodButtonClick(ActionEvent event) throws JsonProcessingException{

        String food = newFood.getText();
        int amount = Integer.parseInt(amountNewFood.getText());
        try {
            this.shopeeList.addFoodShopList(food, amount);
            showShoppingList(this.shopeeList.getShopList());
            newFood.clear();
            amountNewFood.clear();
        } catch (Exception e) {
            outPut.setText("Food is not valid");
        }
       
    }

    /**
     * Sets the selected food object from the shopping list as bought and moves it to the bought list
     * 
     * @param event
     */
    @FXML 
    public void markAsBought(ActionEvent event) throws JsonProcessingException{
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            FoodItem foodItem = this.shopeeList.getShopList().get(selectedIndex);
            this.shopeeList.addFoodBoughtList(foodItem);
        }

        showShoppingList(this.shopeeList.getShopList());
        showBoughtList(this.shopeeList.getBoughtList());
    }

    /**
     * Removes selected food item from the shopping list
     * 
     * @param event
     */
    @FXML
    public void removeItem(ActionEvent event) throws JsonProcessingException{
         int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            this.shopeeList.removeFood(this.shopeeList.getShopList().get(selectedIndex).getFoodName());
        }
        showShoppingList(this.shopeeList.getShopList());
    }

    /**
     * This method handles the action when user clicks on "go back" button
     * go back to home page where user can log in if wanted
     * @param actionEvent
     * loads Home.fxml
     */

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
     * this method handles the action when user clicks on "go back" button and goes back to home page
     * 
     * @param actionevent
     * @throws JsonProcessingException
     */
    public void backToShoppingList(ActionEvent actionevent) throws JsonProcessingException {
        this.dataAccess.deleteShopeeList(this.user.getUsername(), this.shopeeList.getListName());
        this.dataAccess.addShopeeList(this.user.getUsername(), this.shopeeList);

        setScene(Controllers.HOMEPAGE, actionevent, dataAccess, user, null); 
    }

    public ListView<FoodItem> getShoppingListView() {
        return shoppingListView;
    }

    public ListView<FoodItem> getBoughtListView() {
        return boughtListView;
    }
    
    public String getErrorOutput() {
        return outPut.getText();
    }

    public User getUser() {
        return this.user;
    }

}