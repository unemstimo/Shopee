package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        assertTrue(shopeeService.addUser(fileHandler.fromUserToString(testUser)), "addUser method didn't work");
        assertTrue(shopeeService.getAllUsers().contains(testUser), "The user was not added correctly");
        assertFalse(shopeeService.getAllUsers().contains(testUser2), "User was added even though it should't");
    }
    
    /**
     * Helper method such that the test are easier to understand.
     */

    public void getuserInfo(){
        this.allUsers = shopeeService.getAllUsers();
        this.exampleUser = shopeeService.getAllUsers().get(0);
        this.exampleList = shopeeService.getAllUsers().get(0).getShopeeList("Target shoppinglist");
    }

    @Test
    public void addShopeeList(){
        getuserInfo();
        ShopeeList newList = new ShopeeList("testList");
        assertFalse(shopeeService.getUser(this.exampleUser.getUsername()).getShopeeLists().contains(newList)
        ,"The list is already added, should have been false");
        assertTrue(shopeeService.addShopeeList(this.exampleUser.getUsername(), newList)
        ,"addShopeeList method didn't work");
        assertTrue(shopeeService.getAllUsers().get(0).getShopeeLists().contains(newList)
        ,"The shopeeList was not added to the user");
    }


   
}
