# Group gr2334 repository

[<img src="https://eclipse.dev/che/docs/_/img/icon-eclipse-che.svg" width="25" /> open in Eclipse Che](https://che.stud.ntnu.no/unems-stud-ntnu-no/shopee/3100/)


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

- __core:__  The core module for the app.
    - __src/ main/ java/ core__ :  This directory contains core Java classes for the app
        - __FoodItem.java:__  Java class for food object
        - __ShopeeList.java:__ Java class for shopping list
    - __src/ main/ java/ core/ Storage:__ This directory contains file handling classes and files
        - __boughtList.txt:__ This file holds the food items the user have marked as bought in the app
        - __shopeList.txt:__ This file holds the food items the user have added to the shopping list
        - __ReadFromFile.java:__ Java class containing file handling for reading from file
        - __WriteToFile.java:__ Java class containg file handling for writing to file
    - __src/main/java/module-info:__ contains module info for the module in core
    - __src/test/java/core:__ This contains all test classes for the Java classes in core
    - __pom.xml:__ This pom file contains the configuration for maven, javafx, jacoco etc. Contains build setting and other configuration.



- **docs**:  This folder contains the documentation for app. Releases is placed in this folder.
    - **release1** : Documentation of what has been accomplished for the first sprint in this project.




- **ui**  
    - **/src/main/java/ui**  This contains the main java classes for ui.
        - **Shopee.java**  This is the main application class for the UI. The shopee class launches the JavaFX app, sets up the first page of the app. 
        - **ShopeeController.java**  The JavaFX controller class for handeling UI. 
     **/src/main/java/module-info.java**  Module info for ui folder. 
    - **/src/main/resources/ui**  Contains the fxml files used for the app.
        - **Shopee.fxml**  This is the fxml file defying the user interface layout.
    - **pom.xml**  This pom file contains the configuration for maven, javafx, jacoco etc. Contains build setting and other configuration.
    - **readme.md**  Description of the app project, illustration of the app, user story and important functuality to the app.

- **Sequence Diagram**
A sequence diagram to show the functionality of the code. In a lesson George said that we needed a sequence diagram, don't know if it is for release 2. But we made it anyways, or we tried to. Please give feedback if it wrong and what not (I know it contains a class we havent created yet, but we included it to give insight to the finished project). 
![ShopeeApp](https://www.plantuml.com/plantuml/svg/RL91RiCW4BppYlr0v7iELTocbIAblLXHpyXC7LGC5DQD_FkkxJgE9NC0j3Cxmm27p3fnrpgghY42POmgVriVTT8jLTmxKEPawu7pYE2dWw_nZfnrWzBPnhVmJBLCI4g6hZaakcGaKkEyUw9pQAod_LGz-Q-Wfef0bR15IS3A-aR0uXBLNDoj2VeNz87RABFYpFel2PfX9i9YCKOysynvFr35PEbUmRJW3yN1G3Pt_UBLM2wEDaUd-n5I8xxmIcEA8rpntFNq5e8vCLgwapOIhKJM1vfz_4np8KI2DmbAEwPFBTVFWUghzYBQXWHoGwyY6Tiv8058mMNGxR_TIUxGyMRJS95A74cUzo2h_8a_ "ShopeeApp")


## Configuration of tools for code quality
* __jaCoCo:__ Gathers and presents information and testcode coverage



