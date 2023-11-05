package shopee.rest;

import shopee.core.User;
import shopee.core.ShopeeList;
import shopee.core.FoodItem;
import shopee.json.FileHandeler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;


@Service
public class ShopeeUserService {
    private List<User> allUsers; 
    // private User shopeeUser;
    private FileHandeler shopeePersistence;
   
  /**
     * Constructor for ShopeeUserService.
     */
    public ShopeeUserService() {
        try {
            this.shopeePersistence = new FileHandeler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.allUsers = getAllUsers();
        
    }


    /**
     * Gets all users from the database.
     * @return
     */
    public List<User> getAllUsers() {
        try {
        return shopeePersistence.jsonToObj();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a user from the database.
     * @param username
     * @return
     */
    public User getUser(String username){
        return allUsers.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }


    /**
     * Adds a user to the database.
     * @param userString
     */
    public void addUser(String userString){
        try {
            User user = shopeePersistence.jsonToUser(userString);
            if(user == null||user.getUsername().equals("")){
                throw new IllegalArgumentException("User was not created properly when adding user");
            }
            shopeePersistence.writeToFile(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
       
    }

      /**
     * Adds a shopeeList to a user.
     * @param username
     * @param newList
     * @return
     * @throws IOException
     */
    public boolean addShopeeList(String username, ShopeeList newList){
        try {
            List<User> users = shopeePersistence.jsonToObj();
             User user = users.stream().filter(u->u.getUsername()
            .equals(username)).findFirst().orElse(null);
            for(ShopeeList list : user.getShopeeLists()){
                if(list.getListName().equals(newList.getListName())){
                    user.replaceShopeeList(username, newList);
                }
            }
            shopeePersistence.writeToFile(user);
            return true;
                
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false; 
        }
       
    }

        /**
     * Deletes a shopeeList from a user.
     * @param username
     * @param listName
     * @return
     * @throws IOException
     */
    public boolean deleteShopeeList(String username, String listName) throws IOException{
        try {
            if(username.equals("")||listName.equals("")){
                throw new IOException("name or listname is null");
            }
            User user = shopeePersistence.jsonToObj().stream().filter(u->u.getUsername()
            .equals(username)).findFirst().orElse(null);
            user.deleteShopeeList(listName);
            shopeePersistence.writeToFile(user);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false; 
        }
        
    }


}