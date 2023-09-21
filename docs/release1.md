# **Release 1**

**Goal: Apply elementary domain logic and perform uncomplicated file handling.**
_________
__In the first release,__ a simple grocery list app has been created. In the app, a user can enter the desired food item and the quantity of that item in an input field, and then add the item to the shopping list. The shopping list appears on the screen where the user can view or modify the list. To make changes to the list, a user can tap on a food item and then decide whether to mark it as bought or simply remove it from the shopping list. If a food item is marked as bought, it is removed from the shopping list. The shopping list is saved to a file, so when the app is opened later, previously added items that have not been purchased yet will be displayed. User would also be able to see previously bought food items displayed in the list to the right.

__The group decided to have two files__, one for the food items in the shopping list and the other for the food items that has been bought. Food items in the file for the shopping list would be removed from the file when bought. After it has been marked bought it is placed in the file for the purchased food items. There are no food items that gets removed from this file. The reason behind this is that the group wanted the user to be able to allways see all previously bought items. 


__Desired implementation for release 1:__
For release 1, the initial goal was to create a simple app, which the group has accomplished. However, it was desirable for the group to enable users to name their own shopping lists so that the actual shopping list could be saved, allowing for the creation of multiple shopping lists. This is planned to be implemented in a future release. Additionally, it was desired to be able to mark a food item as purchased in the shopping list by highlighting it with a square icon, for example in green. The marked item should then move to the bottom of the list and still be displayed, rather than being placed in a new list as in the functionality of release 1. This is also an implementation that the group plans to add after release 1.


__________
## Code documentation

The group has concluded that it is most practical and efficient to comment directly in the source code. This makes it clear and straightforward for future developers/users to understand what the various methods and variables do. If these comments were placed in a separate file, a potential developer/user would need to frequently switch between code and file, which can be disruptive and confusing. Additionally, it is convenient for internal project development within the group to quickly grasp what the rest of the group has implemented.
_____________
## Working process

For the first release, the group has learned to use issues, tasks, and milestones. Initially, during the development process in the first sprint, several issues were set up with multiple tasks associated with them. These tasks were then allocated to group members after the group had determined which app idea to use and had some idea of the code structure they envisioned.

The group encountered some challenges regarding collaboration using Git, but eventually, this was resolved. Collaborating using GitLab is not new to the group, but adhering to agile practices by setting up milestones, issues, branches, etc., required some initial learning. The group has tried to efficiently use branches and stick to assigned issues to avoid merge conflicts. 

Initially, the group worked extensively while all members were present. This was positive for facilitating effective communication and problem-solving. However, in the future, there will likely be less need for the entire group to work together all the time.


