# **UI Module**
This folder contains the ui module of the shopee application. The ui module manages the user experience through the fxml-files while ensuring seamless integration among the domain logic layer, persistence layer, and UI layer through the controller classes. The user interface is made using JavaFX- and fxml- technologies and shows the shopee list to a user, i.e. the users shopping list and bought list. In the ui, the user can move food items from the shopping list to the bought list and add new food items to the shopping list.  

## **User interface**
This folder contains the controller classes as well as the class that makes the application run. The controller classes connects the domain logic in the core module with the user input from the fxml-files found under resources. 

**Classes:**
- __LogInController.java__ which is connected to the first page the user meets: the log-in page. Also makes sure that the program moves on to the next page when its time. 
- __ShopeeController.java__ is connected to the second page the user meets: the shopee list page. 
- __Shopee.java__ is the class that extends Application and launches the Shopee application

## **Resources**
This folder contains the fxml-files which comprises the user interface appearing at the users screen.

**Files**
- __LogIn.fxml__ the first page the user meets. Here the user can either log in or create a new user.
- __Shopee.fxml__ the second page the user meets. Here the user can interact with and view its shopee list. 