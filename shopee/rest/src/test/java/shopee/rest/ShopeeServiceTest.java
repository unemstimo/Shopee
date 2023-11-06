package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler; 


public class ShopeeServiceTest {
    private ShopeeUserService shopeeService;
    private FileHandeler fileHandler; 
    private List<User> allUsers; 
    private User exampleUser; 
    private ShopeeList exampleList; 


    /* 
     * Sets up a service and uses createIntialUser to reset the 
     * user info between each test
     */
    @BeforeEach
    public void setUp() {
        try {
            shopeeService = new ShopeeUserService();
            shopeeService.setAllUsers(ShopeeUserService.createInitialUser());
            fileHandler = new FileHandeler();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

    @Test 
    public void addUser(){
        User testUser = new User("Richard@gmail.com", "Password123!");
        User testUser2 = new User("Daniel@outlook.no", "HarryP123@");
        shopeeService.addUser(fileHandler.fromUserToString(testUser));
        assertTrue(shopeeService.getAllUsers().contains(testUser), "The user was not added correctly");
        assertFalse(shopeeService.getAllUsers().contains(testUser2), "User was added even though it should't");
    }
    
    
}
