package ui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInTest extends ApplicationTest {
    
    
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LogIn.fxml"));
        root = fxmlLoader.load();
        
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This method tests if with correct username and password the next page shopee.fxml
     */

    @Test
    public void testSignIn() {
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signIn");

        verifyThat("#shoppingListView", isVisible());
        verifyThat("#newFood", isVisible());
        verifyThat("#AmountNewFood", isVisible());
    }
    

    /**
     * This method tests if correct text shows up when invalid username or password is entered
     */
    @Test
    public void testSignUpWithInvalidCredentials() {
        
        clickOn("#usernameInput").write("invalid@example");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        
        verifyThat("#output", hasText("Feil brukernavn eller passord. Vennligst prøv igjen."));

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("invalidpassword");
        clickOn("#signUp");

        
        verifyThat("#output", hasText("Feil brukernavn eller passord. Vennligst prøv igjen."));

    }

    /*
     * This method tests if correct text in ouput field shows when a user 
     * create new user with valid username and password
     */
    @Test
    public void testSignUpWithValidCredentials() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        
        verifyThat("#output", hasText("Brukeren er blitt opprettet. Du kan nå logge inn"));
    }

    /*
     * This method tests if correct output text shows when a user trys to sign up
     * with a already existing username
     */

    @Test
    public void testSignUpWithExistingUsername() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        
        verifyThat("#output", hasText("Brukernavnet finnes allerede."));
    }

   

}
