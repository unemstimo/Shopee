package shopee.ui.dataaccess;

import shopee.core.User;
import shopee.core.FoodItem;
import shopee.core.ShopeeList;

/**
 *  Interface for both local and remote UserAccess
 */
public interface UserAccess {
    
    User loadUser(String username, String password);

    /**
     * Edit one of the users Shopee lists
     * 
     * @param listName
     * @param editedList
     * @return true if the Shopee list was edited 
     */
    //boolean editShopeeList(String listName, ShopeeList editedList);

    /**
     * Adds a new Shopee list to the user
     * 
     * @param shopeeList
     * @return true if the Shopee list was added to the user successfully 
     */
    boolean addShopeeList(ShopeeList shopeeList);

    /**
     * Deletes a Shopee list from the user
     * 
     * @param shopeeList
     * @return true if the Shopee list was deleted from the user successfully
     */
    boolean deleteShopeeList(String listName);

    /**
     * Adds a food item to the shop list
     * 
     * @param foodItem
     * @return true if the food item was added to the shop list successfully
     */
    boolean addFoodItem(String listName, FoodItem foodItem);

    /**
     * Removes a food item from the shop list
     * 
     * @param foodItem
     * @return true if the food item was removed from the shop list successfully
     */
    boolean removeFoodItem(String listName, FoodItem foodItem);

    /**
     * Adds the food item into the bought list, removes the same item from the shop list
     * 
     * @param foodItem
     * @return true if the food item was moved successfully
     */
    boolean markAsBought(String listName, FoodItem foodItem);

    /**
     * Sets the user
     * 
     * @param user
     */
    void setUser(User user);

    /**
     * Gets the user
     * 
     * @return the user
     */
    User getUser();
}
