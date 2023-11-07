package shopee.ui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.LocalUserAccess;
import shopee.ui.dataaccess.UserAccess;


public class LogInTest extends ApplicationTest {
    
    
    private FileHandeler fileHandeler = new FileHandeler();


    private LogInController controller = new LogInController();
    private UserAccess dataAccess;
  
    
  
    // @BeforeAll
    // public void rigup() throws FileNotFoundException {
    //     this.testUser = exampleUser();
    // }
  
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.dataAccess = new LocalUserAccess();
        this.fileHandeler.setFilePath("direct.json");
        fileHandeler.clearFileContent();
        controller.initData(this.dataAccess);  
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        fxmlLoader.setLocation(this.getClass().getResource("Login.fxml"));
        final Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
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

        assertEquals("Index 1 out of bounds for length 1", this.controller.getErrorLabel());  

    }
    /**
     * This method tests invalid password 
     */
    @Test
    public void testSignUpWithInvalidPassword() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("invalidpassword");

        clickOn("#signUp");

        assertEquals("The password must contain letters, digits and special characters", this.controller.getErrorLabel());

    

    }


    /*
     * This method tests if fields get clear when clicking on sign up button 
     * and that the output text is given correctly
     * 
     */
    @Test
    public void testSignUpWithValidCredentials() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        assertEquals("", lookup("#usernameInput").queryTextInputControl().getText());
        assertEquals("", lookup("#passwordInput").queryTextInputControl().getText());

        assertEquals("Brukeren er blitt opprettet. Du kan nå logge inn", this.controller.getErrorLabel());

        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");

        assertEquals("Brukernavnet finnes allerede.", this.controller.getErrorLabel());

    }

    /*
     * This method tests if fields clear when signing in
     * 
     */

    @Test
    public void testSignIn() {
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signUp");
        
        clickOn("#usernameInput").write("valid@example.com");
        clickOn("#passwordInput").write("validpwd123/");
        clickOn("#signIn");
    }


    @Test
    public void testUnvalidSignIn() {
            
            clickOn("#usernameInput").write("invalid@example");
            clickOn("#passwordInput").write("validpwd123/");
            clickOn("#signIn");  
    
            assertEquals("Feil brukernavn eller passord. Vennligst prøv igjen.", this.controller.getErrorLabel());
    }

  
}
