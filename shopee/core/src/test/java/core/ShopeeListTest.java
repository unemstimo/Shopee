package shopee.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * In this test class, all the main methods of the ShopeeList.java class are being tested.
 * Some of the smaller methods (such ass getters) are being indirectly tested and do not have their own tests. 
*/

public class ShopeeListTest {

    @Test
    public void testShopeeList(){
    }

    /**
     * Tests if the setListName(String listName) method works. 
     * This means that you can change the name of the ShopeeList.
     * This test also indirectly tests the validName(String listName) method to 
     * check that the criteria for listnames are being fulfilled. 
     */
    @Test
    public void testSetListName() {
        ShopeeList list2 = new ShopeeList("Une");
        //Checking if exception is beeing thrown if the listname includes special characters
        try{
            list2.setListName("Une@");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch(Exception e) {
            assertEquals("Shoplist name is not valid", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
        //checking if listname is updated if it is valid
        list2.setListName("Une2");
        assertEquals("Une2", list2.getListName());
    }

    /**
     * Tests if the addFoodShopList(String foodName, int amount) method works.
     */
    @Test
    public void testAddShopListFood(){
        ShopeeList list1 = new ShopeeList("Hanna");
        // tests if the correct food is added
        list1.addFoodShopList("apple", 4);
        assertEquals("apple", list1.getFood(0).getFoodName());
        assertEquals(4, list1.getFood(0).getFoodAmount());
    }

    /**
     * Tests if the setShopList(List<FoodItem> list) method works.
     * It should make it possible to initalize the ShopeeLists shopList in one take.
     */
    @Test
    public void testSetShopList() {
        ShopeeList list3 = new ShopeeList("Johan");
        assertTrue(list3.getShopList().isEmpty());
        
        List<FoodItem> shopList = new ArrayList<>();
        FoodItem food = new FoodItem("pear", 3);
        shopList.add(food);
        list3.setShopList(shopList);
        assertEquals(1, list3.getShopList().size());
        assertEquals("pear", list3.getFood(0).getFoodName());
    }

    /**
     * Tests if the setBoughtList(List<FoodItem> list) method works.
     * It should make it possible to initalize the ShopeeLists boughtList in one take.
     */
    @Test
    public void testSetBoughtList() {
        ShopeeList list3 = new ShopeeList("Johan");
        assertTrue(list3.getBoughtList().isEmpty());
        
        List<FoodItem> boughtList = new ArrayList<>();
        FoodItem food = new FoodItem("banana", 2);
        boughtList.add(food);
        list3.setBoughtList(boughtList);
        assertEquals(1, list3.getBoughtList().size());
        assertEquals("banana", list3.getBoughtItem(0).getFoodName());
    }

    /**
     * Tests if the addFoodBoughtList(FoodItem foodItem) method works.
     */
    @Test
    public void testAddFoodBoughtList() {
        ShopeeList list4 = new ShopeeList("Oskar");
        list4.addFoodShopList("kiwi", 5);
        assertTrue(list4.getBoughtList().isEmpty());

        list4.addFoodBoughtList(list4.getFood(0));
        assertEquals("kiwi", list4.getBoughtItem(0).getFoodName());
        assertEquals(5, list4.getBoughtItem(0).getFoodAmount());
        assertTrue(list4.getShopList().isEmpty());
    }

    /**
     * Tests if the removeFood(String foodName) method is working. 
     */
    @Test
    public void testRemoveFood() {
        ShopeeList list5 = new ShopeeList("Oskar");
        list5.addFoodShopList("bread", 2);
        list5.removeFood("bread");
        assertTrue(list5.getShopList().isEmpty());
    }

    /**
     * Tests if exception is being thrown when we check if a certain food 
     * (that is not in the shopList) is in the shoplist.
     */
    @Test
    public void testHasFood() {
        ShopeeList list6 = new ShopeeList("Hanna");
        list6.addFoodShopList("milk", 2);

        try{
            list6.hasFood("bread");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch(Exception e) {
            assertEquals("There is no such food in the list", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
}
