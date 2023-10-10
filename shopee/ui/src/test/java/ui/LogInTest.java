package ui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
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
     * This method tests if correct text shows up when invalid username or password is entered
     */
    @Test
    public void testSignUpWithInvalidUsername() {
        
        clickOn("#usernameInput").write("invalid@example");
        clickOn("#passwordInput").write("validpwd123/");

        Exception exception = assertThrows(Exception.class, () -> {
            clickOn("#signUp");
        });
        assertEquals("Brukernavnet eller passordet oppfyller ikke gitte krav. Brukernavn må være på formatet navn@epost.domene , passordet må være minst 8 tegn langt, og innholde både bokstaver, tall og spesialtegn.", exception.getMessage());

       

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("invalidpassword");
        clickOn("#signUp");

        

    }
    @Test
    public void testSignUpWithInvalidPaasword() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("invalidpassword");

        Exception exception = assertThrows(Exception.class, () -> {
            clickOn("#signUp");
        });
        assertEquals("Brukernavnet eller passordet oppfyller ikke gitte krav. Brukernavn må være på formatet navn@epost.domene , passordet må være minst 8 tegn langt, og innholde både bokstaver, tall og spesialtegn.", exception.getMessage());


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

        assertEquals("", lookup("#usernameInput").queryTextInputControl().getText());
        assertEquals("", lookup("#passwordInput").queryTextInputControl().getText());

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

        User newUser = new User("valid@example.com", "validpwd123/");

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        try {
            User user = new User("valid@example.com", "validpwd123/");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (Exception e) {
            assertEquals("Brukeren finnes allerede. ", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

   

}
