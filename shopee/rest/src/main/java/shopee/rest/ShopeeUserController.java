package shopee.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import shopee.core.User;

/**
 * Class responsible for handling following requests through the REST API:
 * (GET,POST,PUT,DELETE,PATCH)
 */
@RestController
@RequestMapping("/users")

public class ShopeeUserController {

  @Autowired
  private ShopeeUserService userService;

  /**
   * Constructor for ShopeeUserController.
   * 
   * @param userService
   * @throws IllegalStateException
   * @throws IOException
   */
  public ShopeeUserController(final ShopeeUserService userService)
      throws IllegalStateException, IOException {
    this.userService = new ShopeeUserService();
  }

  /**
   * Gets all users from the database.
   * 
   * @return
   */
  @GetMapping("")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  /**
   * Gets a specific user from the database.
   * 
   * @param username
   * @return
   */
  @GetMapping("/{username}")
  public User getUser(@PathVariable("username") String username) {
    return userService.getUser(username);
  }

  /**
   * Adds a user to the database.
   * 
   * @param user
   * @return
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  @PostMapping("/add")
  public boolean addUser(@RequestBody String user) throws JsonMappingException, JsonProcessingException {
    userService.addUser(user);
    return true;
  }

  /**
   * Adds a shopeeList to a user in the database.
   * 
   * @param username
   * @param newList
   * @return
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  @PostMapping("/{username}/addList")
  public boolean addShopeeList(@PathVariable("username") String username, @RequestBody String newList)
      throws JsonMappingException, JsonProcessingException {
    userService.addShopeeList(username, newList);
    return true;
  }

  /**
   * Deletes a shopeeList from a user in the database.
   * 
   * @param username
   * @param listName
   * @throws IOException
   */
  @DeleteMapping("/{username}/{listName}")
  public boolean deleteShopeeList(@PathVariable("username") String username,
      @PathVariable("listName") String listName) throws IOException {
    userService.deleteShopeeList(username, listName);
    return true;
  }

}
