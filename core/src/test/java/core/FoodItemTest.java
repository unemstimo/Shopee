package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class to test the FoodItem-class
 */
public class FoodItemTest {

    /**
     * Tests the setFoodAmount(int foodAmount) method.
     * Checks if the method can change the foodAmount variable in the FoodItem object.
     * Checks if exception is being thrown if the user writes a number <= 0
     */
    @Test
    public void testSetFoodAmount() {
        FoodItem food = new FoodItem("Apple", 3);
        
        // checking with a valid number as input
        food.setFoodAmount(5);
        Assertions.assertEquals(5, food.getFoodAmount());

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
     * Tests the validFoodName(String foodName) method.
     * A valid food name consists of letters only.
     * An unvalid name should throw an exception. 
     */
    @Test
    public void testValidFoodName() {
        try{
            FoodItem food = new FoodItem("ban4n", 2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertEquals("The food name can only contain letters.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

}