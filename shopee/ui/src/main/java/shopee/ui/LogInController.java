package shopee.ui;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shopee.core.User;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.RemoteUserAccess;
import shopee.ui.dataaccess.UserAccess;

public class LogInController extends AbstractController{

@FXML private TextField usernameInput, passwordInput;
@FXML private Button signIn, signUp;
@FXML private Label output;

private String username;
private String password;
private List<User> users;
private User user;

private UserAccess dataAccess;

public void initData(UserAccess dataAccess) {
    this.dataAccess = dataAccess; //sets dataAccess to either local or remote!
    this.users = dataAccess.getAllUsers(); //gets all users from database either local or remote!

}   

private void readTextFields(){
    this.username = usernameInput.getText();
    this.password = passwordInput.getText();
}

/**
 * Method for seting up the server. Tries to connect to the server first.
 * If the server is not running, it connects directly to a local file
 */
public void setUpAccess() {
    try {
      this.dataAccess = new RemoteUserAccess(new URI("http://localhost:8080"), false);
    } catch (Exception e) {
      this.dataAccess = new LocalUserAccess();
    }

    this.initData(this.dataAccess);
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
        boolean usernameTaken = false;
        for (User userInFile : this.users) {
            if(userInFile.getUsername().equals(username)){
                usernameTaken = true;
                break;
            }
        }
        if(usernameTaken){
            output.setText("Brukernavnet finnes allerede.");
        } else{
            try{
                User user = new User(username, password);
                dataAccess.addUser(user); //adds user to file either local or remote!
                this.user = user;
                this.users = dataAccess.getAllUsers(); //gets all users from database either local or remote updated
                output.setText("Brukeren er blitt opprettet. Du kan nå logge inn");
                usernameInput.clear();
            passwordInput.clear();
            } catch(Exception e) {
                output.setText(e.getMessage());
            }
            
        
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

        boolean userExist = false;
        for (User userInFile : this.users) { // checks the newes list made from the dataAccess
            if(userInFile.getUsername().equals(this.username) && userInFile.getPassword().equals(this.password)){
                userExist = true;
                break;
            }
        }
        if(userExist){ // if user exists, the user is sent to the next page
            this.user = dataAccess.getUser(this.username); //gets user from database either local or remote!
            setScene(Controllers.HOMEPAGE, event, this.dataAccess, this.user, null); //sets the scene to the homepage with the user
        }
        else{
            output.setText("Feil brukernavn eller passord. Vennligst prøv igjen.");
        }   
    }  catch(Exception e){
        e.printStackTrace();
        output.setText("En feil har oppstått. Prøv igjen senere.");
    }
}

public String getErrorLabel() {
    return output.getText();
}

}




