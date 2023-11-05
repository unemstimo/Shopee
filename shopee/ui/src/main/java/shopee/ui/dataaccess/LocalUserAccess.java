package shopee.ui.dataaccess;

import java.io.FileNotFoundException;
import java.util.List;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

/**
 * Sets the persistence storage file when using the application locally.
 */

public class LocalUserAccess implements UserAccess {

    private final FileHandeler filehandler = new FileHandeler();
    private List<User> users; 
    
    /**
     * Gets all users from the database
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        try {
            users = filehandler.jsonToObj();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Gets the user from the database
     */
    @Override
    public User getUser(String username) {
        this.getAllUsers();
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    /**
     * Adds a user to the local file
     * @param user
     */
    @Override
    public void addUser(User user) {
        try {
            filehandler.writeToFile(user);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.getAllUsers();
    }

    /**
     * Adds a shopee list to the local file
     */
    @Override
    public void addShopeeList(String username, ShopeeList newShopeeList) {
        getAllUsers();
        User user = getUser(username);
        user.addShopeeList(newShopeeList);
        try {
            filehandler.writeToFile(user);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes a shopee list from the local file
     */
    @Override
    public void deleteShopeeList(String Username, String listName) {
        getAllUsers();
        User user = getUser(Username);
        user.deleteShopeeList(listName);
        try {
            filehandler.writeToFile(user);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
    
}