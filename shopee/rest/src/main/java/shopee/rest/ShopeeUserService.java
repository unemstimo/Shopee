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
            this.allUsers = shopeePersistence.jsonToObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets all users from the database.
     * @return
     */
    public List<User> getAllUsers() {
        return this.allUsers;
    }

    /**
    * sets users, used for testing.
    * @param allUsers
    */
    public void setAllUsers(List<User> allUsers){
        this.allUsers = allUsers;
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

    /**
    * Creates an initial list of users. User for testing etc. 
    */
    public static List<User> createInitialUser() throws FileNotFoundException{
        FileHandeler testHandler = new FileHandeler();
        List<User> users = testHandler.jsonToObj();
        if(!users.isEmpty()){
            return users; 
        }
        List<User> exampleUsers = new ArrayList<>();
        User exampleUser = new User("Terje@gmail.com", "Passord123@");
        
        ShopeeList list1 = new ShopeeList("Target shoppinglist");
        list1.addFoodShopList("Bread", 2);
        list1.addFoodShopList("Milk", 1);
        list1.addFoodShopList("Soda", 6);
        list1.addFoodShopList("Carrots", 6);
        

        ShopeeList list2 = new ShopeeList("Rema 1000");
        list2.addFoodShopList("Meat", 1);
        list2.addFoodShopList("Coffee", 6);
        list2.addFoodShopList("Cake", 1);
        exampleUser.addShopeeList(list1);
        exampleUser.addShopeeList(list2);

        User exampleUser2 = new User("Laylae@gmail.com", "Hunder123@");
        exampleUser2.addShopeeList(new ShopeeList("Gifts"));
        
        testHandler.writeToFile(exampleUser);
        testHandler.writeToFile(exampleUser2);
        exampleUsers.add(exampleUser);
        exampleUsers.add(exampleUser2);
        
        return exampleUsers;
    }


}