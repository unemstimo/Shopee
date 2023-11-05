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
   

    public ShopeeUserService() {
        try {
            this.shopeePersistence = new FileHandeler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.allUsers = getAllUsers();
        
    }

    public List<User> getAllUsers() {
        try {
        return shopeePersistence.jsonToObj();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}