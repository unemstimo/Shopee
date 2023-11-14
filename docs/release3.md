# **Release 3**

**Goal: Implement rest API, Extend code functionality and user experience and reflect on project growth and teamwork**

## **Extended code functionality**

__The group has expanded the functionality__ and improved the code in this final release. The most notable changes from Release 2 include the expansion of the user interface and the successful implementation of a REST API. Read more about REST API [here](/shopee/rest/readme.md)


The change in functionality includes implementation of a new homepage for the user. In the initial phase of the project, we had a model where each user was associated with a single ShoppeeList. However, recognizing the need for enhanced flexibility and a more User oriented experience, we decided to evolve the architecture. The user will now be able to oranize multiple shopee lists. 
- abstract controller
The key change we implemented was transitioning from a single ShoppeeList per user to a model where a user could now manage multiple ShoppeeLists


__Test coverage__

The test coverage has significantly improved for the final release. The comprehensive coverage is prompted by the need to verify all essential code through testing. Although, the code is written such that it is very hard to get full coverage in jacoco. 

The main issue during test writing was the file handler. Although we designed the code to always have a file to write to, it still contains a throw statement for cases where it cannot find a file. However, this throw will never occur, making it unnecessary to test the code in this regard. All methods utilizing the file handler include a try/catch statement that will never result in an exception. Nevertheless, Jacoco requires us to test all lines for complete coverage, preventing us from achieving full coverage.

The app's starter, the last significant field left untested, doesn't require testing since it lacks variables, and the app starts flawlessly both locally and remotely. 

Apart from these, all other critical aspects have been tested.

## **User experience improvements**
__For the final release we have improved the user experience of the app.__ The primary improvement centers around the introduction of a new feature – the addition of a user-friendly homepage. 

This page provides users with a comprehensive view of all their Shopee lists. Users can easily create new Shopee lists and make modifications or deletions to existing ones. The page is simple but includes all the functions we feel is needed. Clicking the 'modify' button directs users to the page where the shopping list is displayed. This is the page that the user was sent to after login in release 2. The app offers intuitive navigation, and in case any prohibited actions are attempted, a default error message will promptly describe the issue.

## **Working habits and task managment through release 3**
When we recieved the feedback from release two we had to sit down and discuss how we could improve our teamwork. It is important for the project that we communicate well and work together as a group. 

For this release we have focused on a better planning phase. We used more time planning and discussing before we started working on the project. We felt that this really made the teamwork easier because we knew what the others where doing. We actively useed the assignee function in gitlab so there was no doubt who was in charge of the issue. 


When working on a group project it is important to use branches for version controll and enable collaboration. This makes it easy to assigne different tasks to group members and use different branches for these tasks, to avoid merge conficts and ensure agile developing. For this release the group decided on a general format for the branch names to ensure consistency and clarity through the working proccess of the application. 

Branch name format: **"(category word) - (issue number) - (issue name)"**

In this sprint it was focused on avoiding long branch names that are precise and informative. 

**Category words:** Includes informative words that describes what is intended with the branch, commonly used is "feature", which is for adding, removing or modifying features. Other words can be "hotfix", "bugfix", "test" etc. 

**Issue number:** Every branch is connected to an issue or task by using its number so it is easly known which issue the person is working on in that branch. 

**Issue name:** By this it is meant to have the title of the issue or a shorted version of the issue name. Since a lot of the issue names in gitLab where long, it was commonly used a shortened version. The issue name should be informative and precise. 

**Example:** 
* Issue in gitLab: "Make documentation for release 3", issue number: #154
* Branch name: "feature-#154-documentation-release3"


**We where also stricter with the reviewing of eachothers code when merging.** As a security point we always reviewed each others work before merging it into the project. By leaving comments it was easier to go back and check up on the project. 

The group continued meeting alteast every tuesday and thursday for weekly meeting and work session. On these days the group started by going through what every member has done since last session and also  schedual the working session. Between the meetings it was assigned issues/tasks to each nember that was due to next meeting. This contributed to a structured working process, ensured agile developing and good teamwork. 



## Afterthoughts and conclusion
__At the conclusion of this sprint__, the group is confident that the application effectively fulfills its primary purpose, although there is potential for further development to enhance the completeness of the Shopee application if desired.

In retrospect, we'd like to share some reflections to provide a better understanding of our project journey. This project marked our group's initial foray into GitLab, and we faced initial challenges in the proper assignment of tasks to issues, which we progressively improved upon as the project advanced. At the beginning of the semester, our group established a group contract, stipulating weekly meetings, which formed a strong basis for effective cross-group communication.

In retrospect, the group has reflected over the projects working process. On a positive note the group is satisfied with the attendence in the group meeting and that group members has 

positiv:
- vi har vært flinke til å holde møtene, følge kontrakten, godt samarbeid
- bra prograsjon utover semester og for hver release
- sørget for at ingen satt aleine med et problem, ting ble 
Kunne gjort bedre
- kodekvalitet
- vi kunne samlet oss bedre og bruke mere tid på å sette en plan og visualisere hvordan vi skal gjøre












Utilizing communication channels such as Messenger, in combination with practices like pair programming and regular meetings, ensured that none of our group members confronted significant challenges in isolation. Instead, we collectively tackled issues as a team. Our adherence to the group contract also ensured that working hours were consistently met.

In conclusion, every member of our group has gained valuable experience from participating in this project, particularly as it marks our first involvement in a larger programming endeavor.