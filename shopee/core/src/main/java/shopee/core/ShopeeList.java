package shopee.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShopeeList class consists of a boughtList and a shopList which each contains FoodItems.
 */
public class ShopeeList {

    private String listName = ""; 
    private List<FoodItem> shopList;
    private List<FoodItem> boughtList;

    /**
     * Constructor to set the name of the ShopeeList object
     *  and initialize the two lists of the object.
     *
     * @param listName the name of the ShopeeList
     */
    public ShopeeList(@JsonProperty("listName") String listName) {
        setListName(listName);
        this.shopList = new ArrayList<>();
        this.boughtList = new ArrayList<>();
    }

    /**
     * Sets the name of the Shopee List.
     *
     * @param listName the name to set
     */
    public void setListName(final String listName) {
        validateName(listName);
        this.listName = listName;
    }

    /**
     * Returns the name of the list.
     *
     * @return listName
     */
    public String getListName() {
        return this.listName;
    }

    /**
     * Helper method to check if the input name is valid.
     *
     * @param listName the name to validate
     * @throws IllegalArgumentException if the name is not valid
     */
    private void validateName(final String listName) {
        if (!listName.matches("[A-Za-z0-9 ]+")) {
            throw new IllegalArgumentException("Shoplist name is not valid");
        }
    }

    /**
     * Adds a food that the user wants in the shop list. If the food is already in the shop list,
     * only the amount will be updated to the new desired amount.
     *
     * @param foodName the name of the food
     * @param amount   the amount of the food
     */
    public void addFoodShopList(final String foodName, final int amount) {
        if (shopList.stream().anyMatch(a -> a.getFoodName().equals(foodName))) {
            FoodItem food = shopList.stream().filter(a -> a.getFoodName()
            .equals(foodName)).findFirst().orElse(null);
            if (food != null) {
                food.setFoodAmount(amount);
            }
        } else {
            FoodItem foodItem = new FoodItem(foodName, amount);
            this.shopList.add(foodItem);
        }
    }

    /**
     * Sets the shop list to an already existing list.
     *
     * @param list the list to set
     */
    public void setShopList(final List<FoodItem> list) {
        this.shopList = list;
    }

    /**
     * Sets the bought list to an already existing list.
     *
     * @param list the list to set
     */
    public void setBoughtList(final List<FoodItem> list) {
        this.boughtList = list;
    }

    /**
     * Adds a food that the user wants to mark as bought in the 
     * bought list if the food exists in the shop list.
     * Removes the same food from the shopping list.
     *
     * @param foodItem the food item to add to the bought list
     */
    public void addFoodBoughtList(final FoodItem foodItem) {
        hasFood(foodItem.getFoodName());
        this.boughtList.add(foodItem);
        removeFood(foodItem.getFoodName());
    }

    /**
     * Removes a food that the user wants from the shop list. The food must exist in the shop list.
     *
     * @param foodName the name of the food to remove
     */
    public void removeFood(final String foodName) {
        hasFood(foodName);
        this.shopList.remove(getFood(foodName));
    }

    /**
     * Returns a food from the list, null if the food is not in the list.
     *
     * @param foodName the name of the food to retrieve
     * @return FoodItem object or null
     */
    public FoodItem getFood(final String foodName) {
        hasFood(foodName);
        return shopList.stream().filter(a -> a.getFoodName()
        .equals(foodName)).findFirst().orElse(null);
    }

    /**
     * Returns a food from a specific index the list, null if the food is not in the list.
     *
     * @param index the index of the food to retrieve
     * @return FoodItem object or null
     */
    public FoodItem getFood(final int index) {
        return shopList.get(index);
    }

    /**
     * Returns the amount of a given food if the food exists.
     *
     * @param foodName the name of the food to retrieve
     * @return the amount of the food
     */
    public int getFoodAmount(final String foodName) {
        hasFood(foodName);
        return shopList.stream().filter(a -> a.getFoodName()
        .equals(foodName)).findFirst().orElse(null)
        .getFoodAmount();
    }

    /**
     * Helper method to check if the food is in the Shopee list. If not: throw an exception.
     *
     * @param foodName the name of the food to check
     * @throws IllegalArgumentException if the food is not in the list
     */
    public void hasFood(final String foodName) {
        List<String> foodNames = this.shopList.stream().map(FoodItem::getFoodName)
        .collect(Collectors.toList());

        if (!foodNames.contains(foodName)) {
            throw new IllegalArgumentException("There is no such food in the list");
        }
    }

    /**
     * Gets the bought item from a certain index in the bought list.
     *
     * @param index the index of the bought item
     * @return the bought item
     */
    public FoodItem getBoughtItem(final int index) {
        return boughtList.get(index);
    }

    /**
     * Returns the shop list.
     *
     * @return shop_list
     */
    public List<FoodItem> getShopList() {
        return this.shopList;
    }

    /**
     * Returns the bought list.
     *
     * @return bought_list
     */
    public List<FoodItem> getBoughtList() {
        return this.boughtList;
    }

    /**
     * To-String to properly show the ShopeeList object on the screen.
     *
     * @return string representation of the ShopeeList
     */
    @Override
    public String toString() {
        return listName;
    }
}