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

## **Why the group chose document metaphor over implicit storage**
__The group chose to use the document metaphor__ over implicit storage. In this kind of user interaction, with written input, it would be preferable that the user can decide for themselves when they are ready to add a food item to the shopping list, and in doing so, also saving it to file.

For instance, it would be disadvantageous if the program began saving the name and value of a food item while the user is still typing. What if the user then logs out without clicking the 'add food' button? In such a case, we do not want an item that shouldn't be in the shopping list, to appear nonetheless. Additionally, it would require unnecessary computational power and file storage space to manage data that we are yet uncertain whether the user intends to add.

In light of this, the group found it most suitable to use the document metaphor in this project.

## **Desired implementations for release 2 and future goals**
The goal with extending the code functionality and improve the user experience was achieved. Log-in functionality now makes it possible for a user to save its shopee list, which means that we now operate with a number of different objects to save to file, in contrast with only one singular shopee list in release 1. Furthermore, the plan for the next release is to implement code functionality so each user can have multiple shopee lists associated with them. In conjunction with this, the group also plans to incorporate a third fxml-file which will serve as the second page the user meets; the home-page for the user where one can navigate to the different shopee lists and create new ones.  

Throughout this release, the group has also taken the opportunity to reflect further on the desired end product. The decision has been made to deviate from the plan set in release 1, which involved marking purchased items with an icon and placing them at the bottom of the shopping list. 
Firstly, the group believes that the current layout is just as clear and user-friendly as the previously planned approach. Secondly, a crucial argument is that the group prefers to channel its efforts into other aspects of project development in the final release. Given that the evaluation does not necessarily hinge on the code logic itself, additional time will be allocated to further develop upcoming requirements related to file storage, folder structure, documentation, as well as meticulous planning and collaboration on GitLab.

Another point the group has worked on, is the teamwork and especially the planning and collaboration in GitLab. This is covered in the section below.

## **Reflection on Teamwork and use of GitLab**
Thorughout the project, the group have been good at teamwork. Mostly of the work done, have been carried out with the entire group gathered rather than working alone. We have also focused on working more in pairs this release compared to the first one. 

Additionally, the team have spent more time on planning the workflow in advance, using issues and tasks. It has also been a goal to work on making the issues even more detailed, both in naming, description and the way they are parted in tasks. The number of commits in this release have increased, much of it due to the emphasis on committing for each task completed, ensuring that work is done in an organized and orderly manner. 

On the other hand, the group acknowledges that there is still room for improvement in this regard. We need to continue adhering to tasks and issues as we work in different branches. Furthermore, we aim to enhance our practice of breaking down issues and tasks before commencing work, as well as committing each time one of these is completed. This likely entails a more thorough upfront assessment of our objectives. 

In summary, the group has gained a profound understanding of the effectiveness of GitLab as a development tool, particularly in collaborative projects. We have also begun to gain a comprehensive overview of the valuable resources provided by GitLab and how to utilize them optimally.
