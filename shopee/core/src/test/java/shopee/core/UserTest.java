package shopee.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

/**
 * In this test class, all the main methods of the User.java class are being tested.
 * Some of the smaller methods (such ass getters) are being indirectly tested and do not have their own tests. 
*/

public class UserTest {
    
    private User user;

    /**
     * Tests if the validUsername(String username) method works.
     * The test checks if exceptions are being thrown for all the cases where the criteria is not met. 
    */
    @Test
    public void testValidUsername(){
        // String correctUsername = "olanordmann@gmail.com";
        // String correctPassword = "ola123//";
        
        // checking if exception is being thrown for either: number first, or first part of email address having length < 2
        try {
            user = new User("1olanordmann@gmail.com", "ola123//");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (Exception e) {
            assertEquals("The username must begin with a letter, and have a length of minimum two letters before @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            user = new User("o@gmail.com", "ola123//");
        } catch (Exception e) {
            assertEquals("The username must begin with a letter, and have a length of minimum two letters before @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // checking if exception is being thrown if the email address is missing the @- sign
        try {
            user = new User("olanordmanngmail.com", "ola123//");
        } catch (Exception e) {
            assertEquals("Missing @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // checking if exception is being thrown if the domain is not in the list of valid domains
        try {
            user = new User("olanordmann@gmail.con", "ola123//");
        } catch (Exception e) {
            assertEquals("This domain doesnt exist.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests if the validPassword(String password) method works.
     * The test checks if exceptions are being thrown for all the cases where the criteria is not met. 
    */
    @Test
    public void testValidPassword() {
        // example String correctUsername = "olanordmann@gmail.com";
        // example String correctPassword = "ola123//";

        // checking if exception is being thrown if the password length < 8
        try {
            user = new User("olanordmann@gmail.com", "ola123/");
        } catch (Exception e) {
            assertEquals("The password needs to be at least 8 characters long", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // checking if exception is being thrown if password doesnt contain at least one letter, one symbol and one special character
        try {
            user = new User("olanordmann@gmail.com", "123123//");
        } catch (Exception e) {
            assertEquals("The password must contain letters, digits and special characters", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            user = new User("olanordmann@gmail.com", "olaola//");
        } catch (Exception e) {
            assertEquals("The password must contain letters, digits and special characters", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            user = new User("olanordmann@gmail.com", "ola12312");
        } catch (Exception e) {
            assertEquals("The password must contain letters, digits and special characters", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests the setUsername(String username) method.
    */
    @Test
    public void testSetUsername() {
        user = new User("olanordmann@gmail.com", "ola123//");

        user.setUsername("karinordmann@gmail.com");
        Assertions.assertEquals("karinordmann@gmail.com", user.getUsername());
    }

    /**
     * Tests the setPassword(String password) method.
    */
    @Test
    public void testSetPassword() {
        user = new User("olanordmann@gmail.com", "ola123//");

        user.setPassword("kari123//");
        Assertions.assertEquals("kari123//", user.getPassword());
    }

    /**
     * Tests the setShopeeList(ShopeeList list) method.
    */
    @Test
    public void testSetShopeeList() {
        user = new User();
        user.setUsername("olanordmann@gmail.com");
        user.setPassword("ola123//");

        ShopeeList list1 = new ShopeeList("Ola");
        list1.addFoodShopList("apple", 4);
        
        user.setShopeeList(list1);
        Assertions.assertEquals(list1, user.getShopeeList());
    }

}
