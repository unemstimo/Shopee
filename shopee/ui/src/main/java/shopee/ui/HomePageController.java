package shopee.ui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.ui.dataaccess.UserAccess;

/**
 * Class that creates the home page, the second layer in out application, handles the List 
 * of ShopeeList for the logged in user.
 */
public class HomePageController extends AbstractController {

    @FXML 
    private TextField listName;
    @FXML
    private Button addList;
    @FXML
    private Button logOut;
    @FXML
    private Button deleteList;
    @FXML
    private Button modifyList;
    @FXML
    private ListView<ShopeeList> shoppingListView;
    @FXML private Label output;

    private User user;
    private UserAccess dataAccess;

    /**
     * Method for inserting the date read from file to this user if there is any data.
     *
     * @throws FileNotFoundException  if file is not found
     */
    public void initData(User user, UserAccess access) {
        this.user = user;
        this.dataAccess = access;
        shoppingListView(this.user.getShopeeLists());
    }

    /**
     * Method for removing list for a User. 
     */
    public void deleteList() {
        int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String username = this.user.getUsername();
            String listName = this.user.getShopeeLists().get(selectedIndex).getListName();

            // deletes first from the database
            this.dataAccess.deleteShopeeList(username, listName);

            // then deletes from the local user
            this.user.deleteShopeeList(listName);

            // Updates the vieuw
            shoppingListView(user.getShopeeLists());

        } else {
            output.setText("Failed to delete list");
        } 
    }

    /**
     * After selecting a list in the application and click "modify list", this 
     * method handles loading to the shopee scene and changes controller.
     *
     * @param actionEvent when clicking "modify list"
     */
    public void modifyList(ActionEvent actionEvent) {
        int index = shoppingListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String listname = this.user.getShopeeLists().get(index).getListName();
            setScene(Controllers.EDITSHOPEELIST, actionEvent, this.dataAccess, this.user, listname);
        } else {
            output.setText("Wrong index");
        }
    }

    /**
     * Writing a listname in home page and click "add list", this 
     * method adds the list to the user if the name is not taken, and updates
     * the page so it displays on the application.
     *
     * @param actionEvent when clicking "Add List"
     */
    public void addNewList(ActionEvent actionEvent) {
        try {
            String newList = listName.getText();
            this.user.addShopeeList(new ShopeeList(newList));
            shoppingListView(this.user.getShopeeLists());

            // Adds shopeelist to the user, and updates to file
            this.dataAccess.addShopeeList(this.user.getUsername(), new ShopeeList(newList)); 
            listName.clear();

            setScene(Controllers.EDITSHOPEELIST, actionEvent, this.dataAccess, this.user, newList);
        
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    @FXML
    public void shoppingListView(List<ShopeeList> listOfLists) {
        ObservableList<ShopeeList> foodList = FXCollections.observableArrayList(listOfLists);
        shoppingListView.setItems(foodList);
    }

    public void logOut(ActionEvent actionEvent) {
        setScene(Controllers.LOGIN, actionEvent, this.dataAccess, null, null);
    }

    // public String getErroroutput() {
    //     return output.getText();
    // }
}
