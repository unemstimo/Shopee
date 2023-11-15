package shopee.ui;

import java.io.FileNotFoundException;
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

/**
* Class for the login Controller, the outmost layer of controller. 
*/
public class LogInController extends AbstractController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Button signIn;
    @FXML
    private Button signUp;
    @FXML
    private Label output;

    private String username;
    private String password;
    private List<User> users;
    private User user;
    private UserAccess dataAccess;

    /**
    * Configures if the class is using remote or local access when running
    * the application.
    *
    * @param dataAccess sets the access.
    */
    public void initData(UserAccess dataAccess) {
        //sets dataAccess to either local or remote!
        this.dataAccess = dataAccess; 

        //gets all users from database either local or remote!
        this.users = dataAccess.getAllUsers(); 
    }   

    private void readTextFields() {
        this.username = usernameInput.getText();
        this.password = passwordInput.getText();
    }

    /**
    * Method for seting up the server. Tries to connect to the server first.
    * If the server is not running, it connects directly to a local file
    *
    * @throws FileNotFoundException if file doesnt exist
    */
    public void setUpAccess() throws FileNotFoundException {
        try {
            this.dataAccess = new RemoteUserAccess(new URI("http://localhost:8080/users"), false);
            System.out.println("Connected to server");
        } catch (Exception e) {
            this.dataAccess = new LocalUserAccess();
            System.out.println(e.getMessage()); 
            System.out.println("Local file is used");
        }
        this.initData(this.dataAccess);
    }

    /**
    * This method handles when user wants to sign up to make an user in the app.
    * The method goes through Json File to check if username already exists,
    * if it does the user need to make another username
    * If username does not exist, the new user gets added to the Json file. 
    * Catches if an error occur while reading/writing to file or other errors.
    *
    * @param event when the signUp-button is clicked
    */
    @FXML 
    public void handleSignUpButtonClick(ActionEvent event) {
        readTextFields();
        boolean usernameTaken = false;
        for (User userInFile : this.users) {
            if (userInFile.getUsername().equals(username)) {
                usernameTaken = true;
                break;
            }
        }
        if (usernameTaken) {
            output.setText("Brukernavnet finnes allerede.");
        } else {
            try {
                User user = new User(username, password);
                dataAccess.addUser(user); //adds user to file either local or remote!
                this.user = user;
                //gets all users from database either local or remote updated
                this.users = dataAccess.getAllUsers(); 
                output.setText("Brukeren er blitt opprettet. Du kan nå logge inn");
                usernameInput.clear();
                passwordInput.clear();
            } catch (Exception e) {
                output.setText(e.getMessage());
            }
        }
        
    }

    /**
    * This method handles the action of signing in to the app. 
    * Checks if the user already exists in the Json file.
    * If username and password is correct, 
    * the user get sent to the next page where it can make a shopping list
    *
    * @param event when the signIn button is clicked
    */
    @FXML 
    public void handleSignInButtonClick(ActionEvent event) {
        readTextFields();

        boolean userExist = false;
        for (User userInFile : this.users) { // checks the newes list made from the dataAccess
            if (userInFile.getUsername().equals(this.username) 
                    && userInFile.getPassword().equals(this.password)) {
                userExist = true;
                break;
            }
        }
        if (userExist) { // if user exists, the user is sent to the next page
            //gets user from database either local or remote!
            this.user = dataAccess.getUser(this.username); 
            //sets the scene to the homepage with the user
            setScene(Controllers.HOMEPAGE, event, this.dataAccess, this.user, null);
        } else {
            output.setText("Feil brukernavn eller passord. Vennligst prøv igjen.");
        }   
    }
    
    public String getErrorLabel() {
        return output.getText();
    }

}




