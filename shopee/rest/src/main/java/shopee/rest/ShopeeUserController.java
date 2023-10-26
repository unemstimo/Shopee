package shopee.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import shopee.core.User;
import shopee.core.ShopeeList;
import shopee.core.FoodItem;

/**
 * Class responsible for handling following requests through the REST API:
 * (GET,POST,PUT,DELETE,PATCH)
 */
@RestController
@RequestMapping()

public class ShopeeUserController {

  @Autowired
  private ShopeeUserService userService;

  public ShopeeUserController(final ShopeeUserService userService) throws IllegalStateException, IOException {
      this.userService = new ShopeeUserService();
  }



  @GetMapping
  public User getUser(){
    return userService.getUser();
  }


  @PostMapping(path = "/{name}") 
  public boolean addShoppeeList(@PathVariable("name") String name, @RequestBody ShopeeList shopeeList){
    userService.addShoppeList(shopeeList);
    return true;
  }

  @DeleteMapping(path = "/{name}") 
  public boolean deleteShopeeList(@PathVariable("name") String name, @RequestBody ShopeeList shopeeList ){
    userService.removeShopeeList(shopeeList);
    return true;

  }

  @PostMapping(path = "/{name}") 
  public boolean addFoodItem(@PathVariable("name") String listName, @RequestBody FoodItem foodItem){
    userService.addFoodItem(listName, foodItem);
    return true;
  }

  @DeleteMapping(path = "/{name}") 
  public boolean removeFoodItem(@PathVariable("name") String listName, @RequestBody FoodItem foodItem){
    userService.removeFoodItem(listName, foodItem);
    return true;
  }

  @PatchMapping(path = "/{name}/mark-as-bought")
  public boolean markAsBought(@PathVariable("name") String listName, @RequestBody FoodItem foodItem){
    userService.markAsBought(listName, foodItem);
    return true;
  }
  




    
    
}
