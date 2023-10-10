package shopee.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

import org.junit.jupiter.api.Test;

import shopee.json.FileHandeler; 
public class FileHandlerTest {

    private FileHandeler fileHandler = new FileHandeler();
    //private final String testFilePath = "testDataStorage.json"; // Use a temporary test file

   
    @Test
    public void testWriteAndReadUserToFile() {
        // Create a User object for testing
        fileHandler.clearFileContent();
        User testUser = new User("Johan@gmail.no", "johan123@");
        // Add a shopeelist to User 
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

    @Test
    public void testUpdateUserInFile() {
        fileHandler.clearFileContent();
        // Create a User object for testing
        User testUser = new User("oskar@gmail.com", "Password12!");
        User testUser2 = new User("Hanna@gmail.com", "Henryd12!");
        ShopeeList newList = new ShopeeList("Onsdag");
        newList.addFoodShopList("Chips",7);
        newList.addFoodShopList("Chips",3);
        testUser.setShopeeList(newList);

        // Write the User object to the test file
        fileHandler.writeToFile(testUser);
        fileHandler.writeToFile(testUser2);

        // Update the User object
        testUser.setPassword("NewPwd4$");
        fileHandler.writeToFile(testUser);

        // Read the User object from the test file
        List<User> userList = fileHandler.JsonToObj();

        // Assert that the User object was successfully updated
        assertNotNull(userList);
        assertEquals(2, userList.size());
        assertEquals("NewPwd4$", userList.get(0).getPassword());
        System.out.println(newList.getShopList());
        assertEquals(3, testUser.getShopeeList().getShopList().get(0).getFoodAmount());
    }

    @Test
    public void testClearFileContent() {
        // Create a User object for testing
        User testUser = new User("Une@outlook.com", "Umulig18%");

        // Write the User object to the test file
        fileHandler.writeToFile(testUser);

        // Clear the file content
        fileHandler.clearFileContent();

        // Read the User object from the test file
        List<User> userList = fileHandler.JsonToObj();

        // Assert that the file content was cleared
        assertNotNull(userList);
        assertEquals(0, userList.size());
    }
}
