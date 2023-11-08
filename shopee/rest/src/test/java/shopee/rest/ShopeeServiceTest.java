package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler; 


public class ShopeeServiceTest {
    private ShopeeUserService shopeeService;
    private FileHandeler fileHandler; 
    private List<User> allUsers; 
    private User exampleUser; 
    private ShopeeList exampleList; 
    private ObjectMapper mapper;


    /* 
     * Sets up a service and uses createIntialUser to reset the 
     * user info between each test
     */
    @BeforeEach
    public void setUp() {
        try {
            shopeeService = new ShopeeUserService();
            shopeeService.setAllUsers(ShopeeUserService.createInitialUser());
            // fileHandler = new FileHandeler();
            mapper = new ObjectMapper();
            this.allUsers = shopeeService.getAllUsers();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

    

    @Test 
    public void addUser() throws JsonProcessingException{
        User testUser = new User("Richard@gmail.com", "Password123!");
        User testUser2 = new User("Daniel@outlook.no", "HarryP123@");

        assertTrue(shopeeService.addUser(mapper.writeValueAsString(testUser)), "addUser method didn't work");
        assertTrue(shopeeService.getAllUsers().contains(testUser), "The user was not added correctly");
        assertFalse(shopeeService.getAllUsers().contains(testUser2), "User was added even though it should't");
    }
    
    /**
     * Helper method such that the test are easier to understand.
     */

    public void getuserInfo(){
        this.exampleUser = shopeeService.getAllUsers().get(0);
        this.exampleList = this.allUsers.get(0).getShopeeLists().get(0);
    }

    @Test
    public void addShopeeList(){
        User userExample = shopeeService.getAllUsers().get(0);
        ShopeeList newList = new ShopeeList("testList");
        assertFalse(userExample.getShopeeLists().contains(newList)
        ,"The list is already added, should have been false");
        assertTrue(shopeeService.addShopeeList(userExample.getUsername(), newList)
        ,"addShopeeList method didn't work");
        assertTrue(userExample.getShopeeLists().contains(newList)
        ,"The shopeeList was not added to the user");
    }

    @Test
    public void deleteShopeeList() throws IOException{
        User userExample = shopeeService.getAllUsers().get(0);
        ShopeeList listExample = shopeeService.getAllUsers().get(0).getShopeeLists().get(0);
        assertTrue(shopeeService.getUser(userExample.getUsername()).getShopeeLists().contains(listExample)
        , "The shopeeList was never in example user and cant be removed");
        assertTrue(shopeeService.deleteShopeeList(userExample.getUsername(), listExample.getListName())
        , "The delete shopeeList method didnt work");
        assertFalse(shopeeService.getUser(userExample.getUsername()).getShopeeLists().contains(listExample)
        , "The shopeeList was not deleted from the example user");
    }

    @Test
    public void createIntitalUser(){
        getuserInfo();
        assertTrue(this.allUsers.size() ==2, "something went wrong when initializing users in tests");
        assertEquals(this.exampleUser.getUsername(), "Terje@gmail.com", "The first user didnt match");
        assertTrue(this.exampleUser.getShopeeLists().size() ==2, "The amount of shopeeLists didnt match what it shoudl be");
        assertEquals(this.exampleUser.getShopeeLists().get(0).getFood(0).getFoodName(),"Bread"
        , "the food name didnt match what was created in createIntialUser ");
    }

  
}
