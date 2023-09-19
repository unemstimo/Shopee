package core.Storage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import core.FoodItem;
import core.ShopeeList;

public class WriteToFile {
  

    public void textWriter(ShopeeList shopeeList){
        // Specify the file path
        String filePath1 = "core/src/main/java/core/Storage/shopeList.txt";
        String filePath2 = "core/src/main/java/core/Storage/boughtList.txt";


        try {
            // Create a BufferedWriter and open the file in append mode
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(filePath1));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(filePath2));
            

            // Data to append
            // Write the data to the file
            // Add a newline for readability (optional)
            for (FoodItem food :shopeeList.getShopList()) {
                String name = food.getFoodName();
                String amount = Integer.toString(food.getFoodAmount()); 
                writer1.write(name + ", " + amount);
                writer1.newLine(); 
            }

            for (FoodItem food :shopeeList.getBoughtList()) {
                String name = food.getFoodName();
                String amount = Integer.toString(food.getFoodAmount()); 
                writer2.write(name + ", " + amount);
                writer2.newLine(); 
            }
           

            // Close the BufferedWriter
            writer1.close();
            writer2.close();

            System.out.println("Data appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while appending data to the file.");
        }
    }
}


