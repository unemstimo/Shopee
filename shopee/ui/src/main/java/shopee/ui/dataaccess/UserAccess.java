package shopee.ui.dataaccess;

import shopee.core.User;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import shopee.core.ShopeeList;

/**
 *  Interface for both local and remote UserAccess
 */
public interface UserAccess {
    
    /**
     * Adds a new Shopee list to the user
     * 
     * @param shopeeList
     * @return true if the Shopee list was added to the user successfully 
     */
    void addShopeeList(String username, ShopeeList newShopeeList) throws JsonProcessingException;

    /**
     * Deletes a Shopee list from the user
     * 
     * @param shopeeList
     * @return true if the Shopee list was deleted from the user successfully
     */
    void deleteShopeeList(String username, String listname);

    List<User> getAllUsers();

    void addUser(User user) throws JsonProcessingException;

    /**
     * Gets the user
     * 
     * @return the user
     */
    User getUser(String username);
}
