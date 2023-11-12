package shopee.ui.dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import shopee.core.ShopeeList;
import shopee.core.User;

public class RemoteUserAccess implements UserAccess{
    
    private final URI endpointUri;
  
  
  /**
   * Constructor for the RemoteUserAccess class.
   * 
   * @param uri
   * @param mock
   * @throws IOException
   * @throws InterruptedException
   */
    public RemoteUserAccess(URI uri, Boolean mock) throws IOException, InterruptedException {
      if (!mock) {
          HttpRequest request = HttpRequest.newBuilder(uri)
                  .header("Accept", "application/json").GET().build();

          try {
              final HttpResponse<Void> response = HttpClient.newBuilder().build().send(request,
                      HttpResponse.BodyHandlers.discarding());

              int statusCode = response.statusCode();

              if (statusCode != 200) {
                  throw new IOException("Server is not running. HTTP Status Code: " + statusCode);
              }
          } catch (IOException e) {
              throw new IOException("Error checking server status: " + e.getMessage());
          }
      }
      this.endpointUri = uri;
  }

          /**
    * Method for creating the correct path to the URI.
    *
    * @param uri   the uri you want to find the path to.
    * @return URI  the endpoint URI
    */
    private URI shoppingListUri(String uri) {  
        return endpointUri.resolve(uri);
    }

    /**
     * Method for getting all users from the remote database.
     */
      @Override
    public List<User> getAllUsers() { // Brukes i Login?? for å sjekke om bruker finnes????
        HttpRequest request = HttpRequest.newBuilder(endpointUri)
            .header("Accept", "application/json")
            .GET().build();
        
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
                final String responseString = response.body();
    
                ObjectMapper objectMapper = new ObjectMapper();
    
                List<User> users = objectMapper.readValue(responseString, new TypeReference<List<User>>() {});
    
                return users;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
      }



    /**
     * Method for getting a user from the remote database.
     */
    @Override
    public User getUser(String username) {
        String value = "users/" + username;

        try {
        HttpRequest request = HttpRequest.newBuilder(shoppingListUri(value))
            .header("Accept", "application/json").GET().build();
        final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
            HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();

        ObjectMapper objectMapper = new ObjectMapper();

        User user = objectMapper.readValue(responseString, User.class);

        return user;

        } catch (IOException | InterruptedException e) {
        return null;
        }
    }
    

    /**
     * Method for adding a user to the remote database.
     */
    @Override
    public void addUser(User user) throws JsonProcessingException {
        String mapping = "users/add";

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(user);

        try {
          //String json = gson.toJson(user);
          HttpRequest request = HttpRequest.newBuilder(shoppingListUri(mapping))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(BodyPublishers.ofString(json)).build();
    
          HttpResponse<String> response = HttpClient.newBuilder()
              .build().send(request, HttpResponse.BodyHandlers.ofString());
          if (response.statusCode() > 399) {
            throw new IOException("Not legal status code"); 
          }
        } catch (IOException | InterruptedException e) {
          System.out.println("halla");
          throw new RuntimeException(e);
        }
      }
    
      /**
       * Method for updating a user in the remote database.
       */
    @Override
    public void addShopeeList(String username, ShopeeList newShopeeList) throws JsonProcessingException  {
        String mapping = "users/" + username + "/addList";

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(newShopeeList);

        try {
          //String json = gson.toJson(user);
          HttpRequest request = HttpRequest.newBuilder(shoppingListUri(mapping))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(BodyPublishers.ofString(json)).build();
    
          HttpResponse<String> response = HttpClient.newBuilder()
              .build().send(request, HttpResponse.BodyHandlers.ofString());
          if (response.statusCode() > 399) {
            throw new IOException("Not legal status code"); 
          }
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
    }

    /**
     * Method for updating a user in the remote database.
     */
    @Override
    public void deleteShopeeList(String usernname, String listName) {
      String mapping = "users/" + usernname + "/" + listName;
      try {
        HttpRequest request = HttpRequest
            .newBuilder(shoppingListUri(mapping))
            .DELETE()
            .build();
        HttpResponse<String> response = HttpClient.newBuilder().build()
              .send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() > 399) {
          throw new IOException("Not legal status code");
        }
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }


}
    



