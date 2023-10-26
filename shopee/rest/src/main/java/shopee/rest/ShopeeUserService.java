package shopee.rest;

import shopee.core.User;
import shopee.core.ShopeeList;
import shopee.core.FoodItem;
import shopee.json.FileHandeler;

import java.util.IOException;
import java.util.List;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileReader;


@Service
public class ShopeeUserService {
    
    private User shopeeUser;
    private ShopeeList list;
    private FileHandeler shopeePersistence;
   

    public ShopeeUserService() throws IllegalStateException, IOException {
        this.shopeePersistence = new FileHandeler();
        this.shopeeUser = shopeePersistence.jsonToObj().stream().filter(u -> u.isActive());
    }

    public User getUser(){
        return this.shopeeUser;
    }

    public void setUser(User user){
        this.shopeeUser = user;
    }

    public static User createInitialUsers(){
        FileHandeler testPersistance = new FileHandeler();
        try (Reader rsreader = new FileReader(new File(System.getProperty("user.home") + File.separator
        + ("/src/main/resources/shopee/rest/initial-shopee.json")))) {
            ObjectMapper mapper = testPersistance.createObjectMapper();
            return mapper.readValue(reader, User.class);
            
        } catch (IOException e) {
            System.out.println("Could not read the initial cookbook... tries to make it manually (" + err + ")");
        }
    }


    public boolean addShopeeList(ShopeeList shopeeList){
        shopeeUser.addShopeeList(shopeeList);
        writeToFile(shopeeUser);
        return true;
    }

    public boolean deleteShopeeList(int i){
        shopeeUser.deleteShopeeList(i);
        writeToFile(shopeeUser);
        return true;
    }

    public boolean addFoodItem(String listName, FoodItem foodItem){
        list = shopeeUser.getShopeeList(listName);
        list.addFoodItem(foodItem.getFoodName(), foodItem.getFoodAmount());
        shopeePersistance.writeToFile(shopeeUser);
        return true;
    }

    public boolean removeFoodItem(String listName, FoodItem foodItem){
        list = shopeeUser.getShopeeList(listName);
        list.removeFoodItem(foodItem.getFoodName());
        shopeePersistance.writeToFile(shopeeUser);
        return true;
    }

    public boolean markAsBought(String listName, FoodItem foodItem){
        list = shopeeUser.getShopeeList(listName);
        list.addFoodBoughtList(foodItem);
        shopeePersistance.writeToFile(shopeeUser);
        return true;
    }

   



}
