package shopee.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import shopee.core.User;

public class FileHandeler {

    // Use your local path to the file when running maven
   private String filePath = "ui/src/main/resources/shopee/DataStorage.json";

    // Use this relative path when running in launch
    // private String filePath = "core/src/main/java/shopee/json/DataStorage.json";

    // new method which writes to the DataStorage.json file
    public void writeToFile(User object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // makes the text more readable when written to file
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // copies all User objects in the file and puts it to a list
            List<User> objects = jsonToObj();

            // Check if an instance with the same name already exists
            String userName = "";

            userName = ((User) object).getUsername();
            boolean instanceExists = false;
            for (int i = 0; i < objects.size(); i++) {
                Object existingObject = objects.get(i);
                if (existingObject instanceof User) {
                    String existingListName = ((User) existingObject).getUsername();
                    if (userName.equals(existingListName)) {
                        // Update the existing instance
                        objects.set(i, object);
                        instanceExists = true;
                        break;
                    }
                }
            }
            if (!instanceExists) {
                objects.add(object);
            }


            // self created method which wipes the file such that no duplicates are saved
            this.clearFileContent();

            // Write the updated list of objects to the file
            mapper.writeValue(new File(filePath), objects);
            System.out.println("Object written to file\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method for reading from file
    public List<User> jsonToObj() {
        try {

            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);

            // Creates a list of all saved User objects
            if (file.exists() && file.length() > 0) {
                List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {
                });
                return userList;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Helper method that removes all content in the file
    public void clearFileContent() {

        try {
            File file = new File(filePath);
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
                    writer.write(""); // Clear the content by writing an empty string
                    writer.close();
                }
                System.out.println("File content cleared successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getAbsolutePathOfFileName(String fileName) {
        Path root = Paths.get(System.getProperty("user.dir")); // Set the root directory for your search
        try {
            // Walk through the file system starting from the root
            Path result = Files.walk(root)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .orElse(null);

            if (result != null) {
                return result.toAbsolutePath().toString();
            } else {
                System.out.println("File not found: " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
