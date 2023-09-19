package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import core.Storage.WriteToFile;

public class ShopeeList {
    
    private String listName ="";
    private List<FoodItem> shop_list;
    private List<FoodItem> bought_list;
    private WriteToFile fileWriter = new WriteToFile();

    /**
     * Empty construtor for use in controller. 
     */
    public ShopeeList(){
        this.shop_list = new ArrayList<>();
        this.bought_list = new ArrayList<>();
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
     * Gets the name of the list
     * 
     * @return ListName
     */
    public String getListName() {
        return this.listName;
    }

    /**
     * Checks if the input name is valid
     * 
     * @param listName
     */
    public void ValidName(String listName) {
        if (!listName.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Shoplist name is not valid"); // usikker på om dette er nødvendig??
        }
    }

    /**
     * Adds a food that the user wants in the shopping list
     * 
     * @param foodname
     * @param amount
     */
    public void addFoodShopList(String foodname, int amount) { 
        if(shop_list.stream().map(a -> a.getFoodName()).collect(Collectors.toList()).contains(foodname)) {
            FoodItem food = shop_list.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
            food.setAmount(amount);
        }
        else {
            FoodItem foodItem = new FoodItem(foodname, amount);
            this.shop_list.add(foodItem);
        }
        this.fileWriter.textWriter(this);
    }

    /**
     * Adds a food that the user wants to mark as bought in the bought list,
     * and removes the same food from the shopping list
     * 
     * @param foodItem
     */
    public void addFoodBoughtList(FoodItem foodItem) { 
        hasFood(foodItem.getFoodName());
        this.bought_list.add(foodItem);
        removeFood(foodItem.getFoodName());
        
        this.fileWriter.textWriter(this);
    }

     /**
     * Removes a food that the user wants from the List
     * 
     * @param foodname
     */
    public void removeFood(String foodname) {
        hasFood(foodname);
        this.shop_list.remove(this.getFood(foodname));
        
        //Oppdaterer filen
        this.fileWriter.textWriter(this);
    }

    /**
     * Gets a food from the list, null if the food is not in the list
     * 
     * @param foodname
     * @return Fooditem object or null
     */
    public FoodItem getFood(String foodname) {
        hasFood(foodname);
        return shop_list.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
    }

    /**
     * Gets a food from a specific index the list, null if the food is not in the list
     * 
     * @param index
     * @return Fooditem object or null
     */
    public FoodItem getFood(int index) {
        return shop_list.get(index);
    }

    /**
     * Gets the amount of a given food
     * 
     * @param food
     * @return the amount of the food
     */
    public int getFoodAmount(String food) {
        hasFood(food);
        return shop_list.stream().filter(a -> a.getFoodName().equals(food)).findFirst().orElse(null).getFoodAmount();
    }


    /**
     * Checks if the food is in the shopee list, if not: throw exception
     * 
     * @param foodname
     * @throws IllegalArgumentException if the food is not in the list
     */
    public void hasFood(String foodname) {
        List<String> foodnames = this.shop_list.stream().map(a -> a.getFoodName()).collect(Collectors.toList());

        if(!foodnames.contains(foodname)) {
            throw new IllegalArgumentException("There is no such food in the list");
        }
    }

    /**
     * Gets the bought item from a certain index
     * 
     * @return foodItem bought
     */
    public FoodItem getBoughtItem(int index) {
        return bought_list.get(index);
    }

    /**
     * Returns the shopping list
     * 
     * @return shop_list
     */
    public List<FoodItem> getShopList() {
        return this.shop_list;
    }

    /**
     * Returns the list containing bought food items
     * 
     * @return bought_list
     */
    public List<FoodItem> getBoughtList() {
        return this.bought_list;
    }
    

    public static void main(String[] args) {
        ShopeeList hall = new ShopeeList();
        hall.addFoodShopList("kiwi", 5);
        hall.addFoodShopList("kiwi", 5);
        hall.addFoodShopList("tomat", 3);
        System.out.println(hall);
        System.out.println(hall.getFood("kiwi"));
        System.out.println(hall.getFoodAmount("kiwi"));
        hall.addFoodBoughtList(hall.getFood("kiwi"));
        //hall.removeFood("kiwi");
        System.out.println("halla");
        System.out.println(hall.getFood("tomat"));
        hall.addFoodShopList("hanna", 4);
        System.out.println(hall);
        // System.out.println(hall.getFoodAmount("kiwi"));
    }
   
}