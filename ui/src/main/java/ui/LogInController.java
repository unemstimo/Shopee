package ui;

import java.io.IOException;
import java.util.List;
import core.User;
import core.Storage.FileHandeler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

@FXML private TextField usernameInput, passwordInput;
@FXML private Button signIn, signUp;
@FXML private Label output;

private String username;
private String password;
private User user;

private FileHandeler jsonFile = new FileHandeler();

private void readTextFields(){
    username = usernameInput.getText();
    password = passwordInput.getText();
}

/**
 * This method handles when user wants to sign up to make an user in the app.
 * The method goes through Json File to check if username already exists, if it does the user need to make another username
 * If username does not exist, the new user gets added to the Json file. 
 * @param event
 * Catches if an error occur while reading/writing to file or other errors.
 */
@FXML 
public void handleSignUpButtonClick (ActionEvent event){
    readTextFields();
    try {
        List<User> users = jsonFile.JsonToObj();
        boolean usernameTaken = false;
        for (User userInFile : users) {
            if(userInFile.getUsername().equals(username)){
                usernameTaken = true;
                break;
            }
        }
        if(usernameTaken){
            output.setText("Brukernavnet finnes allerede.");
        } else{
            try{
                this.user.setUsername(username);
                this.user.setPassword(password);
                users.add(user);
            } catch(Exception e) {
                output.setText("Brukernavnet eller passordet oppfyller ikke gitte krav. Brukernavn må være på formatet navn@epost.domene , passordet må være minst 8 tegn langt, og innholde både bokstaver, tall og spesialtegn.");
            }
            for (User userToFile : users) {
                jsonFile.writeToFile(userToFile);
            }
            
            output.setText("Brukeren er blitt opprettet. Du kan nå logge inn");
            usernameInput.clear();
            passwordInput.clear();
        }
    }  catch(Exception e){
        e.printStackTrace();
        output.setText("En feil har oppstått. Prøv igjen senere.");
    }
}

/**
 * This method handles the action of signing in to the app. 
 * Checks if the user already exists in the Json file.
 * If username and password is correct, the user get sent to the next page where it can make a shopping list
 * @param event
 * @throws IOException 
 * catches file reading error and other exceptions 
 */
@FXML 
public void handleSignInButtonClick(ActionEvent event)throws IOException{
    readTextFields();

    try {
        List<User> users = jsonFile.JsonToObj();

        boolean userExist = false;
        for (User userInFile : users) {
            if(userInFile.getUsername().equals(username) && userInFile.getPassword().equals(password)){
                userExist = true;
                output.setText(username + password);
                break;
            }
        }
        if(userExist){
            this.user.setUsername(username);
            this.user.setPassword(password);
            loadNewPage(new ActionEvent());
            
        }
        else{
            output.setText("Feil brukernavn eller passord. Vennligst prøv igjen.");
        }   
    }  catch(Exception e){
        e.printStackTrace();
        output.setText("En feil har oppstått. Prøv igjen senere.");
    }

}

private void loadNewPage(ActionEvent actionEvent) {
    try{  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shopee.fxml"));
        //Parent root = loader.load();
        //Stage stage = new Stage();
        Scene shopeeScene = new Scene(loader.load());

        ShopeeController shopeeController = loader.getController();
        shopeeController.setUser(user);

        Stage stage = (Stage) signIn.getScene().getWindow();
        stage.setScene(shopeeScene);

        usernameInput.clear();
        passwordInput.clear();
        
        stage.show();
        
        
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}




