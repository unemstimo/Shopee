package core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodItem {
   
private String foodName;
private int foodAmount;

/**
 * Constructor for a food item that sets the name, 
 * the amount and initialize the boolean bought value 
 * 
 * @param food
 * @param amount
 */
public FoodItem(@JsonProperty("foodName") String foodName, @JsonProperty("foodAmount") int foodAmount) {
    setFoodName(foodName);
    setFoodAmount(foodAmount);
}

/**
 * Sets the food name if the name is valid
 * 
 * @param foodName
 */
public void setFoodName(String foodName) {
    if(validFoodName(foodName)) {
        this.foodName = foodName;
    }
}

/**
 * A setter to change the amount of the food.
 * The amount needs to be greater than zero, if not: an exception will be thrown.
 * 
 * @param amount
 */
public void setFoodAmount(int foodAmount) {
    if(validFoodAmount(foodAmount)) {
        this.foodAmount = foodAmount;
    }
}

/**
 * Gets the name of the food
 * 
 * @return the foodname
 */
public String getFoodName() {
    return this.foodName;
}


/**
 * Gets the amount of the food object
 * 
 * @return the amount
 */
public int getFoodAmount() {
    return this.foodAmount;
}

/**
 * Helper method to check if the food name consists of letters only
 * 
 * @param foodname
 * @return True if foodname only consists of letters, throws an exception otherwise
 */
private boolean validFoodName(String foodname) {
    if (foodname.matches("^[A-Za-zåÅ]+$")) {
        return true;
    }
    else{
        throw new IllegalArgumentException("The food name can only contain letters.");
    }
}

/**
 * Helper method to check if the amount of food is 1 or higher
 * 
 * @param foodAmount
 * @return True if foodname only consists of letters, throws an exception otherwise
 */
private boolean validFoodAmount(int foodAmount) {
    if(foodAmount > 0) {
        return true;
    }
    else{
        throw new IllegalArgumentException("The amount of food needs to be 1 or higher");
    }
}

/**
 * Proper to-string to represent the object
 * 
 */
@Override
public String toString() {
    return foodName + " : " + foodAmount + "  STK";
}

}
