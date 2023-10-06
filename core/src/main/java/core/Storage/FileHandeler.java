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

    //the relavtive path to DataStorage.json
    private final String filePath = "/core/src/main/java/core/Storage/DataStorage.json";


    //new method which writes to the DataStorage.json file
    public void writeToFile(User object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            //makes the text more readable when written to file

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            //copies all User objects in the file and puts it to a list
            List<User> objects = JsonToObj();
            objects.add(object);

            // Check if an instance with the same name already exists
            // String userName = "";
            // if (object instanceof User) {
            //     userName = ((User) object).getUsername();
            //     boolean instanceExists = false;
            //     for (int i = 0; i < objects.size(); i++) {
            //         Object existingObject = objects.get(i);
            //         if (existingObject instanceof User) {
            //             String existingListName = ((User) existingObject).getUsername();
            //             if (userName.equals(existingListName)) {
            //                 // Update the existing instance
            //                 objects.set(i, object);
            //                 instanceExists = true;
            //                 System.out.println("brukernavnet matcher oppdaterer filen");
            //                 break;
            //             }
            //         }
            //     }
            //     if (!instanceExists) {
            //         objects.add(object);
            //     }
            // }
            // works without this, because we validate in the loginController, dont know if we should move validation here though
            // so i wont remove it yet

            //self created method which wipes the file such that no duplicates are saved
            this.clearFileContent();

            // Write the updated list of objects to the file
            mapper.writeValue(new File(filePath), objects);

            System.out.println("Object written to file\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method for reading from file
    public List<User> JsonToObj() {
        try {

            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            
            // Creates a list of all saved User objects
            if (file.exists() && file.length() > 0) {
                List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {});
                return userList;
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // //returns a list of all User objects 
    // public List<User> getAllObjectsFromFile(File file) {
    //     return JsonToObj();
    // }

    //Helper method that removes all content in the file
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

    
    User oskar = new User("Osk.voldsund@gmail.no", "Oskar123@");
    //oskar.addShopeeList(shopeeList);
    oskar.setShopeeList(shopeeList);
    FileHandeler handeler = new FileHandeler();
    handeler.writeToFile(oskar);
        }
}


