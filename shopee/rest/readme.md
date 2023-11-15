# REST API

To implement REST API in this project, the group decided to use Spring boot framework for this. In order to implement this it was set up a seperate module for the REST API-server, called `rest`. This allowed the group to implement cloud-based storage by utilizing its REST API. Below it can be found information on how a client can interact with the REST API with the request supported by it. 


## **Classes**
- [rest](src/main/java/shopee/rest)
    - [ShopeeAppApplication.java](src/main/java/shopee/rest/ShopeeAppApplication.java)
    - [ShopeeUserController.java](src/main/java/shopee/rest/ShopeeUserController.java) : class handling request through the REST API
    - [ShopeeUserService.java](src/main/java/shopee/rest/ShopeUserService.java) : manage user-related operations for spring boot

## **Resources**
- [initial-shopee.json](src/main/resources/shopee/rest/initial-shopee.json)
- [application.properties](src/main/resources/application.properties)

## **Testing**
- [ShopeeApplicationTest.java](src/test/java/shopee/rest/ShopeeApplicationTest.java)
- [ShopeeControllerTest.java](src/test/java/shopee/rest/ShopeeControllerTest.java)
- [ShopeeServiceTest.java](src/test/java/shopee/rest/ShopeServiceTest.java)

## **HTTP methods and Endpoints**
Supported HTTP metods `GET`, `POST`, `PUT`, `DELETE`and `PATCH`, document the corresponding endpoints:

* `GET` **/users**: Gets all users stored
* `GET`**/users/{username}**: Gets a specific user 
* `POST`**/users/add**: Add a new user
* `POST`**/users/{username}/addList**: Add ShopeeList to specific user
* `DELETE`**/users/{username}/{listname}**: Delete ShopeeList from a user

### **Path variables**
* **{username}** in **/users/{username}**: This path variable holds the username for a specific user. This can be used when a client uses the `GET` request and gets the information for that user
* **{listname}** in **/userss/{username}/{listname}**: This path variable presents the list name of a ShopeeList object with the specific list name. This can be used when the `DELETE` request is made by the client for deleting a list connected to the specific user. 

