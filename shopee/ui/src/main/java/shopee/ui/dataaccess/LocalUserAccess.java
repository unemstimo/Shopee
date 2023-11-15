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

    public LocalUserAccess() throws FileNotFoundException {
        filehandler.setFilePath("direct.json");
    }
    
    /**
    * Gets user saved.
    *
    * @return all saved users from the json file.
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
    * Gets a user.
    *
    * @param username name of the user
    * @return a User with the given username
    */
    @Override
    public User getUser(String username) {
        List<User> allUsers = this.getAllUsers();
        return allUsers.stream().filter(u -> u.getUsername()
            .equals(username)).findFirst().orElse(null);
    }

    /**
    * Adds a user to the local file.
    *
    * @param user adds a given user to the local file.
    */
    @Override
    public void addUser(User user) {
        try {
            filehandler.writeToFile(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.getAllUsers();
    }

    /**
    * Adds a shopeeList to a user. 
    *
    * @param username name of the user
    * @param newShopeeList the ShopeeList which is being added
    */
    @Override
    public void addShopeeList(String username, ShopeeList newShopeeList) {
        getAllUsers();
        User user = getUser(username);
        user.addShopeeList(newShopeeList);
        try {
            filehandler.writeToFile(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    * Deletes a ShopeeList from a user.
    *
    * @param username name of the user.
    * @param listName name of the list which i being deleted
    */
    @Override
    public void deleteShopeeList(String username, String listName) {
        getAllUsers();
        User user = getUser(username);
        user.deleteShopeeList(listName);
        try {
            filehandler.writeToFile(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
}