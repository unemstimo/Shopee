package shopee.ui;


import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javafx.collections.FXCollections;
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

/**
 * Controller for the innermost layer of out application, 
 * handles action of modifying a list.
 */
public class ShopeeController extends AbstractController {
    
    /**
     * Using the empty constructor in the ShopeeList class to create a new shopping
     * list.
     * Now we can set and validate the input from the user.
     */
    @FXML
    private TextField newFood;
    @FXML
    private TextField amountNewFood;
    @FXML
    private Button addFood;
    @FXML
    private Button foodBought;
    @FXML
    private Button removeFood;
    @FXML
    private Button back;
    @FXML
    private VBox shoppingListContainer;
    @FXML 
    private ListView<FoodItem> shoppingListView;
    @FXML 
    private ListView<FoodItem> boughtListView;
    @FXML 
    private Label outPut;

    private User user;
    private UserAccess dataAccess;
    private ShopeeList shopeeList;

    /**
     * Method responsible for setting up the page and loading the list that was selected
     * int the homepage controller.
     *
     * @param user the user logged in and is currently modifying its lists
     * @param listName the name of the list which is supposed to be displayed
     * @param access sets the access local or remote depending on the server status
     */
    public void initData(User user, String listName, UserAccess access) {
        this.user = user;
        this.dataAccess = access;
        this.shopeeList = user.getShopeeList(listName);
        this.showShoppingList(shopeeList.getShopList());
        this.showBoughtList(shopeeList.getBoughtList());
    }
    
    /**
     * Collect the user input from food- and amount-textfield, 
     * and adds the food object to the shopping list.
     *
     * @param event when the "add" button is the fooditem is added
     */
    @FXML
    public void handleAddFoodButtonClick(ActionEvent event) throws JsonProcessingException {
        try {
            String food = newFood.getText();
            int amount = Integer.parseInt(amountNewFood.getText());

            try {
                this.shopeeList.addFoodShopList(food, amount);
                showShoppingList(this.shopeeList.getShopList());
                newFood.clear();
                amountNewFood.clear();
                outPut.setText("");
            } catch (Exception e) {
                outPut.setText(e.getMessage());
            }

        } catch (Exception e) {
            outPut.setText("Input fields are not valid");
        }
    }

    /**
     * Sets the selected food object from the shopping list as bought and 
     * moves it to the bought list.
     *
     * @param event when the "marked as bought" button is clicked
     */
    @FXML 
    public void markAsBought(ActionEvent event) throws JsonProcessingException {
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            FoodItem foodItem = this.shopeeList.getShopList().get(selectedIndex);
            this.shopeeList.addFoodBoughtList(foodItem);
        }

        showShoppingList(this.shopeeList.getShopList());
        showBoughtList(this.shopeeList.getBoughtList());
    }

    /**
     * Removes a selected food item from the shopping list.
     *
     * @param event when the "remove from shoplist" button is clicked
     */
    @FXML
    public void removeItem(ActionEvent event) throws JsonProcessingException {
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.shopeeList.removeFood(this.shopeeList.getShopList()
                .get(selectedIndex).getFoodName());
        }
        showShoppingList(this.shopeeList.getShopList());
    }

    /**
     * Updates the shopping list- ListView with the list-parameter in the UI.
     *
     * @param listOfFoods displays all FoodItems in the shopList 
     */
    @FXML
    public void showShoppingList(List<FoodItem> listOfFoods) {
        ObservableList<FoodItem> foodList = FXCollections.observableArrayList(listOfFoods);
        shoppingListView.setItems(foodList);
    }

    /**
     * Updates the shopping list- ListView with the list-parameter in the UI.
     *
     * @param listOfBoughtFoods displays all FoodItems in the ShopeeList boughtList
     */
    @FXML
    public void showBoughtList(List<FoodItem> listOfBoughtFoods) {
        ObservableList<FoodItem> foodList = FXCollections.observableArrayList(listOfBoughtFoods);
        boughtListView.setItems(foodList);
    }

    /**
     * this method handles the action when user clicks on "go back" 
     * button and goes back to home page.
     *
     * @param actionevent displays all FoodItems in the ShopeeList boughtList
     * @throws JsonProcessingException if theres an issue parsing to/from json
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

    public void clearInputFields() {
        newFood.clear();
        amountNewFood.clear();
    }

}