package core;

import java.util.HashMap;
import java.util.List;

public class ShopeeList {
    
    private String ListName ="";
    private HashMap<String,Integer> Shop_list;

    public ShopeeList(){
        
    }

    /**
     * Constructor a shoppinglist with a name and a hashmap containing the different foods
     * 
     * @param name
     */
    
    public ShopeeList(String name) {
        SetListName(name);
        this.Shop_list = new HashMap<>();
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
     * @param food
     * @param amount
     */

    public void AddFood(String food, int amount) {
        if(Shop_list.keySet().contains(food)) {
            int current_amount = Shop_list.get(food);
            int newAmount = current_amount + amount;

            this.Shop_list.put(food, newAmount);
        }
        else {
            this.Shop_list.put(food, amount);
        }
    }

     /**
     * Removes a food that the user wants from the List
     * 
     * @param food
     */

    public void RemoveFood(String food) {
        if(!Shop_list.keySet().contains(food)) {
            throw new IllegalArgumentException("This food is not in the food list!");
        }
        this.Shop_list.remove(food);
    }


    /**
     * Gets the amount of a certain food
     * 
     * @param food
     */

    public int getFoodAmount(String food) {
        if(!Shop_list.keySet().contains(food)) {
            throw new IllegalArgumentException("This food is not in the food list!");
        }
        return Shop_list.get(food);
    }

    /**
     * Gets a list containing all the foodnames in the list
     * 
     * @return the names of all the foods in the list
     */

    public List<String> getFoods() {
        return Shop_list.keySet().stream().toList();
    }

    

    public static void main(String[] args) {
        ShopeeList hall = new ShopeeList("Oskar");
        hall.AddFood("kiwi", 5);
        hall.AddFood("kiwi", 5);
        hall.AddFood("tomat", 3);
        System.out.println(hall);
        System.out.println(hall.getFoods());
        hall.RemoveFood("kiwi");
        System.out.println(hall);
        System.out.println(hall.getFoods());
    }
   
}