# Shopee Project

## App Description
The shopee app is an application that works as a shopping list. An user can log in or create an account to further view or create multiple lists. The user can therefore have multiple lists and the application allows an user to create a shopping list and manage/edit the shopping list. The shopee app makes it easy to keep track of what the user need to purchase by adding food items along with the amount of the item. Food items can easly be deleted or be mark as bought. 
___

## Storage
The application supports cloud based use for saving and loading. The states and the servers will always be up to date by sending a post-request to the REST API when a state is changed. This makes it possible for the user to close the applicatiom, resume a session without explicitly load it from previus sessions and also use the app other places. Read more about REST API [here](./rest/readme.md)
_____

## Module documentation
### `core` module
Read more about core-module [here](../shopee/core/readme.md)
### `ui` module
Read more about the ui-module [here](../shopee/ui/readme.md)
### `rest` module
Read more about the rest-module [here](../shopee/rest/readme.md)
_____
## Diagrams 
Diagrams for shopee [here](../diagrams/readme.md)

___

## Illustrative screenshot of the application

The pictures illustrate how the group thinks the final product of the app will look like. 

### FrontPage:
![FrontPage](../diagrams/pictures/frame1.png)

### New Shopee List:
![New Shopee List](../diagrams/pictures/frame2.png)

### Edit Shopee List:
![Edit Shopee List](../diagrams/pictures/frame3.png)

## User story 1
As a user I want to be able to add food items to my shopping list, and also mark item as bought or remove items, for an efficient and simple shopping.
## User story 2
As a user I want to be able to log in to my user and view what I have purchased earlier and to se my shopping list.
## User story 3
As a user I want to create an account in this application and create grocery lists for every week to view what I have bought each week to simplify my weekly shopping trip. 
## User story 4
As a user I want to log in to view what groceries is needed for different dishes I want to make, just like a cook book but without description to make it easy for me to shop for each dish when in the grocery store.
_____

## Intended functionality at final release:


### Important to be able to see
#### Log in page
1. **Input fields** : The user should be able to easly see input fields for username and password
2. **Verification text** : User should be able to see text occur when clicking sign up/in that verificate the action done
### Home page
1. **Shopping lists** : The user should be able to view existing shopping lists created. 
2. **Buttons** : Should view button for creating list and log out button.
#### Shopping list page
1. **Shopping List** : The user should be able to see the food items added to the shopping list along with the amount of food items. Food items that is bought should not be shown in the list. If there os no items added yet, there is a square marked where food items may be added to.
2. **Bought list** : The user should be able to see a list of all the items that has been purchased by the user.
3. **Input fields** : The user can view input fields for food item and amount.
4. **Buttons** : The user should see buttons for removing/adding and mark item as bought. Should also see button for going back to previus page.

### Important to be able to do
#### Log in page
1. **Sign up** : User should be able to make an user with vaalid credentials
2. **Sign in** : User should be able to sign in if the user already have an account
### Home page
1. **View selected shopping list**: User should be able to click on wanted shopping list and view/edit the content of that list.
2. **Create new shopping list**: User should be able to create a new shopping list and further view/edit the list.
3. **Log out**: User should be able to log out of the current logged in account and be directed to the log-in-page.
#### Shopping list page
1. **Add food** : User should be able to use input field to add food item to shopping list
2. **Buy food** : User should be able to mark a food item in the list and then click button to mark as bought. The item switches place from the shopping list to the list of bought items. 
3. **Remove food** : User should be able to mark a food item and click the button for removing the item. The item should not be viewable anymore 
4. **Go back** : User should be able to click the back button and be directed to the previus page wich is the home page.

