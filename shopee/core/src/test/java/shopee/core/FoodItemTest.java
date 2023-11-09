package shopee.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Class to test the FoodItem-class
 */
public class FoodItemTest {

    private FoodItem food; // Food item to use in testing

    /**
     * Tests the setFoodAmount(int foodAmount) method.
     * This test also implicitly tests the validFoodAmount(String foodName) and the getFoodAmount() methods.
     * Checks if the method can change the foodAmount variable in the FoodItem object.
     * Checks if exception is being thrown if the user writes a number <= 0
     */
    @Test
    public void testSetFoodAmount() {
        // checking with a valid number as input, the constructor uses setFoodAmount(int foodAmount) to set the amount.
        food = new FoodItem("Apple", 5);
        assertEquals(5, food.getFoodAmount());

        // checking with a unvalid number as input, exception should be thrown
        try{
            food.setFoodAmount(-2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch(Exception e) {
            assertEquals("The amount of food needs to be 1 or higher", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Tests the setFoodName(String foodName) method. 
     * This test also implicitly tests the validFoodName(String foodName) and the getFoodName() methods.
     * A valid food name consists of letters only.
     * An unvalid name should throw an exception. 
     */
    @Test
    public void testSetFoodName() {
        // checking with a valid name as input, the constructor uses setFoodName(String foddName) to set the name.
        food = new FoodItem("Banana", 2);
        assertEquals("Banana", food.getFoodName());

        // checking with an unvalid name
        try{
            food.setFoodName("Ban4na");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertEquals("The food name can only contain letters.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

   /**
    * Tests the toString() method.
    * Checks if the string returned is the same as the expected string.
    */
    @Test
    public void testToString() {
        food = new FoodItem("Apple", 3);
        assertEquals("Apple : 3  STK", food.toString());
    }

}