package shopee.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shopee.core.ShopeeList; 
import shopee.core.User;


/**
 * Test for file handling to and from json files.
 */
public class FileHandlerTest {

    private FileHandeler fileHandler = new FileHandeler();

    
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        fileHandler = new FileHandeler("testFile.json");
    }

    @AfterEach
    public void tearDown() {
        fileHandler.clearFileContent();
    }

    /**
     * Test the writeToFile(User) method and jsonToObj()
     * Checks if new login info i saved properly plus shopping 
     * list info. Sees if the added fooditems 
     * match with what we write when creating a list.
     * 
     */
    @Test
    public void testWriteAndReadUserToFile() throws FileNotFoundException {
        
        // Add a shopeelist to User 
        ShopeeList newList = new ShopeeList("Wednesday");
        newList.addFoodShopList("Chips", 7);
        newList.addFoodBoughtList(newList.getFood("Chips"));
        newList.addFoodShopList("Apple", 4);
        newList.addFoodShopList("Bread", 1);

        // Create a User object for testing
        User testUser = new User("Johan@gmail.no", "johan123@");
        testUser.addShopeeList(newList);

        // Write the User object to the test file
        fileHandler.writeToFile(testUser);

        // Read the User object from the test file
        List<User> userList = fileHandler.jsonToObj();

        // Assert that the User object was successfully written and read
        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(testUser.getUsername(), userList.get(0).getUsername());
        assertEquals(testUser.getPassword(), userList.get(0).getPassword());
        assertEquals(2, testUser.getShopeeList(newList.getListName()).getShopList().size());
        assertEquals("Apple", testUser.getShopeeList(newList.getListName())
        .getShopList().get(0).getFoodName());
        assertEquals(1, testUser.getShopeeList(newList.getListName())
        .getShopList().get(1).getFoodAmount());
        assertEquals("Chips", testUser.getShopeeList(newList.getListName())
        .getBoughtItem(0).getFoodName());
    }

    @Test
    public void testUpdateUserInFile() throws FileNotFoundException {
        fileHandler.clearFileContent();
        // Create a User object for testing
        User testUser = new User("oskar@gmail.com", "Password12!");
        ShopeeList newList = new ShopeeList("Wednesday");
        newList.addFoodShopList("Chips", 7);
        newList.addFoodShopList("Chips", 3);
        testUser.addShopeeList(newList);

        User testUser2 = new User("Hanna@gmail.com", "Henryd12!");
        // Write the User object to the test file
        fileHandler.writeToFile(testUser);
        fileHandler.writeToFile(testUser2);

        // Update the User object
        testUser.setPassword("NewPwd4$");
        fileHandler.writeToFile(testUser);

        // Read the User object from the test file
        List<User> userList = fileHandler.jsonToObj();

        // Assert that the User object was successfully updated
        assertNotNull(userList);
        assertEquals(2, userList.size());
        assertEquals("NewPwd4$", userList.get(0).getPassword());
        assertEquals(3, testUser.getShopeeList(newList.getListName())
        .getShopList().get(0).getFoodAmount());
    }

    /**
     * Test the clearFileContent() helper method 
     * Checks if all stored data is removed from the file, 
     * such that an empty file can be used in other methods.
     * 
     */
    @Test
    public void testClearFileContent() throws FileNotFoundException {
        // Create a User object for testing
        User testUser = new User("Une@outlook.com", "Umulig18%");

        // Write the User object to the test file
        fileHandler.writeToFile(testUser);

        // Clear the file content
        fileHandler.clearFileContent();

        // Read the User object from the test file
        List<User> userList = fileHandler.jsonToObj();

        // Assert that the file content was cleared
        assertNotNull(userList);
        assertEquals(0, userList.size());
    }
   
    /**
     * Test the clearFileContent() helper method 
     * Checks if all stored data is removed from the file, 
     * such that an empty file can be used in other methods.
     */
    public User setUpTestuser() {
        
        ShopeeList newList = new ShopeeList("Dinner");
        newList.addFoodShopList("Chips", 7);
        newList.addFoodShopList("Apple", 4);
        newList.addFoodShopList("Bread", 1);

        ShopeeList newlist2 = new ShopeeList("Lunch");
        newlist2.addFoodShopList("Tomato", 7);
        newlist2.addFoodShopList("Fish", 4);
        newlist2.addFoodShopList("Milk", 1);

        User testUser = new User("test@test.no", "test1234@");

        testUser.addShopeeList(newList);
        testUser.addShopeeList(newlist2);

        return testUser;

    }


}
