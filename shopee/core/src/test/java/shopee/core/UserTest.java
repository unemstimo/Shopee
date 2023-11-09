package shopee.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * In this test class, all the main methods of the User.java class are being tested.
 * Some of the smaller methods (such ass getters) are being indirectly tested and do not have their own tests. 
*/

public class UserTest {
    
    private User user;
    private User user2;

    /**
     * Add a BeforeAll to avoid creating a new User object in each method
     */

    @BeforeEach
    public void setUp() {
        user2 = new User("olanordmann@gmail.com", "ola123//");
    }

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
        user2.setUsername("karinordmann@gmail.com");
        Assertions.assertEquals("karinordmann@gmail.com", user2.getUsername());
    }

    /**
     * Tests the setPassword(String password) method.
    */
    @Test
    public void testSetPassword() {
        user2.setPassword("kari123//");
        Assertions.assertEquals("kari123//", user2.getPassword());
    }

    /**
     * Tests the setShopeeList(List<ShopeeList> list) method.
    */
    @Test
    public void testSetShopeeList() {
        List<ShopeeList> shopeeLists = new ArrayList<>();
        ShopeeList list1 = new ShopeeList("Ola");
        ShopeeList list2 = new ShopeeList("Week 42");
        shopeeLists.add(0,list1);
        shopeeLists.add(1, list2);
        user2.setShopeeLists(shopeeLists);
        
        Assertions.assertEquals(list1.getListName(), user2.getShopeeLists().get(0).getListName());
        Assertions.assertEquals(2, user2.getShopeeLists().size());
    }


    /**
     * Tests the getShopeeList(List<ShopeeList>) method
     */
    @Test public void getShopeeLists(){
        List<ShopeeList> shopeeLists = new ArrayList<>();
        ShopeeList list1 = new ShopeeList("Ola");
        ShopeeList list2 = new ShopeeList("Week 42");
        shopeeLists.add(list1);
        shopeeLists.add(list2);
        user2.setShopeeLists(shopeeLists);

        Assertions.assertEquals(shopeeLists.size(), user2.getShopeeLists().size());
    }



    
    /**
     * Tests the addShopeeList(ShopeeList) method
     */
    //  @Test
    //  public void testAddShopeeList(){
    //      ShopeeList list1 = new ShopeeList("Ove");
    //      ShopeeList list2 = new ShopeeList("Henrik");
 
    //      user2.addShopeeList(list1);
    //      List<ShopeeList> shopeeLists = user2.getShopeeLists();
    //      assertTrue(shopeeLists.contains(list1));
    //      assertFalse(shopeeLists.contains(list2));
 
    //      //Adding the same list twice should throw an exception
    //      try {
    //         user2.addShopeeList(list1);
    //     } catch (Exception e) {
    //         assertEquals("Cant use same list name twice", e.getMessage());
    //         assertTrue(e instanceof IllegalArgumentException);
    //     }  
    // }

    /**
     * Tests the method getShopeeList(String)
     */
    @Test
    public void testGetShopeeList() {
        ShopeeList list1 = new ShopeeList("Week 12");
        ShopeeList list2 = new ShopeeList("Week 13");

        user2.addShopeeList(list1);
        user2.addShopeeList(list2);

        assertEquals(list1, user2.getShopeeList("Week 12"));
        assertEquals(list2, user2.getShopeeList("Week 13"));
        try {
            user2.getShopeeList("NonExistentList");
        } catch (Exception e) {
            assertEquals("No such list name for this user", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }  
    }
    
      /**
     * Tests the method deleteShopeeList(int)
     */
    @Test
    public void testDeleteShopeeList() {
        ShopeeList list1 = new ShopeeList("Week 42");
        ShopeeList list2 = new ShopeeList("Week 43");
        ShopeeList list3 = new ShopeeList("Week 44");

        user2.addShopeeList(list1);
        user2.addShopeeList(list2);
        user2.addShopeeList(list3);
        assertEquals(3, user2.getShopeeLists().size());
        user2.deleteShopeeList(1); // Delete "List2"
        List<ShopeeList> shopeeLists = user2.getShopeeLists();

        assertTrue(shopeeLists.contains(list1));
        assertFalse(shopeeLists.contains(list2));
        assertTrue(shopeeLists.contains(list3));
        
        assertThrows(IndexOutOfBoundsException.class, () -> user2.deleteShopeeList(4) );

        
    }

}
