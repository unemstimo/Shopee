package shopee.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

/**
 * In this test class, all the main methods of the User.java class are being tested.
 * Some of the smaller methods (such ass getters) are being indirectly tested and do not have their own tests. 
*/

public class UserTest {
    
    private User user;

    /**
     * Add a BeforeAll to avoid creating a new User object in each method.
     * The username and password used here meet the sat criteria.
     */
    @BeforeEach
    public void setUp() {
        user = new User("olanordmann@gmail.com", "ola123//");
    }

    /**
     * Tests if the validUsername(String username) method works.
     * The test checks if exceptions are being thrown for all the cases where the criteria is not met. 
    */
    @Test
    public void testValidUsername(){
        // Checking if exception is being thrown if the email has a number first.
        try {
            user = new User("1olanordmann@gmail.com", "ola123//");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (Exception e) {
            assertEquals("The username must begin with a letter, and have a length of minimum two letters before @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if the first part of the email has length < 2.
        try {
            user = new User("o@gmail.com", "ola123//");
        } catch (Exception e) {
            assertEquals("The username must begin with a letter, and have a length of minimum two letters before @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if the email is missing the @- sign.
        try {
            user = new User("olanordmanngmail.com", "ola123//");
        } catch (Exception e) {
            assertEquals("Missing @", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if the domain is not in the list of valid domains.
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
        // Checking if exception is being thrown if the password length < 8.
        try {
            user = new User("olanordmann@gmail.com", "ola123/");
        } catch (Exception e) {
            assertEquals("The password needs to be at least 8 characters long", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if password doesnt contain at least one letter.  
        try {
            user = new User("olanordmann@gmail.com", "123123//");
        } catch (Exception e) {
            assertEquals("The password must contain letters, digits and special characters", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if password doesnt contain at least one symbol.
        try {
            user = new User("olanordmann@gmail.com", "olaola//");
        } catch (Exception e) {
            assertEquals("The password must contain letters, digits and special characters", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if exception is being thrown if password doesnt contain at least one special character.
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
        user.setUsername("karinordmann@gmail.com");
        assertEquals("karinordmann@gmail.com", user.getUsername());
    }

    /**
     * Tests the setPassword(String password) method.
    */
    @Test
    public void testSetPassword() {
        user.setPassword("kari123//");
        assertEquals("kari123//", user.getPassword());
    }

    /**
     * Tests the setShopeeList(List<ShopeeList> list) method.
    */
    @Test
    public void testSetShopeeList() {
        assertEquals(new ArrayList<>(), user.getShopeeLists());

        List<ShopeeList> shopeeLists = new ArrayList<>();
        ShopeeList list1 = new ShopeeList("Ola");
        ShopeeList list2 = new ShopeeList("Week 42");
        shopeeLists.add(list1);
        shopeeLists.add(list2);
        user.setShopeeLists(shopeeLists);
        
        assertEquals(list1.getListName(), user.getShopeeLists().get(0).getListName());
        assertEquals(list2.getListName(), user.getShopeeLists().get(1).getListName());
        assertEquals(2, user.getShopeeLists().size());
    }

    /**
     * Tests the addShopeeList(ShopeeList list) method.
     * It should be possible to add a shopeeList to the users list of shopee lists
     * It is not possible to add the same list more than once.
     */
    @Test
    public void testAddShopeeListShopeeList(){
        ShopeeList list1 = new ShopeeList("Week 40");
        ShopeeList list2 = new ShopeeList("Week 41");
 
        assertEquals(new ArrayList<>(), user.getShopeeLists());
        user.addShopeeList(list1);
        List<ShopeeList> shopeeLists = user.getShopeeLists();
        assertTrue(shopeeLists.contains(list1));
        assertFalse(shopeeLists.contains(list2));
 
        // Adding the same list twice should throw an exception
        try {
            user.addShopeeList(list1);
        } catch (Exception e) {
            assertEquals("Cant use same list name twice.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }  
    }

    /**
     * Tests the method getShopeeList(String name)
     * The method returns the shopee list object with the given name
     * Exception should be thrown if no such list exists for the given user
     */
    @Test
    public void testGetShopeeList() {
        ShopeeList list1 = new ShopeeList("Week 12");
        ShopeeList list2 = new ShopeeList("Week 13");

        user.addShopeeList(list1);
        user.addShopeeList(list2);

        assertEquals(list1, user.getShopeeList("Week 12"));
        assertEquals(list2, user.getShopeeList("Week 13"));
        try {
            user.getShopeeList("NonExistentList");
        } catch (Exception e) {
            assertEquals("No such list name for this user.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }  
    }
    
    /**
     * Tests the method deleteShopeeList(int index)
     * Throws an exception if the index passed as argument is unvalid
     */
    @Test
    public void testDeleteShopeeListInt() {
        ShopeeList list1 = new ShopeeList("Week 42");
        ShopeeList list2 = new ShopeeList("Week 43");
        ShopeeList list3 = new ShopeeList("Week 44");

        user.addShopeeList(list1);
        user.addShopeeList(list2);
        user.addShopeeList(list3);
        assertEquals(3, user.getShopeeLists().size());
        user.deleteShopeeList(1); // Delete "list2"

        assertTrue(user.getShopeeLists().contains(list1));
        assertFalse(user.getShopeeLists().contains(list2));
        assertTrue(user.getShopeeLists().contains(list3));
        
        // Passing an unvalid index as argument should throw an exception
        try {
            user.deleteShopeeList(4);
        } catch (Exception e) {
            assertEquals("Index is out of bounds.", e.getMessage());
            assertTrue(e instanceof IndexOutOfBoundsException);
        } 
    }

    /**
     * Tests the deleteShopeeList(String listName) method.
     */
    @Test
    public void testDeleteShopeeListString() {
        ShopeeList list1 = new ShopeeList("Week 42");
        ShopeeList list2 = new ShopeeList("Week 43");
        ShopeeList list3 = new ShopeeList("Week 44");

        user.addShopeeList(list1);
        user.addShopeeList(list2);
        user.addShopeeList(list3);
        assertEquals(3, user.getShopeeLists().size());
        user.deleteShopeeList(list2.getListName());

        assertTrue(user.getShopeeLists().contains(list1));
        assertFalse(user.getShopeeLists().contains(list2));
        assertTrue(user.getShopeeLists().contains(list3));

        try {
            user.deleteShopeeList("NonExistentList");
        } catch (Exception e) {
            assertEquals("No such list name for this user.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        } 
    }

    /**
     * Tests the replaceShopeeList(String listname, ShopeeList newList) method
     */
    @Test
    public void testReplaceShopeeList() {
        ShopeeList list1 = new ShopeeList("Week 42");
        ShopeeList list2 = new ShopeeList("Week 43");
        ShopeeList list3 = new ShopeeList("Week 44");

        user.addShopeeList(list1);
        user.addShopeeList(list2);
        user.addShopeeList(list3);
        assertEquals(3, user.getShopeeLists().size());
        System.out.println(user.getShopeeLists().toString());

        ShopeeList list4 = new ShopeeList("Week 44 new");
        user.replaceShopeeList("Week 44", list4);
        assertTrue(user.getShopeeLists().contains(list4));
        assertFalse(user.getShopeeLists().contains(list3));
        assertEquals(3, user.getShopeeLists().size());

        try {
            user.replaceShopeeList("Week 45", list4);
        } catch (Exception e) {
            assertEquals("No such list name for this user.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        } 
    }



}
