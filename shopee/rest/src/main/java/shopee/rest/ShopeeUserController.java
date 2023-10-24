package shopee.rest;

import java.io.IOException;
import core.User;
/**
 * Class responsible for handling following requests through the REST API:
 * (GET,POST,PUT,DELETE)
 */
@RestController
@RequestMapping()

public class ShopeeUserController {

  @Autowired
  private ShopeeUserService userService;

  public ShopeeUserController(final ShopeeUserService userService)
      throws IllegalStateException, IOException {
    this.userService = new ShopeeUserService();
  }

  //Need methods for adding list, removing list, add/remove item, mark item as bought, 

  @GetMapping("/{userId}")
  public User get




    
    
}
