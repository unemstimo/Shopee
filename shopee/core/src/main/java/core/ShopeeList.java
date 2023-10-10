package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopeeList {
    
    private String listName = ""; // Will be used later in the project!
    private List<FoodItem> shopList;
    private List<FoodItem> boughtList;

    /**
     * Construtor to set the name of the ShopeeList object 
     * and inizialize the two lists of the object.
     * 
     * @param listName
    */
    public ShopeeList(@JsonProperty("listName") String listName){ //Må endre denne til å måtte ha et navn, Kan ikke lage en handleliste uten navn
        setListName(listName);
        this.shopList = new ArrayList<>();
        this.boughtList = new ArrayList<>();
    }
    
    /** 
      * Sets the name of the Shopee List
      *
      * @param ListName
    */
    public void setListName(String listName){
        ValidName(listName);
        this.listName = listName;
    }

    /**
     * Returns the name of the list
     * 
     * @return listName
     */
    public String getListName() {
        return this.listName;
    }

    /**
     * Helper method to check if the input name is valid
     * 
     * @param listName
     */
    private void ValidName(String listName) {
        if (!listName.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Shoplist name is not valid");
        }
    }

    /**
     * Adds a food that the user wants in the shop list.
     * If the food is already in the shop list, only the amount will be updated to the new desired amount.
     * 
     * @param foodname
     * @param amount
     */
    public void addFoodShopList(String foodname, int amount) { 
        if(shopList.stream().map(a -> a.getFoodName()).collect(Collectors.toList()).contains(foodname)) {
            FoodItem food = shopList.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
            food.setFoodAmount(amount);
        }
        else {
            FoodItem foodItem = new FoodItem(foodname, amount);
            this.shopList.add(foodItem);
        }
    }

    /**
     * Sets the shop list to an already existing list.
     * 
     * @param list
     */
    public void setShopList(List<FoodItem> list) {
        this.shopList = list;
    }

    /**
     * Sets the bought list to an already existing list.
     * 
     * @param list
     */
    public void setBoughtList(List<FoodItem> list) {
        this.boughtList = list;
    }

    /**
     * Adds a food that the user wants to mark as bought, in the bought list, if the food exists in the shop list.
     * Removes the same food from the shopping list.
     * 
     * @param foodItem
    */
    public void addFoodBoughtList(FoodItem foodItem) { 
        hasFood(foodItem.getFoodName());
        this.boughtList.add(foodItem);
        removeFood(foodItem.getFoodName()); 
    }

    /**
     * Removes a food that the user wants from the shop list.
     * The food must exist in the shop list.
     * 
     * @param foodname
    */
    public void removeFood(String foodname) {
        hasFood(foodname);
        this.shopList.remove(this.getFood(foodname));
    }

    /**
     * Returns a food from the list, null if the food is not in the list
     * 
     * @param foodname
     * @return Fooditem object or null
     */
    public FoodItem getFood(String foodname) {
        hasFood(foodname);
        return shopList.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
    }

    /**
     * Returns a food from a specific index the list, null if the food is not in the list
     * 
     * @param index
     * @return Fooditem object or null
     */
    public FoodItem getFood(int index) {
        return shopList.get(index);
    }

    /**
     * Returns the amount of a given food if the food exists.
     * 
     * @param food
     * @return the amount of the food
    */
    public int getFoodAmount(String food) {
        hasFood(food);
        return shopList.stream().filter(a -> a.getFoodName().equals(food)).findFirst().orElse(null).getFoodAmount();
    }


    /**
     * Helper method to check if the food is in the shopee list.
     * If not: throw exception.
     * 
     * @param foodname
     * @throws IllegalArgumentException if the food is not in the list
    */
    public void hasFood(String foodname) {
        List<String> foodnames = this.shopList.stream().map(a -> a.getFoodName()).collect(Collectors.toList());

        if(!foodnames.contains(foodname)) {
            throw new IllegalArgumentException("There is no such food in the list");
        }
    }

    /**
     * Gets the bought item from a certain index in bought list.
     * 
     * @return foodItem bought
    */
    public FoodItem getBoughtItem(int index) {
        return boughtList.get(index);
    }

    /**
     * Returns the shop list
     * 
     * @return shop_list
     */
    public List<FoodItem> getShopList() {
        return this.shopList;
    }

    /**
     * Returns the list containing bought food items
     * 
     * @return bought_list
     */
    public List<FoodItem> getBoughtList() {
        return this.boughtList;
    }

    /**
     * To-String to properly show the ShopeeList object to screen.
     * 
     */
    @Override
    public String toString() {
        return "listName=" + listName + "\n shopList=" + shopList + "\n boughtList=" + boughtList + "]\n";
    }
   
}