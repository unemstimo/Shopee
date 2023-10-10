# **Release 2**

**Goal: Extend code functionality and user experience, and reflect on project growth and teamwork**
_________
## **Extend code functionality**
The following has been done since release 1:

__The group has expanded the functionality__ of the code since release 1. The most significant new feature is the login system. A user can now either create a new account with a username and password or log in if the user account already exists. This allows users to store their shopee lists for later use. In this release, it's currently possible to only have one shopee list per user account. However, the group envisions changing this so that a user can have multiple shopee lists associated with them in the next release. There are specific requirements for the validity of both the username and password, which are specified in comments within the User.java class.

__Furthermore,__ one of the major changes we made from release 1 to release 2 is how the file handling is done. We changed from a txt file to a JSON file, and we are now using Jackson as a tool to organize the data being handeled.  We switched from text file handling to JSON using Jackson for several key reasons. JSON offers structured data storage, enhancing readability and maintainability. Jackson's efficient serialization simplifies data manipulation and boosts compatibility with external systems. JSON's support for complex structures ensures a versatile and future-proof solution, aligning our project with industry standards, improving data management, and enhancing overall functionality and interoperability.

__Additionally, more validation__ has been added throughout the code. This includes validation of user input, such as ensuring that food names and food amounts are in valid formats, as well as the name of the shopee list associated with a user.

__Moreover, the code's test coverage__ has significantly increased in this release. The group has focused on testing all architectural layers and has also implemented Jacoco to provide a test coverage report. Since release 1, test classes have been created for all the classes in the program. These test classes are filled with tests for most of the methods.

## **User experience improvements**
The following has been done for improving the user experience in the fxml files since release 1:

In the context of implementing the login functionality, the group has created a new FXML file. This page now serves as the initial interface that greets the user. Here, users are provided with the opportunity to input their username and password, followed by two buttons to choose from: either 'sign up' or 'sign in'. 
In the event of a failed login attempt or user registration, a customized message is displayed on the screen. Upon successful login, the user is directed to the same page as in release 1, where the shopping list is displayed. The only new addition to this page is a 'log out' button, which has been implemented to allow users to log out of the application. The group has prioritized a simple yet intuitive user interface. 

_________
## **Why the group chose document metaphor over implicit storage**
The group chose to use the document metaphor over implicit storage. 

## **Desired implementations for release 2**

## **Reflection on Teamwork and use of GitLab**

## **Future Goals**
