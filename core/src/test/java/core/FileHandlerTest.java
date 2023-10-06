package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Storage.FileHandeler;
import core.ShopeeList;
import core.FoodItem; 
public class FileHandlerTest {

    private FileHandeler fileHandler = new FileHandeler();
    //private final String testFilePath = "testDataStorage.json"; // Use a temporary test file

   

    @Test
    public void testWriteAndReadUserToFile() {
        // Create a User object for testing
        fileHandler.clearFileContent();
        User testUser = new User("Johan@gmail.no", "johan123@");
        //Add a shopeelist to User 
        ShopeeList newList = new ShopeeList("Onsdag");
        newList.addFoodShopList("Chips",7);
        newList.addFoodBoughtList(newList.getFood("Chips"));
        newList.addFoodShopList("Apple", 4);
        newList.addFoodShopList("Bread", 1);

        testUser.setShopeeList(newList);

        // Write the User object to the test file
        fileHandler.writeToFile(testUser);

        // Read the User object from the test file
        List<User> userList = fileHandler.JsonToObj();

        // Assert that the User object was successfully written and read
        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(testUser.getUsername(), userList.get(0).getUsername());
        assertEquals(testUser.getPassword(), userList.get(0).getPassword());
        assertEquals(2, testUser.getShopeeList().getShopList().size());
        assertEquals("Apple", testUser.getShopeeList().getShopList().get(0).getFoodName());
        assertEquals(1, testUser.getShopeeList().getShopList().get(1).getFoodAmount());
        assertEquals("Chips", testUser.getShopeeList().getBoughtItem(0).getFoodName());
    }

   
}
