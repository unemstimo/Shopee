package shopee.rest;

import shopee.core.User;
import shopee.core.ShopeeList;
import shopee.core.FoodItem;
import shopee.json.FileHandeler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;


@Service
public class ShopeeUserService {
    private List<User> allUsers; 
    // private User shopeeUser;
    private FileHandeler shopeePersistence;
    private ObjectMapper mapper;
   
  /**
     * Constructor for ShopeeUserService.
     */
    public ShopeeUserService() {
        try {
            this.shopeePersistence = new FileHandeler();
            this.allUsers = shopeePersistence.jsonToObj();
            this.mapper = new ObjectMapper();
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
     * @return boolean, if the user was added, else false
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public boolean addUser(String userString) throws JsonMappingException, JsonProcessingException{
        try {
            User user = mapper.readValue(userString, User.class);
            if(user == null||user.getUsername().equals("")){
                throw new IllegalArgumentException("User was not created properly when adding user");
            }
            shopeePersistence.writeToFile(user);
            save();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
       
    }

      /**
     * Adds a shopeeList to a user.
     * @param username
     * @param newList
     * @return boolean, true if the user was added/replaced else false
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws IOException
     */
    public boolean addShopeeList(String username, String shopeeList) throws JsonMappingException, JsonProcessingException{
        try {
            List<User> users = shopeePersistence.jsonToObj();
             User user = users.stream().filter(u->u.getUsername()
            .equals(username)).findFirst().orElse(null);
            ShopeeList newList = mapper.readValue(shopeeList, ShopeeList.class);
            boolean added = false;
            for(ShopeeList list : user.getShopeeLists()){
                if(list.getListName().equals(newList.getListName())){
                    user.replaceShopeeList(newList.getListName(), newList);
                    added = true;
                }
            }
            if(!added){
                user.addShopeeList(newList);
            }
            shopeePersistence.writeToFile(user);
            save();
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
     * @return true if the shopeeList was deleted, else false
     * @throws IOException
     */
    public boolean deleteShopeeList(String username, String listName) throws IOException{
        try {
            if(username.equals("")||listName.equals("")){
                throw new IOException("name or listname is not given");
            }
            User user = shopeePersistence.jsonToObj().stream().filter(u->u.getUsername()
            .equals(username)).findFirst().orElse(null);
            user.deleteShopeeList(listName);
            shopeePersistence.writeToFile(user);
            save();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false; 
        }
        
    }

    /*
    * To update the list when changes are done
    */
    public void save(){
        try {
            this.allUsers = shopeePersistence.jsonToObj();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        
        ShopeeList list1 = new ShopeeList("PreLoadedList1");
        list1.addFoodShopList("Bread", 2);
        list1.addFoodShopList("Milk", 1);
        list1.addFoodShopList("Soda", 6);
        list1.addFoodShopList("Carrots", 6);
        

        ShopeeList list2 = new ShopeeList("initialList");
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