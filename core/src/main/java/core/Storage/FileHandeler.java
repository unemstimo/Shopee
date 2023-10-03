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



public class FileHandeler {

    private final String filePath = "/Users/oskarvoldsund/ITP/gr2334/core/src/main/java/core/Storage/DataStorage.json";

    public void writeToFile(ShopeeList object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<ShopeeList> objects = JsonToObj();

            // Check if an instance with the same name already exists
            String listName = "";
            if (object instanceof ShopeeList) {
                listName = ((ShopeeList) object).getListName();
                boolean instanceExists = false;
                for (int i = 0; i < objects.size(); i++) {
                    Object existingObject = objects.get(i);
                    if (existingObject instanceof ShopeeList) {
                        String existingListName = ((ShopeeList) existingObject).getListName();
                        if (listName.equals(existingListName)) {
                            // Update the existing instance
                            objects.set(i, object);
                            instanceExists = true;
                            System.out.println("hallllllla");
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

    public List<ShopeeList> JsonToObj() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
    
            if (file.exists() && file.length() > 0) {
                List<ShopeeList> shopeeLists = mapper.readValue(file, new TypeReference<List<ShopeeList>>() {});
                return shopeeLists;
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return new ArrayList<>();
    }

    public List<ShopeeList> getAllObjectsFromFile(File file) {
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
    ShopeeList shopeeList = new ShopeeList();
    shopeeList.setListName("Oskar");
    FoodItem item1 = new FoodItem("mais", 100);
    FoodItem item2 = new FoodItem("hest", 19);
    FoodItem item3 = new FoodItem("pikk", 1);
    FoodItem item4 = new FoodItem("kjott", 7);
    FoodItem item10 = new FoodItem("fitte", 7);
    shopeeList.addFoodShopList(item1.getFoodName(), item1.getFoodAmount());
    shopeeList.addFoodShopList(item2.getFoodName(), item2.getFoodAmount());
    shopeeList.addFoodShopList(item3.getFoodName(), item3.getFoodAmount());
    shopeeList.addFoodShopList(item4.getFoodName(), item4.getFoodAmount());
    shopeeList.addFoodBoughtList(item1);
    shopeeList.addFoodBoughtList(item4);

    ShopeeList shopeeL = new ShopeeList();
    shopeeL.setListName("Peder");
    FoodItem item5 = new FoodItem("tomat", 100);
    FoodItem item6 = new FoodItem("ost", 19);
    FoodItem item7 = new FoodItem("fisk", 1);
    FoodItem item8 = new FoodItem("kjott", 7);
    shopeeL.addFoodShopList(item5.getFoodName(), item5.getFoodAmount());
    shopeeL.addFoodShopList(item6.getFoodName(), item6.getFoodAmount());
    shopeeL.addFoodShopList(item7.getFoodName(), item7.getFoodAmount());
    shopeeL.addFoodShopList(item8.getFoodName(), item8.getFoodAmount());
    shopeeL.addFoodBoughtList(item5);
    shopeeL.addFoodBoughtList(item7);

    FileHandeler handeler = new FileHandeler();
    handeler.writeToFile(shopeeList);

    shopeeList.addFoodShopList(item10.getFoodName(), item10.getFoodAmount());
    handeler.writeToFile(shopeeList);

    handeler.writeToFile(shopeeL);

    shopeeL.addFoodBoughtList(item8);
    shopeeL.addFoodBoughtList(item6);

    handeler.writeToFile(shopeeL);

        }
}


