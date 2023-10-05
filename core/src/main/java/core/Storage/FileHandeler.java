package core.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.FoodItem;
import core.ShopeeList;
import core.User;



public class FileHandeler {

    private final String filePath = "../core/src/main/java/core/Storage/DataStorage.json";

    public void writeToFile(User object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<User> objects = JsonToObj();
            

            // Check if an instance with the same name already exists
            String userName = "";
            if (object instanceof User) {
                userName = ((User) object).getUsername();
                System.out.println("Halla jeg kom litt inn");
                boolean instanceExists = false;
                for (int i = 0; i < objects.size(); i++) {
                    Object existingObject = objects.get(i);
                    System.out.println("heheh nærmer meg");
                    if (existingObject instanceof User) {
                        System.out.println("Hva faen hvorfor funker det ikke");
                        String existingListName = ((User) existingObject).getUsername();
                        if (userName.equals(existingListName)) {
                            // Update the existing instance
                            objects.set(i, object);
                            instanceExists = true;
                            System.out.println("brukernavnet matcher oppdaterer filen");
                            break;
                        }
                    }
                }
                if (!instanceExists) {
                    objects.add(object);
                }
            }

            this.clearFileContent();

            // Write the updated list of objects to the file
            mapper.writeValue(new File(filePath), objects);

            System.out.println("Object written to file\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> JsonToObj() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
    
            if (file.exists() && file.length() > 0) {
                List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {});
                return userList;
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<User> getAllObjectsFromFile(File file) {
        return JsonToObj();
    }

    public void clearFileContent() {
        
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write(""); // Clear the content by writing an empty string
                writer.close();
                System.out.println("File content cleared successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

public static void main(String[] args) {

    ShopeeList shopeeList = new ShopeeList("Oskar");
    FoodItem item1 = new FoodItem("mais", 100);
    FoodItem item2 = new FoodItem("hest", 19);
    FoodItem item3 = new FoodItem("pølse", 1);
    FoodItem item4 = new FoodItem("kjott", 7);
    shopeeList.addFoodShopList(item1.getFoodName(), item1.getFoodAmount());
    shopeeList.addFoodShopList(item2.getFoodName(), item2.getFoodAmount());
    shopeeList.addFoodShopList(item3.getFoodName(), item3.getFoodAmount());
    shopeeList.addFoodShopList(item4.getFoodName(), item4.getFoodAmount());
    shopeeList.addFoodBoughtList(item1);
    shopeeList.addFoodBoughtList(item4);

    ShopeeList shopeeL = new ShopeeList("Pelle");
    FoodItem ite1 = new FoodItem("mais", 100);
    FoodItem ite2 = new FoodItem("hest", 19);
    FoodItem ite3 = new FoodItem("pølse", 1);
    FoodItem ite4 = new FoodItem("kjott", 7);
    FoodItem ite5 = new FoodItem("mandler", 7);
    shopeeL.addFoodShopList(ite1.getFoodName(), item1.getFoodAmount());
    shopeeL.addFoodShopList(ite2.getFoodName(), ite2.getFoodAmount());
    shopeeL.addFoodShopList(ite3.getFoodName(), ite3.getFoodAmount());
    shopeeL.addFoodShopList(ite4.getFoodName(), ite4.getFoodAmount());
    shopeeL.addFoodShopList(ite5.getFoodName(), ite5.getFoodAmount());
    shopeeL.addFoodBoughtList(ite3);
    shopeeL.addFoodBoughtList(ite4);
    shopeeL.addFoodBoughtList(ite1);
    shopeeL.addFoodBoughtList(ite2);
    shopeeL.addFoodBoughtList(ite5);


    
    User oskar = new User("Osk.voldsund@gmail.no", "Oskar123@");
    //oskar.addShopeeList(shopeeList);
    oskar.setShopeeList(shopeeList);
    //oskar.addShopeeList(shopeeL);

    User johan = new User("Johan.legreid@gmail.no", "turbkka!r5Hs");
    //johan.addShopeeList(shopeeL);
    johan.setShopeeList(shopeeL);

    User une = new User("Une.marie@gmail.no", "9Gt21312312!");
    //une.addShopeeList(shopeeList);

    User hanna = new User("Hanna.kongleif@gmail.no", "Oskar123@");
    //hanna.addShopeeList(shopeeList);
    
    User filip = new User("filip.johnsen@gmail.no", "Oskar123@");
    //filip.addShopeeList(shopeeList);

    


    FileHandeler handeler = new FileHandeler();
    handeler.writeToFile(oskar);
    handeler.writeToFile(une);
    handeler.writeToFile(johan);
    handeler.writeToFile(hanna);
    handeler.writeToFile(filip);


    //System.out.println(handeler.JsonToObj().get(0).getShopeeList().get(0));
    // System.out.println(handeler.JsonToObj().get(1).getUsername());
    // System.out.println(handeler.JsonToObj().get(2).getUsername());
    // System.out.println(handeler.JsonToObj().get(3).getUsername());
        }
}


