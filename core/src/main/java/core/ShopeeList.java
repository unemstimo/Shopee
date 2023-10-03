package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopeeList {
    
    private String listName =""; // will be used later in the project!
    private List<FoodItem> shopList;
    private List<FoodItem> boughtList;

    /**
     * Construtor inizializing the two lists of the ShopeeList object
     */
    public ShopeeList(){ //Må endre denne til å måtte ha et navn, Kan ikke lage en handleliste uten navn
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
        if(shopList.stream().map(a -> a.getFoodName()).collect(Collectors.toList()).contains(foodname)) {
            FoodItem food = shopList.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
            food.setFoodAmount(amount);
        }
        else {
            FoodItem foodItem = new FoodItem(foodname, amount);
            this.shopList.add(foodItem);
        }
        
    }

    public void setShopList(List<FoodItem> list) {
        this.shopList = list;
    }

    public void setBoughtList(List<FoodItem> list) {
        this.boughtList = list;
    }


    /**
     * Adds a food that the user wants to mark as bought in the bought list,
     * and removes the same food from the shopping list
     * 
     * @param foodItem
     */
    public void addFoodBoughtList(FoodItem foodItem) { 
        hasFood(foodItem.getFoodName());
        this.boughtList.add(foodItem);
        removeFood(foodItem.getFoodName());
        
    }




     /**
     * Removes a food that the user wants from the List
     * 
     * @param foodname
     */
    public void removeFood(String foodname) {
        hasFood(foodname);
        this.shopList.remove(this.getFood(foodname));
        
        
    }

    /**
     * Gets a food from the list, null if the food is not in the list
     * 
     * @param foodname
     * @return Fooditem object or null
     */
    public FoodItem getFood(String foodname) {
        hasFood(foodname);
        return shopList.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
    }

    /**
     * Gets a food from a specific index the list, null if the food is not in the list
     * 
     * @param index
     * @return Fooditem object or null
     */
    public FoodItem getFood(int index) {
        return shopList.get(index);
    }

    /**
     * Gets the amount of a given food
     * 
     * @param food
     * @return the amount of the food
     */
    public int getFoodAmount(String food) {
        hasFood(food);
        return shopList.stream().filter(a -> a.getFoodName().equals(food)).findFirst().orElse(null).getFoodAmount();
    }


    /**
     * Checks if the food is in the shopee list, if not: throw exception
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
     * Gets the bought item from a certain index
     * 
     * @return foodItem bought
     */
    public FoodItem getBoughtItem(int index) {
        return boughtList.get(index);
    }

    /**
     * Returns the shopping list
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
    
    

    @Override
    public String toString() {
        return "listName=" + listName + "\n shopList=" + shopList + "\n boughtList=" + boughtList + "]\n";
    }


    public static void main(String[] args) {
        ShopeeList hall = new ShopeeList();
        hall.setListName("jajajajjjjaaj");
        System.out.println(hall.getListName());
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