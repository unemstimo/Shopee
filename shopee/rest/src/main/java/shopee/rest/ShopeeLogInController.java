package shopee.rest;

import java.io.IOException;


import shopee.core.User;

/**
 * Class responsible for handling following requests through the REST API:
 * (GET,POST,PUT,DELETE)
 */
@RestController
@RequestMapping()

public class ShopeeLogInController {


private ShopeeUserService userService;

public ShopeeUserController(ShopeeUserService userService) {
  
    this.userService = new ShopeeUserService();
}

  //Need methods for adding list, removing list, add/remove item, mark item as bought, 

@GetMapping("/{userId}")
public ResponseEntity<User> getUser(@PathVariable Long userId){
    
  User user = userService.getUserById(userId);
  if (user != null) {
      return ResponseEntity.ok(user);
  } else {
      return ResponseEntity.notFound().build();
  }


}


  




    
    
}
