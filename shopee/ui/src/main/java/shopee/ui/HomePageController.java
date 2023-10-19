package shopee.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shopee.core.User;

import java.io.IOException;
public class HomePageController {

@FXML private TextField listName;
@FXML private Button addList, logOut, deleteList, modifyList;

private User user;



public void setUser(User user) {
    this.user = user;
}


private void loadNewPage(ActionEvent actionEvent) {
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
    
}
