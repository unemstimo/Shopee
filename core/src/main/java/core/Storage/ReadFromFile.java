package core.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import core.ShopeeList;

public class ReadFromFile {
      
    public ShopeeList filereader() {
        String filePath1 = "core/src/main/java/core/Storage/shopeList.txt"; 
        String filePath2 = "core/src/main/java/core/Storage/boughtList.txt"; 

        ShopeeList shopeeList = new ShopeeList();

        //Reads from the shoplist text file

        try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1))) {
            String line;
            

            while ((line = reader1.readLine()) != null) {
                // Process each line from the file
                String[] fields = line.split(",");
                shopeeList.loadShopListFile(fields[0],Integer.parseInt(fields[1].strip()));
            }

            reader1.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while reading from the file.");
        } 
        
        //Reads from the bough item text file

         try (BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {
            String line;

            while ((line = reader2.readLine()) != null) {
                // Process each line from the file
                String[] fields = line.split(",");
                shopeeList.loadBoughtListFile(fields[0],Integer.parseInt(fields[1].strip()));
            }

            reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while reading from the file.");
        }
        
       

        System.out.println(shopeeList.getBoughtList());
        System.out.println(shopeeList.getShopList());

        return shopeeList;
    }
}
