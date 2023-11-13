# REST API

To implement REST API in this project, the group decided to use Spring boot framework for this. In order to implement this it was set up a seperate module for the REST API-server, called `rest`. This allowed the group to implement cloud-based storage by utilizing its REST API. 

## **Classes**
- [rest](shopee/rest/src/main/java/shopee/rest)
    - [ShopeeAppApplication.java](shopee/rest/src/main/java/shopee/rest/ShopeeAppApplication.java)
    - [ShopeeUserController.java](shopee/rest/src/main/java/shopee/rest/ShopeeUserController.java) : class handling request through the REST API
    - [ShopeeUserService.java](shopee/rest/src/main/java/shopee/rest/ShopeUserService.java) : manage user-related operations for spring boot

## **Resources**
- [initial-shopee.json](shopee/rest/src/main/resources/shopee/rest/initial-shopee.json)
- [application.properties](shopee/rest/src/main/resources/shopee/rest/application.properties)

## **Testing**
- [ShopeeApplicationTest.java](shopee/rest/src/test/java/shopee/rest/ShopeeApplicationTest.java)
- [ShopeeControllerTest.java](shopee/rest/src/test/java/shopee/rest/ShopeeControllerTest.java)
- [ShopeeServiceTest.java](shopee/rest/src/test/java/shopee/rest/ShopeServiceTest.java)

