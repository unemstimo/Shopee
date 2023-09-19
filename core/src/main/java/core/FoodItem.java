package core;

public class FoodItem {
    
private final String food;
private int amount;


/**
 *Constructor for a food item that sets the name, 
 *the amount and initialize the boolean bought value 
 * 
 * @param food
 * @param amount
 */
public FoodItem(String food, int amount) {
    this.food = food;
    this.amount = amount;
}

/**
 * A setter to change the amount of the food
 * 
 * @param amount
 */

public void setAmount(int amount) {
    this.amount = amount;
}

/**
 * Gets the name of the food
 * 
 * @return the foodname
 */
public String getFoodName() {
    return this.food;
}


/**
 * Gets the amount of the food object
 * 
 * @return the amount
 */
public int getFoodAmount() {
    return this.amount;
}


@Override
public String toString() {
    return food + "," + amount;
}

}
