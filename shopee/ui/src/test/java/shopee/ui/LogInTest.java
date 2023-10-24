package shopee.ui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogInTest extends ApplicationTest {
    
    
    private Parent root;
    private LogInController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LogIn.fxml"));
        root = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @Test
    public void testControllerInitial() {
        assertNotNull(this.controller);
    }

    
    /**
     * This method tests invalid username 
     */
    @Test
    public void testSignUpWithInvalidUsername() {
        assertEquals("", lookup("#usernameInput").queryTextInputControl().getText());
        assertEquals("", lookup("#passwordInput").queryTextInputControl().getText());

        clickOn("#usernameInput").write("invalid@example");
        clickOn("#passwordInput").write("validpwd123/");

        clickOn("#signUp");

        

    }
    /**
     * This method tests invalid password 
     */
    @Test
    public void testSignUpWithInvalidPaasword() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("invalidpassword");

        clickOn("#signUp");

    }


    /*
     * This method tests if fields get clear when clicking on sign up button 
     * 
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
     * This method tests if fields clear when signing in
     * 
     */

    @Test
    public void testSignIn() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signIn");
        
    }

}
