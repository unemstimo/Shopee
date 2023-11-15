package shopee.ui.dataaccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import shopee.core.ShopeeList;
import shopee.core.User;

/**
 *  Interface for both local and remote UserAccess.
 */
public interface UserAccess {
    
    /**
    *  Adds a new Shopee list to the user.
    *
    * @param username username to the user that the shopeeList is added to
    * @param newShopeeList the shopeelist that gets added to the user
    */
    void addShopeeList(String username, ShopeeList newShopeeList) throws JsonProcessingException;

    /**
    *  Deletes a Shopee list from the user.
    *
    * @param username username to the user that the shopeeList is deleted from
    * @param listname the name of the ShopeeList that gets deleted
    */
    void deleteShopeeList(String username, String listname);

    /**
    *  Retrieves all currently saved users.
    *
    * @return a list of user-objects
    */
    List<User> getAllUsers();

    /**
    *  Adds a new user to the List of Users.
    *
    * @param user new user that gets added
    * @throws JsonProcessingException e
    */
    void addUser(User user) throws JsonProcessingException;

    /**
    *  Retrieves a User with the matching username.
    *
    * @param username username to the user 
    * @return a user 
    */
    User getUser(String username);
}