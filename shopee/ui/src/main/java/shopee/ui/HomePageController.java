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
import shopee.core.ShopeeList;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.ListView;
public class HomePageController {

@FXML private TextField listName;
@FXML private Button addList, logOut, deleteList, modifyList;
@FXML private ListView<ShopeeList> shoppingListView;

private User user;



public void setUser(User user) {
    this.user = user;
}

public void deleteList() {

}

public void modifyList() {

}



public void addNewList() {
    ShopeeList newList = newList.getText()
this.user.addShopeeList();
}

@FXML
public void showShoppingList(List<ShopeeList> listOfLists) {
    ObservableList<ShopeeList> shopeeList = FXCollections.observableArrayList(listOfList);
    shoppingListView.setItems(shopeeList);
}

public void logOut() {
    
}



private void loadShopeePage(ActionEvent actionEvent) {
    try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shopee.fxml"));
        Scene shopeeScene = new Scene(loader.load());

        ShopeeController shopeeController = loader.getController();
        shopeeController.setUser(this.user);

        Stage stage = (Stage) modifyList.getScene().getWindow();
        stage.setScene(shopeeScene);

        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    

    private void loadLoginPage(ActionEvent actionEvent) {
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
