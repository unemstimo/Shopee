package shopee.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import shopee.core.User;

/**
 * FileHandler class for handling file operations related to User objects.
 */
public class FileHandeler {

    private Path filePath;

    /**
     * Constructor that sets the filepath.
     *
     * @param filepath the path of the file
     * @throws FileNotFoundException if the file is not found
     */
    public FileHandeler(String filepath) throws FileNotFoundException {
        setFilePath(filepath);
    }

    /**
     * Default constructor.
     */
    public FileHandeler() {
    }

    /**
     * Method for setting the filepath.
     *
     * @param filename the name of the file to use
     * @throws FileNotFoundException if the file is not found
     */
    public void setFilePath(String filename) throws FileNotFoundException {
        try {
            String filePath = filename;
            this.filePath = Paths.get(System.getProperty("user.home"), filePath);
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }
    }

    /**
     * Method which writes a User object to the file.
     *
     * @param object the User object to write
     * @throws FileNotFoundException if the file is not found
     */
    public void writeToFile(User object) throws FileNotFoundException {
        try {
            ObjectMapper mapper = createObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<User> objects = jsonToObj();

            String userName = object.getUsername();
            boolean instanceExists = false;

            for (int i = 0; i < objects.size(); i++) {
                User existingObject = objects.get(i);

                if (userName.equals(existingObject.getUsername())) {
                    objects.set(i, object);
                    instanceExists = true;
                    break;
                }
            }

            if (!instanceExists) {
                objects.add(object);
            }

            clearFileContent();

            mapper.writeValue(filePath.toFile(), objects);
            System.out.println("Object written to file\n");

        } catch (IOException e) {
            throw new FileNotFoundException("Failed to write to file" + e);
        }
    }

    /**
     * Method for reading User objects from the file.
     *
     * @return a list of User objects
     * @throws FileNotFoundException if the file is not found
     */
    public List<User> jsonToObj() throws FileNotFoundException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = filePath.toFile();

            if (file.exists() && file.length() > 0) {
                return mapper.readValue(file, new TypeReference<List<User>>() {});
            }

        } catch (IOException e) {
            throw new FileNotFoundException("Failed to read file" + e);
        }
        return new ArrayList<>();
    }

    /**
     * Helper method that removes all content in the file.
     */
    public void clearFileContent() {
        try {
            File file = new File(filePath.toString());

            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
                    writer.write(""); // Clear the content by writing an empty string
                }

                System.out.println("File content cleared successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to create a new object mapper.
     *
     * @return a new object mapper
     */
    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}
