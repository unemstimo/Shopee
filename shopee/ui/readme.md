# **UI Module**
This folder contains the ui module of the shopee application. The ui module manages the user experience through the fxml-files while ensuring seamless integration among the domain logic layer, persistence layer, and UI layer through the controller classes. The user interface is made using JavaFX- and fxml- technologies and shows the shopee list to a user, i.e. the users shopping list and bought list. In the ui, the user can move food items from the shopping list to the bought list and add new food items to the shopping list.  

## **User interface**
This folder contains the controller classes as well as the class that makes the application run. The controller classes connects the domain logic in the core module with the user input from the fxml-files found under resources. The user interface for this application is easy to use with basic colors and intuitive functionality. 

## **Data access**
In this folder it is included a folder containing the data access functionality. The classes promote consistency handling tasks in our application´s RESTful API implementation.

## **Classes:**
- [dataAccess](shopee/ui/src/main/java/shopee/ui/dataAccess)
    - [LocalUserAccess.java](shopee/ui/src/main/java/shopee/ui/dataAccess/LocalUserAccess.java) : access class for using the application locally
    - [RemoteUserAccess.java](shopee/ui/src/main/java/shopee/ui/dataAccess/RemoteUserAccess.java) : access class for using the application remote
    - [UserAccess.java](shopee/ui/src/main/java/shopee/ui/dataAccess/UserAccess.java) : interface for facilitationg both local and remote access operations 
- [ui](shopee/ui/src/main/java/shopee/ui)
    - [AbstractController.java](shopee/ui/src/main/java/shopee/ui/AbstractController.java) : abstract class for use in the other controllers
    - [HomePageController.java](shopee/ui/src/main/java/shopee/ui/HomePageController.java) : controller class hanling home page for an user -containing a list of multiple lists, second page 
    - [LogInController.java](shopee/ui/src/main/java/shopee/ui/LogInController.java) : controller class for handling log-in page, the first page user meets
    - [ShopeeApp.java](shopee/ui/src/main/java/shopee/ui/ShopeeApp.java) : entry point for the application
    - [ShopeeController.java](shopee/ui/src/main/java/shopee/ui/ShopeeController.java) : controller class for handling selected shopping list, connected to the third(and last) frame/page


## **Resources**
This folder contains the fxml-files which comprises the user interface appearing at the users screen.
- [resources](shopee/ui/src/main/resources)
    - [LogIn.fxml](shopee/ui/src/main/resources/shopee/ui/LogIn.fxml) : log-in page
    - [Home.fxml](shopee/ui/src/main/resources/shopee/ui/Home.fxml) : home page
    - [Shopee.fxml](shopee/ui/src/main/resources/shopee/ui/Shopee.fxml) : selected shopping list page

## **Testing**
- [HomePageTest.java](shopee/ui/src/test/java/shopee/ui/HomePageTest.java)
- [LogInTest.java](shopee/ui/src/test/java/shopee/ui/LogInTest.java)
- [ShopeeTest.java](shopee/ui/src/test/java/shopee/ui/ShopeeTest.java)


## **View:**
- [frame1.png](shopee/ui/frame1.png)
- [frame2.png](shopee/ui/fram2.png)
- [frame3.png](shopee/ui/frame3.png)