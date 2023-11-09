package shopee.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import shopee.core.FoodItem;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.RemoteUserAccess;

/**
 * Test class for RemoteUserAccess class
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RemoteUserAccessTest {
    
    private WireMockServer mockServer;
    private RemoteUserAccess remoteAccess;
    private FileHandeler fileHandler = new FileHandeler();
    private List<User> allUsers; 
    private User user = new User();
    private ObjectMapper mapper = new ObjectMapper();

    private static final int port = 8080;
    private static final String host = "localhost";


    /** 
    private static String SHOPEE_RESPONSE = """
            {
                [ {
                    "username" : "johan@ntnu.no",
                    "password" : "Johan123@",
                    "shopeeLists" : [ ]
                  }, {
                    "username" : "osk@.no",
                    "password" : "Oskar123@",
                    "shopeeLists" : [ {
                      "listName" : "week49",
                      "shopList" : [ {
                        "foodName" : "Apple",
                        "foodAmount" : 4
                      } ],
                      "boughtList" : [ ]
                    } ]
                  }, {
                    "username" : "une@.no",
                    "password" : "Oskar@123",
                    "shopeeLists" : [ ]
                  } ]
            }
            """;*/

    
    @BeforeAll
    public void rigUp() throws FileNotFoundException {
        fileHandler.setFilePath("remote.json");
        allUsers = fileHandler.jsonToObj();
        User testUser = new User("test@test.no", "testing@123");
        ShopeeList testList = new ShopeeList("testShoppingListe");
        FoodItem testFoodItem = new FoodItem("Apple", 7);
        testList.addFoodShopList(testFoodItem.getFoodName(), testFoodItem.getFoodAmount());
        testUser.addShopeeList(testList);
        fileHandler.writeToFile(testUser);
    }


    @BeforeEach
    public void setUp() {
		mockServer = new WireMockServer(options().port(8080));
        mockServer.start();
        WireMock.configureFor(host, port);
        try {
            remoteAccess = new RemoteUserAccess(
            new URI("http://localhost:" + mockServer.port() + "/"), true);
        } catch (Exception e) {
        e.printStackTrace();
    }
  }



    @Test
    public void testGetAllUsers() throws JsonProcessingException, FileNotFoundException{
        /** 
        User user = new User("example@ex.no", "example@1");
        fileHandler.setFilePath("remote.json");
        fileHandler.jsonToObj();
        WireMock.stubFor(get("/users").willReturn(new ResponseDefinitionBuilder().withBody(mapper.writeValueAsString(allUsers))));
        
        List<User> mockUsers = remoteAccess.getAllUsers();
        assertEquals(1, mockUsers.size());*/
         
        List<User> expectedUsers = List.of(new User("user1@example.com", "password@1"), new User("user2@example.com", "password@2"));
        WireMock.stubFor(get("/users")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(new ObjectMapper().writeValueAsString(expectedUsers))
                ));

        List<User> actualUsers = remoteAccess.getAllUsers();

        assertEquals(expectedUsers.size(), actualUsers.size());
        
    }
    
    
    @Test
    public void testGetUser(){
        String testUser = "une@.no";
        String url = "/users/" + testUser;
        stubFor(get(urlEqualTo(url)).withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json").withBody(SHOPEE_RESPONSE)));
        User user = remoteAccess.getUser(testUser);
        assertEquals("une@.no", user);
    }

    @Test
    public void testAddUser() throws JsonProcessingException{
        String url = "/users/add";
        User user = new User("testUser@.no", "Testing123@");
        String newUserJson = objectMapper.writeValueAsString(user);
        stubFor(post(url).withHeader("Accept", equalTo("application/json"))
            .withRequestBody(equalTo(SHOPEE_RESPONSE))
            .willReturn(aResponse().withStatus(201)));
            
        remoteAccess.addUser(user);
        assertEquals(user, remoteAccess.getUser("testUser@.no"));
    }

    /**
     * Tests the add method in RemoteUserAcces
     * Initializes new object of ShopeeList, adds that to a user that does not have any shopping lists
     * Tests if the shopping list created is the same that the user got
     * @throws JsonProcessingException
     */

    @Test
    public void testAddShopeeList() throws JsonProcessingException{
        ShopeeList testList = new ShopeeList("testList");
        String url = "users/une@.no/add";
        stubFor(post(url).withHeader("Accept", equalTo("application/json"))
            .withRequestBody(equalTo(SHOPEE_RESPONSE))
            .willReturn(aResponse().withStatus(201)));
            
        remoteAccess.addShopeeList("une@.no", testList);
        assertEquals(testList, remoteAccess.getUser("une@.no").getShopeeLists().get(0));
    }

    @Test
    public void testDeleteShopeeList() {
        String url = "users/osk@.no/delete";
        stubFor(delete(url).withHeader("Accept", equalTo("application/json"))
            .withRequestBody(equalTo(SHOPEE_RESPONSE))
            .willReturn(aResponse().withStatus(201)));
        remoteAccess.deleteShopeeList("osk@.no", "week49");
        assertEquals(0, remoteAccess.getUser("osk@.no").getShopeeLists().size());
    }



    @AfterEach
    public void stopWireMockServer() {
        mockServer.stop();
    }


}
