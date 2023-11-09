package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private ObjectMapper mapper; 
    private FileHandeler handler;



    /* 
     * Sets up a service and uses createIntialUser to reset the 
     * user info between each test
     */ 
    @BeforeEach
    public void setUp() throws FileNotFoundException{
        this.shopeeService = new ShopeeUserService();
        this.mapper = new ObjectMapper();
        this.handler = new FileHandeler();
        handler.clearFileContent();
        shopeeService.setAllUsers(ShopeeUserService.createInitialUser());
        
    }

    @Test
    public void testGetAllUsers() throws FileNotFoundException {
        int expectedSize = ShopeeUserService.createInitialUser().size();
        assertNotNull(shopeeService.getAllUsers());
        assertEquals(expectedSize, shopeeService.getAllUsers().size());
    }

    @Test 
    public void addUser() throws JsonProcessingException{
        User testUser = new User("Richard@gmail.com", "Password123!");
        User testUser2 = new User("Daniel@outlook.no", "HarryP123@");

        assertTrue(shopeeService.addUser(mapper.writeValueAsString(testUser)), "addUser method didn't work");
        assertTrue(shopeeService.getAllUsers().contains(testUser), "The user was not added correctly");
        assertFalse(shopeeService.getAllUsers().contains(testUser2), "User was added even though it should't");
    }
    
  @Test
    public void testAddAndGetUser() throws JsonProcessingException {
        
        User testUser = new User("Service@user.no","Service123@");
        String userString = mapper.writeValueAsString(testUser);
        try {
            assertEquals(2, shopeeService.getAllUsers().size());
            assertTrue(shopeeService.addUser(userString));
            assertEquals(3, shopeeService.getAllUsers().size());
            User addedUser = shopeeService.getUser("Service@user.no");
            assertNotNull(addedUser);

            User neverAddedUser = shopeeService.getUser("Never@added.no");
            assertNull(neverAddedUser);
            assertEquals("Service@user.no", addedUser.getUsername());
        } catch (Exception e) {
            fail("Exception thrown while adding a user: " + e.getMessage());
        }
    }

    @Test
    public void testAddShopeeList() {
        //already created user in createInitialUsers method
        String username = "Terje@gmail.com";
        ShopeeList testList = new ShopeeList("serviceList");
        try {
            assertThrows(IllegalArgumentException.class, ()-> shopeeService.getUser(username).getShopeeList(username));
            assertTrue(shopeeService.addShopeeList(username, mapper.writeValueAsString(testList)));
            User user = shopeeService.getUser(username);
            assertNotNull(user);
            assertEquals(user.getShopeeLists().get(user.getShopeeLists().size()-1).getListName(),testList.getListName() ); 
        } catch (Exception e) {
            fail("Exception thrown while adding a ShopeeList: " + e.getMessage());
        }
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
