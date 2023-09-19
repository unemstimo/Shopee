package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

/**
 * TestFX App test
 */
public class ShopeeTest extends ApplicationTest {

    private ShopeeController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Shopee.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Parent getRootNode() {
        return root;
    }

    /**
     * Test for the behavior and simulate the user interactions
     */

    @Test
    public void testAddFoodButtonClick(){
        clickOn("#newFood").write("Apple");
        clickOn("#amountNewFood").write("4");
        clickOn("#addFood");
        Assertions.assertEquals(" ", lookup("#newFood").queryTextInputControl().getText());
        Assertions.assertEquals(" ", lookup("#amountNewFood").queryTextInputControl().getText());

    }

    
}
