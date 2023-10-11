# **Core Module**
This folder contains the core module of the shopee application. Core module handles domain logic and data persistence.

## **Domain logic**
This folder contains all classes and logic related to the data that the application deals with. The domain logic layer is independent of the user interface and the persistence layer. 
Since our application concerns shopping lists, the classes in this layer are made to represent and handle this as follows: 

**Classes:**
- __FoodItem.java__ which handles the food item objects that the shopee lists contains of.
- __ShopeeList.java__ which gives us the methods needed to work with, and create, shopee lists.
- __User.java__ which makes it possible to implement log-in functionality.


## **Persistence**
This folder contains the classes needed to store all data in the application. This involves both reading from and writing to file using JSON.

**Classes**
- __DataStorage.json__ which is the file where all the data is being stored in this release.
- __FileHandler.java__ the class where all the methods needed to actually read and write from the file above is located.



