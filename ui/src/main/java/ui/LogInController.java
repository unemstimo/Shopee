package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController implements Initializable {

@FXML private TextField usernameInput, passwordInput;
@FXML private Button signIn, signUp;
@FXML private Label output;

private String username;
private String password;
private User user;

private void readTextFields(){
    username = usernameInput.getText();
    password = passwordInput.getText();
}


@FXML 
public void handleSignUpButtonClick (ActionEvent event){
    readTextFields();
    
    //go through file to check if the user already exist
    //if user exist:
    output.setText("Brukeren finnes allerede i systemet vårt. Prøv å logg inn i stedet.");
    
    //if user doesnt exist:
    // ny bruker burde hete newUseri hvor i er lengden på listen av eksitserende brukere slik at vi kan skille de fra hverandre. 
    // eller trenger kanskje ikke dette hvis vi hver gang vi skal frem til en spesifikk bruker, bruker en stream som filtrerer på user.getUsername
    user = new User(username, password);
    //add new user to file
    loadNewPage(new ActionEvent());
}


@FXML 
public void handleSignInButtonClick(ActionEvent event)throws IOException{
    readTextFields();

    //trenger vi try/catch her eller kan vi bare bruke if setninger alene?
    try {
        //check if user already exists in file, if so, go to next page:
        user = new User(username, password);
        loadNewPage(new ActionEvent());
        usernameInput.clear();
        passwordInput.clear();
    } catch (Exception e) {
        output.setText("Feil brukernavn eller passord. Vennligst prøv igjen.");
    }

}


@Override
public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'initialize'");
}


private void loadNewPage(ActionEvent actionEvent) {
    try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shopee.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ShopeeController shopeeController = loader.getController();
        shopeeController.setUser(user);
        usernameInput.clear();
        passwordInput.clear();
        output.setText("");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}




