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


}