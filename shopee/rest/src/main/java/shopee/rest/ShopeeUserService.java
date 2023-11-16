package shopee.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

/**
 * Class responsible for connecting remote and local activity.
 */
@Service
public class ShopeeUserService {
    private List<User> allUsers;
    private FileHandeler shopeePersistence;
    private ObjectMapper mapper;

    /**
    * Constructor for the controller, creates a service object.
    *
    * @throws FileNotFoundException if files not found
    */
    public ShopeeUserService() throws FileNotFoundException {
        this.shopeePersistence = new FileHandeler("direct.json");
        this.allUsers = shopeePersistence.jsonToObj();
        this.mapper = new ObjectMapper();
    }

    /**
     * Gets all users from the database.
     *
     * @return a list of all users. 
     */
    public List<User> getAllUsers() {
        return this.allUsers;
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param allUsers setter for the list of Users
    */
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param username username of the user
    * @return a user with the matching username 
    */
    public User getUser(String username) {
        return allUsers.stream().filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param userString user object as a json string
    * @throws JsonMappingException if mapping is incorrect
    */
    public boolean addUser(String userString) throws JsonMappingException, JsonProcessingException {
        try {
            User user = mapper.readValue(userString, User.class);
            shopeePersistence.writeToFile(user);
            save();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param username name of the user where the shopeeList is added 
    * @param shopeeList shopeeList as a json string
    * @throws JsonMappingException if there is something wrong when parsing from string to object
    */
    public boolean addShopeeList(String username, String shopeeList)
            throws JsonMappingException, JsonProcessingException {
        try {
            List<User> users = shopeePersistence.jsonToObj();
            User user = users.stream().filter(u -> u.getUsername()
                    .equals(username)).findFirst().orElse(null);
            ShopeeList newList = mapper.readValue(shopeeList, ShopeeList.class);
            user.addShopeeList(newList);
            shopeePersistence.writeToFile(user);
            save();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param username name 
    * @param listName name of the list which is deleted
    * @throws FileNotFoundException if files not found
    */
    public boolean deleteShopeeList(String username, String listName) {
        try {
            User user = shopeePersistence.jsonToObj().stream().filter(u -> u.getUsername()
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

    /**
     * To update the list when changes are done.
     */
    public void save() {
        try {
            this.allUsers = shopeePersistence.jsonToObj();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an initial list of users. User for testing etc.
     */
    public static List<User> createInitialUser() throws FileNotFoundException {
        FileHandeler testHandler = new FileHandeler("direct.json");
        List<User> users = testHandler.jsonToObj();
        if (!users.isEmpty()) {
            return users;
        }

        ShopeeList list1 = new ShopeeList("PreLoadedList1");
        list1.addFoodShopList("Bread", 2);
        list1.addFoodShopList("Milk", 1);
        list1.addFoodShopList("Soda", 6);
        list1.addFoodShopList("Carrots", 6);

        ShopeeList list2 = new ShopeeList("initialList");
        list2.addFoodShopList("Meat", 1);
        list2.addFoodShopList("Coffee", 6);
        list2.addFoodShopList("Cake", 1);

        User exampleUser = new User("Terje@gmail.com", "Passord123@");
        exampleUser.addShopeeList(list1);
        exampleUser.addShopeeList(list2);

        User exampleUser2 = new User("Laylae@gmail.com", "Hunder123@");
        exampleUser2.addShopeeList(new ShopeeList("Gifts"));

        testHandler.writeToFile(exampleUser);
        testHandler.writeToFile(exampleUser2);

        List<User> exampleUsers = new ArrayList<>();
        exampleUsers.add(exampleUser);
        exampleUsers.add(exampleUser2);

        return exampleUsers;
    }
}
