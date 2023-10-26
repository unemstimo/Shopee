package shopee.ui.dataaccess;

import java.util.List;
import java.util.NoSuchElementException;

import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

/**
 * Sets the persistence storage file when using the application locally.
 */

public class LocalUserAccess implements UserAccess {

    private final FileHandeler persistence = new FileHandeler();
    private User user = new User();
    private ShopeeList shopeeList;

    /**
     * Constructor to initialize persistence
     */
    public LocalUserAccess() {
        //persistence.setSaveFilePath("Shopee.json");
        //try {
        //this.user = persistence.loadUser();
            List<User> users = persistence.jsonToObj();

            boolean newUser = true;
            for (User userInFile : users) {
                if(userInFile.getUsername().equals(user.getUsername()) && userInFile.getPassword().equals(user.getPassword())){
                    this.user = userInFile;
                    newUser = false;
                    break;
                }
            }

            if(newUser) {
                this.user = new User();
                persistence.writeToFile(user);
            }

        //} catch (IllegalStateException | IOException e) {
        //    this.user = new User();
       // }
    }

    /*public LocalUserAccess(String path) {
        persistence.setSaveFilePath(path);
        try {
        this.user = persistence.loadUser();
        } catch (IllegalStateException | IOException e) {
        this.user = new User();
        } 
    }*/

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

    /*@Override
    public boolean editShopeeList(String listName, ShopeeList editedList) {
        ShopeeList existingList = user.getShopeeList(listName);
        existingList.setShopList(editedList.getShopList());
        existingList.setBoughtList(editedList.getBoughtList());
    }*/

    @Override
    public boolean addShopeeList(ShopeeList shopeeList) {
        try{
            user.addShopeeList(shopeeList);
            persistence.writeToFile(user);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteShopeeList(String listName) {
       try{
            user.deleteShopeeList(listName);
            persistence.writeToFile(user);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addFoodItem(String listName, FoodItem foodItem) {
        try{
            shopeeList = user.getShopeeList(listName);
            shopeeList.addFoodShopList(foodItem.getFoodName(), foodItem.getFoodAmount());
            persistence.writeToFile(user);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeFoodItem(String listName, FoodItem foodItem) {
        try{
            shopeeList = user.getShopeeList(listName);
            shopeeList.removeFood(foodItem.getFoodName());
            persistence.writeToFile(user);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean markAsBought(String listName, FoodItem foodItem) {
        try{
            shopeeList = user.getShopeeList(listName);
            shopeeList.addFoodBoughtList(foodItem);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }
    
}
