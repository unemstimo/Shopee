# IT1901 Group gr2334 repository

[<img src="https://eclipse.dev/che/docs/_/img/icon-eclipse-che.svg" width="25" /> open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2334/gr2334)


## Description
This group project is a developing project through the course IT1901. The purpose of this project is to create an app that can handle data managed as a cloud service. This project is intended to familiarize the group with working collaboratively and using GitLab to become well-versed in agile practices. Read more about the project [here](shopee/readme.md)
______________

## Tools used to ensure code quality
* __Checkstyle__ : tool that makes Java code checking faster and smoother that follows coding standards
* __Spotbugs__ : analyses bugs in Java code
* __JaCoCo__ : checks code coverage and generates coverage reports  

## system requirements
- Java 17.0.8
- Maven 4.0.0
- javaFX

## Project building and running

This project is built and runs on Maven.

Before running:
- cd shopee
- mvn clean install
- mvn compile

### Running the application with local access:

1. Navigate to the shopee directory using __"cd shopee/ui"__

2. __mvn clean install__, (tests can be skipped with __mvn clean install -DskipTests__ )

3. use command __"mvn javafx:run"__ in ui folder or use command "mvn javafx:run -f ui/pom.xml" wherever you would like

### Running the application with remote access:
1. Navigate to shopee directory using __"cd shopee"__

2. __mvn clean install__, (tests can be skipped by using __mvn clean install -DskipTests)__

3. Navigate to rest direcory using __"cd rest"__ 

4. Open new terminal window and use __"mvn spring-boot:run"__

5. Open another terminal window, navigate to ui folder and use command __"mvn javafx:run"__



### Testing:

1. Navigate to the shopee directory using __"cd shopee"__
2. Test using __"mvn verify"__

The JaCoCo report is created when this command is runned and is located in /target/site/index.html in each module. 

__Note:__ The springboot- and integrationtest-module runs on port 8080, this requires that the server is not running at the same time. Make sure the server is not running in any terminal when running `mvn verify` or `mvn clean install`.  
_______________________

## How the application work


________
## Project Structure
The project is organized with these directories for managing source code, documentation and resources.

* [shopee](shopee/core/src/main/java/shopee)
    * [core](shopee/core/)
        * [domain-logic](shopee/core/src/main/java/shopee/core) 
        * [persistance](shopee/core/src/main/java/shopee/json)
    * [ui](shopee/ui)
        * [javafx](shopee/ui/src/main/java/shopee/ui)
        * [FXML](shopee/ui/src/main/resources/)
    * [rest](shopee/rest)



## Package diagram explaining folder structure
[Package diagram](ShopeePackage.png)

______
## The plan for this project
The project har three releases, where in each sprint the following plans where intended.

**Sprint 1**

In this sprint the group should have the app running with maven and with simple persitance. The springt contains one user story; be able to create a grocarey list.

US-1: As a user I want to add/remove/mark as bought food items in my grocery list.

Read more about sprint 1 [here](docs/release1.md)

**Sprint 2**

In this sprint the group will continue working on the application and extending the functionality and implement persistance using json. 

The group will extend the application to contain more user stories. 

US-2: I want to log in to my user and view my already existing shopping list.


Read more about sprint 2 [here](docs/release2.md)

**Sprint 3**

In the last sprint the group will implement more functionality where an user can have multiple lists connected to their logged in user. The group will also focus on REST API and REST SERVER.

US-3: As a user I want to create an account and create multiple grocery lists for each week

US-4: As a user I want to log in and view my grocery lists for selected dishes.

Read more about sprint 3 [here](docs/release3.md)

_Note: this plan is tentative, it may change._




