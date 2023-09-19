package core;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopeeListTest {

    

    @Test
    public void testShopeeList(){

    }

    @Test
    public void testAddFood(){
        ShopeeList list1 = new ShopeeList();
        list1.addFoodShopList("apple", 4);
        Assertions.assertEquals("apple", list1.getFood(0));
    }
}
