package shopee.rest;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import shopee.core.ShopeeList;
import shopee.core.User;


public class ShopeeServiceTest {
     private ShopeeUserService shopeeService;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

    
    


}
