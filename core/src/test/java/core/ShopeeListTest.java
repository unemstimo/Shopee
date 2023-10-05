package core;

import java.util.ArrayList;
import java.util.List;
import core.ShopeeList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopeeListTest {



    @Test
    public void testShopeeList(){

    }

    @Test
    public void testAddFood(){
        ShopeeList list1 = new ShopeeList("Hanna");
        list1.addFoodShopList("apple", 4);
        Assertions.assertEquals("apple", list1.getFood(0).getFoodName());
        Assertions.assertEquals(4, list1.getFood(0).getFoodAmount());

    }
}
