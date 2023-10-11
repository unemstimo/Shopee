# Group gr2334 repository

[<img src="https://eclipse.dev/che/docs/_/img/icon-eclipse-che.svg" width="25" /> open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2334/gr2334)


## Description
This group project is a developing project through the course IT1901. The purpose of this project is to create an app that can handle data managed as a cloud service. This project is intended to familiarize the group with working collaboratively and using GitLab to become well-versed in agile practices.
______________

## system requirements
- Java 17.0.8
- Maven 4.0.0
- javaFX

## Project Build

This project is built and runs on Maven.

Before running:
- mvn clean install
- mvn compile

To run the app you can either:
1. use command "mvn javafx:run" in ui folder
2. use command "mvn javafx:run -f ui/pom.xml" wherever you would like 

_______________________
## Project Structure
The project is organized with these directories for managing source code, documentation and resources.
**shopee/core**
- **/src/main/java/shopee/core** : The core module for the app, contains core Java classes for the app.
    - __FoodItem.java:__  Java class for food object
    - __ShopeeList.java:__ Java class for shopping list
    - **User.java:** Java class for user
- **/src/main/java/shopee/json** Folder contains persistance for code project.
    - **DataStorage.json** 
    - **FileHandeler.java** Handles the json file methods, read/write to file
- **/src/main/java/module-info.java** contains module info for the module in core   
- **/src/test/java/shopee/core** Contains test classes for the java classes that holds the logic.
    - **FoodItemTest.java**
    - **ShopeeListTest.java**
    - **UserTest.java**
- **/src/test/java/shopee/json** Holds testing for file handler class
    - **FileHandlerTest.java**
- **/pom.xml** This pom file contains the configuration for maven, javafx, jacoco etc. Contains build setting and other configuration.


- **docs**:  This folder contains the documentation for app. Releases is placed in this folder.
    - **release1.md** : Documentation of what has been accomplished for the first sprint in this project.
    - **release2.md** : Documentation on what has been accomplished since first release, reflection on this sprint and how the group has cooporated.



**shopee/ui**  
- **/src/main/java/shopee/ui**  This contains the main java classes for ui.
    - **LogInController.java**  Controller class for handling log in page.
    - **Shopee.java**  This is the main application class for the UI. The shopee class launches the JavaFX app, sets up the first page of the app. 
    - **ShopeeController.java**  The JavaFX controller class for handeling UI. 
- **/src/main/java/module-info.java**  Module info for ui folder. 
- **/src/main/resources/ui**  Contains the fxml files used for the app.
    - **LogIn.fxml** Fxml for the first page the user sees, contains log in ui
    - **Shopee.fxml**  This is the fxml file defying the user interface layout.
- **/src/test/java/shopee/ui** Contains test classes for controller and fxml
    - **LogInTest.java**
    - **ShopeeTest.java**
- **pom.xml**  This pom file contains the configuration for maven, javafx, jacoco etc. Contains build setting and other configuration.
- **readme.md**  Description on ui module.

**shopee/readme.md** Description of the app project, illustration of the app, user story and important functuality to the app.

## Package diagram explaining folder structure
[Package diagram](ShopeePackage.png)



## Configuration of tools for code quality
* __jaCoCo:__ Gathers and presents information and testcode coverage
* __checkstyle:__ Checks code quality
*__spotBugs:__ Finds bugs in code



