package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodItemTest {

    @Test
    public void testSetFoodAmount() {
        FoodItem food = new FoodItem("Apple", 3);
        food.setFoodAmount(5);
        Assertions.assertEquals(5, food.getFoodAmount());
    }

    @Test
    public void testFoodName() {
        try{
            FoodItem food = new FoodItem("ban4n", 2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertEquals("The food name can only contain letters.", e.getMessage());
            assertTrue(e instanceof IllegalArgumentException);
        }

    }

}