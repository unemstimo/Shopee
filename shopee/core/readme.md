# **Core Module**
This folder contains the core module of the shopee application. Core module handles domain logic and data persistence.

## **Domain logic**
This folder contains all classes and logic related to the data that the application deals with. The domain logic layer is independent of the user interface and the persistence layer. 

## **Persistence**
This folder contains the classes needed to store all data in the application. This involves both reading from and writing to file using JSON.

## **Classes:**
- [core](shopee/core/src/main/java/shopee/core)
    - [FoodItem.java](shopee/core/src/main/java/shopee/core/FoodItem.java) : handles food item objects
    - [ShopeeList.java](shopee/core/src/main/java/shopee/core/ShopeeList.java) : Java class for shopping list objects
    - [User.java](shopee/core/src/main/java/shopee/core/User.java) : Java class for user object
- [json](shopee/core/src/main/java/shopee/json)
    - [FileHandeler.java](shopee/core/src/main/java/shopee/json/FileHandeler.java) : handles persitance regarding the application


## **Testing:**
-[core](shopee/core/src/test/java/shopee/core)
    - [FoodItemTest.java](shopee/core/src/test/java/shopee/core/FoodItemTest.java)
    - [ShopeeListTest.java](shopee/core/src/test/java/shopee/core/ShopeeListTest.java)
    - [UserTest.java](shopee/core/src/test/java/shopee/core/UserTest.java)
- [jsoon](shopee/core/src/test/java/shopee/json)
    - [FileHandlerTest.java](shopee/core/src/test/java/shopee/json/FileHandlerTest.java)




