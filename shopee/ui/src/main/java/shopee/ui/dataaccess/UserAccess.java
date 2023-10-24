package shopee.ui.dataaccess;

import shopee.core.User;
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
    boolean editShopeeList(String listName, ShopeeList editedList);

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
    boolean deleteShopeeList(ShopeeList shopeeList);

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
