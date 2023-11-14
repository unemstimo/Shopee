package shopee.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FoodItem class consist of a name and an amount. 
 */

public class FoodItem {

    private String foodName;
    private int foodAmount;

    /**
     * Constructor for a food item that sets the name,
     * the amount, and initializes the boolean bought value.
     *
     * @param foodName The name of the food.
     * @param foodAmount The amount of the food.
     */
    public FoodItem(@JsonProperty("foodName") String foodName,
     @JsonProperty("foodAmount") int foodAmount) {
        setFoodName(foodName);
        setFoodAmount(foodAmount);
    }

    /**
     * Sets the food name if the name is valid.
     *
     * @param foodName The name of the food.
     */
    public void setFoodName(final String foodName) {
        validFoodName(foodName);
        this.foodName = foodName;
    }

    /**
     * A setter to change the amount of the food.
     * The amount needs to be greater than zero; otherwise, an exception will be thrown.
     *
     * @param foodAmount The amount of the food.
     */
    public void setFoodAmount(final int foodAmount) {
        validFoodAmount(foodAmount);
        this.foodAmount = foodAmount;
    }

    /**
     * Gets the name of the food.
     *
     * @return The food name.
     */
    public String getFoodName() {
        return this.foodName;
    }

    /**
     * Gets the amount of the food object.
     *
     * @return The amount.
     */
    public int getFoodAmount() {
        return this.foodAmount;
    }

    /**
     * Helper method to check if the food name consists of letters only.
     *
     * @param foodName The name of the food.
     * @return True if the food name only consists of letters; otherwise, throws an exception.
     */
    private boolean validFoodName(final String foodName) {
        if (foodName.matches("^[A-Za-zåÅ ]+$")) {
            return true;
        } else {
            throw new IllegalArgumentException("The food name can only contain letters.");
        }
    }

    /**
     * Helper method to check if the amount of food is 1 or higher.
     *
     * @param foodAmount The amount of the food.
     * @return True if the amount is 1 or higher; otherwise, throws an exception.
     */
    private boolean validFoodAmount(final int foodAmount) {
        if (foodAmount > 0) {
            return true;
        } else {
            throw new IllegalArgumentException("The amount of food needs to be 1 or higher");
        }
    }

    /**
     * Proper toString to represent the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return foodName + " : " + foodAmount + "  STK";
    }
}

