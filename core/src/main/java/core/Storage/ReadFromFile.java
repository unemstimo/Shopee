package core.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import core.ShopeeList;
import core.FoodItem;

public class ReadFromFile {
      
    public ShopeeList filereader() {
        String filePath1 = "core/src/main/java/core/Storage/shopeList.txt"; 
        String filePath2 = "core/src/main/java/core/Storage/boughtList.txt"; 

        ShopeeList shopeeList = new ShopeeList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath1))) {
            String line;
            

            while ((line = reader.readLine()) != null) {
                // Process each line from the file
                System.out.println(line);
                String[] fields = line.split(",");
                shopeeList.addFoodShopList(fields[0],Integer.parseInt(fields[1].strip()));
                //food.isStatus(fields[2]);
               
                System.out.println(fields[0].strip() + " jaaa " + fields[1].strip());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while reading from the file.");
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath2))) {
            String line;
            

            while ((line = reader.readLine()) != null) {
                // Process each line from the file
                System.out.println(line);
                String[] fields = line.split(",");
                FoodItem item = new FoodItem(fields[0],Integer.parseInt(fields[1].strip()));
                shopeeList.addFoodBoughtList(item);
                //food.isStatus(fields[2]);
               
                System.out.println(fields[0].strip() + "" + fields[1].strip());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while reading from the file.");
        }

        System.out.println(shopeeList);
        return shopeeList;
    }
}
