package core;

public class FoodItem {
    
private final String Food;
private int amount;

private boolean bought;


/**
 *Constructor for a food item that sets the name, 
 *the amount and initialize the boolean bought value 
 * 
 * @param Food
 * @param amount
 */
public FoodItem(String Food, int amount) {
    this.Food = Food;
    this.amount = amount;
    this.bought = false;
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
    return this.Food;
}


/**
 * Gets the amount of the food object
 * 
 * @return the amount
 */
public int getFoodAmount() {
    return this.amount;
}


/**
 * Gets the boolean if the food is bought
 * 
 * @return
 */
public boolean isBought() {
    return bought;
}


/**
 * Sets the bought variable true
 * 
 */
public void setBought() {
    this.bought = true;
}


@Override
public String toString() {
    return Food + "," + amount;
}

}
