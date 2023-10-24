package shopee.ui.dataaccess;

import java.io.IOException;
import java.util.NoSuchElementException;

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

/**
 * Sets the persistence storage file when using the application locally.
 */

public class LocalUserAccess implements UserAccess {

    private final FileHandeler persistence = new FileHandeler();
    private User user = new User();

    /**
     * Constructor to initialize persistence
     */
    public LocalUserAccess() {
        persistence.setSaveFilePath("Shopee.json");
        try {
        this.user = persistence.loadUser();
        } catch (IllegalStateException | IOException e) {
        this.user = new User();
        }
    }

    public LocalUserAccess(String path) {
        persistence.setSaveFilePath(path);
        try {
        this.user = persistence.loadUser();
        } catch (IllegalStateException | IOException e) {
        this.user = new User();
        } 
    }

    @Override
    public User loadUser(String username, String password) {
        try {
            user.setUsername(username);
            user.setPassword(password);
            return user;
          } 
          catch (IllegalStateException | NoSuchElementException e) {
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
    }

    @Override
    public boolean editShopeeList(String listName, ShopeeList editedList) {
        ShopeeList existingList = user.getShopeeList(listName);
        existingList.setShopList(editedList.getShopList());
        existingList.setBoughtList(editedList.getBoughtList());
    }

    @Override
    public boolean addShopeeList(ShopeeList shopeeList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addShopeeList'");
    }

    @Override
    public boolean deleteShopeeList(ShopeeList shopeeList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShopeeList'");
    }

    @Override
    public void setUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }
    
}
