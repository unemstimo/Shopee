package shopee.ui.dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.NoSuchElementException;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;

public class RemoteUserAccess implements UserAccess{

    private final URI uri;
    private final ObjectMapper objectMapper;
    private User user;
    private ShopeeList shopeeList;

    public RemoteUserAccess(URI uri) {
        this.uri = uri;
        this.objectMapper = FileHandeler.createObjectMapper();
    }

    @Override
    public User loadUser(String username, String password) {
        try {
            user.setUsername(username);
            user.setPassword(password);
            return user;
        } catch (IllegalStateException | NoSuchElementException err) {
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
    }

    @Override
    public boolean addShopeeList(ShopeeList shopeeList) {
        try {
            String jsonShopeeList = objectMapper.writeValueAsString(shopeeList);
            final HttpRequest request = HttpRequest
                    .newBuilder(URI.create(uri + "/" + shopeeList.getListName().replaceAll(" ", "-")))
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(jsonShopeeList)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            Boolean success = objectMapper.readValue(response.body(), Boolean.class);
            if (success != null && success) {
                user.addShopeeList(shopeeList);
                return true;
            }
            return false;
        } catch (IOException | InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public boolean deleteShopeeList(String listName) {
        try {
            final HttpRequest request = HttpRequest.newBuilder(URI.create(uri + "/" + listName.replaceAll(" ", "-")))
                    .header("Accept", "application/json").DELETE().build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            Boolean success = objectMapper.readValue(response.body(), Boolean.class);
            if (success != null && success) {
                user.deleteShopeeList(listName);
                return true;
            }
            return false;
        } catch (IOException | InterruptedException err) {
            throw new RuntimeException(err);
        }
    }
    
    @Override
    public boolean addFoodItem(String listName, FoodItem foodItem) {
        try{
            String jsonNewFood = objectMapper.writeValueAsString(foodItem);
            final HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri + "/" + listName.replaceAll(" ", "-")))
                .header("Accept", "application/json").header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonNewFood)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            Boolean success = objectMapper.readValue(response.body(), Boolean.class);
            if (success != null && success) {
                shopeeList = user.getShopeeList(listName);
                shopeeList.addFoodShopList(foodItem.getFoodName(), foodItem.getFoodAmount());
                return true;
            }
            return false;
        } catch (IOException | InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public boolean removeFoodItem(String listName, FoodItem foodItem) {
        try{
            String jsonFoodItem = objectMapper.writeValueAsString(foodItem);
            final HttpRequest request = HttpRequest.newBuilder(URI.create(uri + "/" + listName.replaceAll(" ", "-")))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .method("DELETE", BodyPublishers.ofString(jsonFoodItem)) 
                .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            Boolean success = objectMapper.readValue(response.body(), Boolean.class);
            if (success != null && success) {
                shopeeList = user.getShopeeList(listName);
                shopeeList.removeFood(foodItem.getFoodName());
                return true;
            }
            return false;
        } catch (IOException | InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public boolean markAsBought(String listName, FoodItem foodItem) {
        try{
            String jsonBoughtFood = objectMapper.writeValueAsString(foodItem);
            final HttpRequest request = HttpRequest.newBuilder(URI.create(uri + "/" + listName.replaceAll(" ", "-") + "/mark-as-bought"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .method("PATCH", BodyPublishers.ofString(jsonBoughtFood)) 
                .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            Boolean success = objectMapper.readValue(response.body(), Boolean.class);
            if (success != null && success) {
                shopeeList = user.getShopeeList(listName);
                shopeeList.addFoodBoughtList(foodItem);
                return true;
            }
            return false;
        } catch (IOException | InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        if (user == null) {
            try {
                final HttpRequest request = HttpRequest.newBuilder(uri).header("Accept", "application/json").GET()
                        .build();
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                        HttpResponse.BodyHandlers.ofString());
                this.user = objectMapper.readValue(response.body(), User.class);
            } catch (IOException | InterruptedException err) {
                throw new RuntimeException(err);
            }
        }
        return user;
    }
    
}
