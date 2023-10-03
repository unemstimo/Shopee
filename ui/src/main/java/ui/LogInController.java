package ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import core.User;
import core.Storage.ReadFromFile;
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
private User user = new User();

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
        //"readFromJson()" need to be replaced with method from Json class
        List<User> users = readFromJson();
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
            User newUser = new User(username, password);
            users.add(newUser);
            //need to save the updated user list to JSon datastorage, "writeToJdon()" need to be replaced with method from Json class
            writeToJson(users);
            output.setText("Brukeren er blitt opprettet. Du kan nå logge inn");
        }
    } catch (IOException e) {
        e.printStackTrace(); 
        output.setText("Feil ved lesing av data. Prøv igjen senere.");
    } catch(Exception e){
        e.printStackTrace();
        output.setText("En feil har oppstått. Prøv igjen senere.");
    }
    //go through file to check if the user already exist
    //if user exist:
    //output.setText("Brukeren finnes allerede i systemet vårt. Prøv å logg inn i stedet.");
    
    //if user doesnt exist:
    // ny bruker burde hete newUseri hvor i er lengden på listen av eksitserende brukere slik at vi kan skille de fra hverandre. 
    // eller trenger kanskje ikke dette hvis vi hver gang vi skal frem til en spesifikk bruker, bruker en stream som filtrerer på user.getUsername
    //this.user = new User(username, password);
    //add new user to file
    //loadNewPage(new ActionEvent());
}

/**
 * This method handles the action of signing in to the app. 
 * Checks if the user already exists in the Json file.
 * If username and password is correct, the user get sent to the next page where it can make a shopping list
 * @param event
 * @throws IOException 
 * catches file reading error and other exceptions 
 */
//!!!HEr trenger vi kanskje å hente opp handlelisten? Eller vent siden user blir med videre til shopeecontrolleren så tar vi opp handlelisten i kontrolleren i stedet
// okei, sidn vi bruker i shopeecontroller at vi viser shopeeList som listen med food items, må vi enten endre dette eller koble user opp mot shopeelist i enten shopeelist eller shopeeApp

@FXML 
public void handleSignInButtonClick(ActionEvent event)throws IOException{
    readTextFields();

    try {
        //Read from Json, "readFromJson()" need to be replaced with method from Json, need to be made by Johan and Oskar 
        //and should return list of users.
        List<User> users = readFromJson();

        boolean userExist = false;
        for (User userInFile : users) {
            if(userInFile.getUsername().equals(username) && userInFile.getPassword().equals(password)){
                userExist = true;
                break;
            }
        }
        if(userExist){
            this.user = new User(username, password);
            loadNewPage(new ActionEvent());
            usernameInput.clear();
            passwordInput.clear();
        }
        else{
            output.setText("Feil brukernavn eller passord. Vennligst prøv igjen.");
        }   
    } catch (IOException e) {
        e.printStackTrace(); 
        output.setText("Feil ved lesing av data. Prøv igjen senere.");
    } catch(Exception e){
        e.printStackTrace();
        output.setText("En feil har oppstått. Prøv igjen senere.");
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




