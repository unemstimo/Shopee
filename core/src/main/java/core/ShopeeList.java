package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopeeList {
    
    private String ListName ="";
    private List<FoodItem> Shop_list;

    /**
     * Empty construtor for use in controller. 
     */
    public ShopeeList(){
        this.Shop_list = new ArrayList<>();
    }

    
    /** 
      * Sets the name of the Shopee List
      *
      * @param ListName
      */

    public void SetListName(String ListName){
        ValidName(ListName);
        this.ListName = ListName;
    }

     /**
     * Gets the name of the list
     * 
     * @return ListName
     */

    public String getListName() {
        return this.ListName;
    }

    /**
     * Checks if the input name is valid
     * 
     * @param Listname
     */
    public void ValidName(String listName) {
        if (!listName.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Shoplist name is not valid"); // usikker på om dette er nødvendig??
        }
    }

    /**
     * Adds a food that the user wants in the List
     * 
     * @param foodname
     * @param amount
     */

    public void AddFood(String foodname, int amount) { 
        if(hasFood(foodname)) {
            FoodItem food = Shop_list.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
            food.setAmount(amount);
        }
        else {
            FoodItem foodItem = new FoodItem(foodname, amount);
            this.Shop_list.add(foodItem);
        }
    }

     /**
     * Removes a food that the user wants from the List
     * 
     * @param foodname
     * @throws IllegalArgumentException if the food is not in the list
     */

    public void RemoveFood(String food) {
        if(!hasFood(food)) {
            throw new IllegalArgumentException("This food is not in the food list!");
        }
        this.Shop_list.remove(this.getFood(food));
    }

    /**
     * Gets a food from the list, null if the food is not in the list
     * 
     * @param foodname
     * @return Fooditem object or null
     */

    public FoodItem getFood(String foodname) {
        if(!hasFood(foodname)) {
            throw new IllegalArgumentException("This food is not in the shopee list!");
        }
       return Shop_list.stream().filter(a -> a.getFoodName().equals(foodname)).findFirst().orElse(null);
    }

    public FoodItem getFood(int index) {
        return Shop_list.get(index);
    }

      /**
       * Gets the amount of a given food
       * 
       * @param food
       * @return the amount of the food
       */

    public int getFoodAmount(String food) {
        if(!hasFood(food)) {
            throw new IllegalArgumentException("Cannot remove this food because the list does not contain it!");
        }
        return Shop_list.stream().filter(a -> a.getFoodName().equals(food)).findFirst().orElse(null).getFoodAmount();
    }


    /**
     * Checks if the food is in the shopee list
     * 
     * @param foodname
     * @return
     */
    public boolean hasFood(String foodname) {
        List<String> foodnames = this.Shop_list.stream().map(a -> a.getFoodName()).collect(Collectors.toList());

        if(!foodnames.contains(foodname)) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Method to get all elements in the shopping list
     * @return all objects in the shopping list
     */
    public List<FoodItem> getShoppingList(){
        return Shop_list;
    }
    

    

    public static void main(String[] args) {
        ShopeeList hall = new ShopeeList();
        hall.AddFood("kiwi", 5);
        hall.AddFood("kiwi", 5);
        hall.AddFood("tomat", 3);
        System.out.println(hall);
        System.out.println(hall.getFood("kiwi"));
        System.out.println(hall.getFoodAmount("kiwi"));
        hall.RemoveFood("kiwi");
        System.out.println("halla");
        System.out.println(hall.getFood("kiwi"));
        System.out.println(hall.getFoodAmount("kiwi"));

        
    }
   
}