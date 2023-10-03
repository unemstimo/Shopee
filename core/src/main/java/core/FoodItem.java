package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodItem {
   
private String foodName;
private int foodAmount;


/**
 *Constructor for a food item that sets the name, 
 *the amount and initialize the boolean bought value 
 * 
 * @param food
 * @param amount
 */

public FoodItem(@JsonProperty("foodName") String foodName, @JsonProperty("foodAmount") int foodAmount) {
    this.foodName = foodName;
    this.foodAmount = foodAmount;
}

/**
 * A setter to change the amount of the food
 * 
 * @param amount
 */

public void setFoodAmount(int foodAmount) {
    this.foodAmount = foodAmount;
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
 * Proper to-wtring to represent the object
 * 
 */
@Override
public String toString() {
    return foodName + "," + foodAmount;
}

}
