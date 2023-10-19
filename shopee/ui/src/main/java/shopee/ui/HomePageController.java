package shopee.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.core.ShopeeList;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
public class HomePageController {

@FXML private TextField listName;
@FXML private Button addList, logOut, deleteList, modifyList;
@FXML private ListView<ShopeeList> shoppingListView;
@FXML private Label output;

private User user;
private int index;
private FileHandeler jsonFile = new FileHandeler();



public void setUser(User user) {
    this.user = user;
    shoppingListView(user.getShopeeLists());
}

public void deleteList() {
    int selectedIndex = shoppingListView.getSelectionModel().getSelectedIndex();
    if(selectedIndex >= 0) {
        this.user.deleteShopeeList(selectedIndex);
        shoppingListView(user.getShopeeLists());
        jsonFile.writeToFile(user);
    }
    else {
        output.setText("Funke itj");
    }
    
}

public void modifyList() {
    this.index = shoppingListView.getSelectionModel().getSelectedIndex();
    if(index >= 0){
        loadShopeePage(new ActionEvent());
    } else{
        output.setText("Wrong index");
    }

}



public void addNewList() {
    try {
        String newList = listName.getText();
        this.user.addShopeeList(new ShopeeList(newList));
        shoppingListView(this.user.getShopeeLists());
        this.index = this.user.getShopeeLists().size() - 1;
        listName.clear();
        loadShopeePage(new ActionEvent());
        jsonFile.writeToFile(user);
    
    } catch (Exception e) {
        e.printStackTrace();
        output.setText("Something went wrong");
    }
}

@FXML
public void shoppingListView(List<ShopeeList> listOfLists) {
    ObservableList<ShopeeList> foodList = FXCollections.observableArrayList(listOfLists);
    shoppingListView.setItems(foodList);
}

public void logOut() {
    loadLogIn(new ActionEvent());
}



private void loadShopeePage(ActionEvent actionEvent) {
    try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shopee.fxml"));
        Scene shopeeScene = new Scene(loader.load());

        ShopeeController shopeeController = loader.getController();
        shopeeController.setUser(this.user, this.index);

        Stage stage = (Stage) modifyList.getScene().getWindow();
        stage.setScene(shopeeScene);

        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    

private void loadLogIn(ActionEvent actionEvent) {
    try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene LoginScene = new Scene(loader.load());

        Stage stage = (Stage) modifyList.getScene().getWindow();
        stage.setScene(LoginScene);

        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
