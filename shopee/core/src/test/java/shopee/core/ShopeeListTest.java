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

    private ShopeeList list; // Shopee list to use in testing

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
        list = new ShopeeList("Une");

        // Checking if exception is beeing thrown if the listname includes special characters
        try{
            list.setListName("Une@");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch(Exception e) {
            assertEquals("Shoplist name is not valid", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

        // Checking if listname is updated if it is valid
        list.setListName("Une2");
        assertEquals("Une2", list.getListName());
    }

    /**
     * Tests if the addFoodShopList(String foodName, int amount) method works.
     * If the food item already exists we only change the amount to the new amount.
     * If the food item does not exist we will add a new FoodItem. 
     */
    @Test
    public void testAddFoodShopList(){
        list = new ShopeeList("Hanna");
        
        // When the food item does not exist in the shop list beforehand:
        assertEquals("[]", list.getShopList());
        list.addFoodShopList("Apple", 4);
        assertEquals("Apple", list.getFood(0).getFoodName());
        assertEquals(4, list.getFood(0).getFoodAmount());

        // When the food item already exists in the shop list:
        list.addFoodShopList("Banana", 2);
        list.addFoodShopList("Apple", 5);
        assertEquals(5, list.getFood("Apple").getFoodAmount());
    }

    /**
     * Tests if the setShopList(List<FoodItem> list) method works.
     * It should make it possible to initalize the ShopeeLists shopList in one take.
     */
    @Test
    public void testSetShopList() {
        list = new ShopeeList("Johan");
        assertTrue(list.getShopList().isEmpty());
        
        List<FoodItem> shopList = new ArrayList<>();
        FoodItem food = new FoodItem("Pear", 3);
        shopList.add(food);
        list.setShopList(shopList);
        assertEquals(1, list.getShopList().size());
        assertEquals("Pear", list.getFood(0).getFoodName());
    }

    /**
     * Tests if the setBoughtList(List<FoodItem> list) method works.
     * It should make it possible to initalize the ShopeeLists boughtList in one take.
     */
    @Test
    public void testSetBoughtList() {
        list = new ShopeeList("Johan");
        assertTrue(list.getBoughtList().isEmpty());
        
        List<FoodItem> boughtList = new ArrayList<>();
        FoodItem food = new FoodItem("Banana", 2);
        boughtList.add(food);
        list.setBoughtList(boughtList);
        assertEquals(1, list.getBoughtList().size());
        assertEquals("Banana", list.getBoughtItem(0).getFoodName());
    }

    /**
     * Tests if the addFoodBoughtList(FoodItem foodItem) method works.
     * This test also implicitly tests the removeFood(String foodName) method.
     * This method adds the food item selected from the shop list to the bought list,
     * and then removes the food item from the shop list.
     */
    @Test
    public void testAddFoodBoughtList() {
        list = new ShopeeList("Oskar");
        list.addFoodShopList("Kiwi", 5);
        assertTrue(list.getBoughtList().isEmpty());

        list.addFoodBoughtList(list.getFood(0));
        assertEquals("Kiwi", list.getBoughtItem(0).getFoodName());
        assertEquals(5, list.getBoughtItem(0).getFoodAmount());
        assertTrue(list.getShopList().isEmpty());
    }

    /**
     * Tests if exception is being thrown when we check if a certain food 
     * (that is not in the shopList) is in the shoplist.
     */
    @Test
    public void testHasFood() {
        list = new ShopeeList("Hanna");
        list.addFoodShopList("Milk", 2);

        try{
            list.hasFood("Bread");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch(Exception e) {
            assertEquals("There is no such food in the list", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests if the getFoodAmount(String foodName) method in the ShopeeList.java class works.
     */
    @Test
    public void testGetFoodAmount() {
        list = new ShopeeList("Une");
        list.addFoodShopList("Avocado", 4);
        assertEquals(4, list.getFoodAmount("Avocado"));
    }

    /**
     * Tests the toString() method.
     * Checks if the string returned is the same as the expected string.
     */
    @Test
    public void testToString() {
        list = new ShopeeList("Oskar");
        assertEquals("Oskar", list.toString());
    }

}
