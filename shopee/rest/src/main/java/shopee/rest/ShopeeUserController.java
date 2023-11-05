package shopee.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
@RequestMapping("/users")

public class ShopeeUserController {
  @Autowired
  private ShopeeUserService userService;

  public ShopeeUserController(final ShopeeUserService userService) throws IllegalStateException, IOException {
      this.userService = new ShopeeUserService();
  }

  @Bean
  public ShopeeUserService listContainerBean()  {
    return new ShopeeUserService();
  }

    
    
}
