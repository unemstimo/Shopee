package core;
import core.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    
    private User user;

    @Test
    public void testValidUsername(){
        String correctUsername = "olanordmann@gmail.com";
        String correctPassword = "ola123//";
        
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

    @Test
    public void testValidPassword() {
        String correctUsername = "olanordmann@gmail.com";
        String correctPassword = "ola123//";

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

    @Test
    public void testSetUsername() {
        user = new User("olanordmann@gmail.com", "ola123//");

        user.setUsername("karinordmann@gmail.com");
        Assertions.assertEquals("karinordmann@gmail.com", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        user = new User("olanordmann@gmail.com", "ola123//");

        user.setPassword("kari123//");
        Assertions.assertEquals("kari123//", user.getPassword());
    }

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
