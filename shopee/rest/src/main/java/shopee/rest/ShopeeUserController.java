package shopee.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopee.core.User;

/**
 * Class responsible for handling following requests through the REST API:
 * (GET,POST,PUT,DELETE,PATCH).
 */
@RestController
@RequestMapping("/users")

public class ShopeeUserController {

    @Autowired
  private ShopeeUserService userService;

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param userService takes inn a service
    * @throws FileNotFoundException if files not found
    */
    public ShopeeUserController(final ShopeeUserService userService) 
        throws FileNotFoundException {
        this.userService = new ShopeeUserService();
    }

    /**
    * Gets all users from the database.
    * 
    */
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
    * Gets a specific user from the database.
    *
    * @param username gets a user .
    * @return a User with the given username
    */
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param user takes inn a user as a json string
    * @throws JsonMappingException if it cant fint the the right path
    */
    @PostMapping("/add")
    public boolean addUser(@RequestBody String user) 
        throws JsonMappingException, JsonProcessingException {
        userService.addUser(user);
        return true;
    }

    /**
    * Constructor for the controller, creates a service object.
    *
    * @param username takes inn the username of the user
    * @param newList the new list as a json string 
    * @throws JsonMappingException if theres something wrong with the mapping
    */
    @PostMapping("/{username}/addList")
    public boolean addShopeeList(@PathVariable("username") String username,
     @RequestBody String newList) throws JsonMappingException, JsonProcessingException {
        userService.addShopeeList(username, newList);
        return true;
    }

    /**
    * Deletes a shopeeList from a user in the database.
    *
    * @param username takes a user where the list is supposed to be deleted
    * @param listName the name of the list that gets deleted.
    */
    @DeleteMapping("/{username}/{listName}")
    public boolean deleteShopeeList(@PathVariable("username") String username,
        @PathVariable("listName") String listName) {
        userService.deleteShopeeList(username, listName);
        return true;
    }

}
