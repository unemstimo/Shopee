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

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.RemoteUserAccess;

/**
 * Test class for RemoteUserAccess class
 */
public class RemoteUserAccessTest {
    
    private WireMockServer mockServer;
    private RemoteUserAccess remoteAccess;
    private WireMockConfiguration config;
    private FileHandeler fileHandler;
    ObjectMapper objectMapper = new ObjectMapper();

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
            """;

    @BeforeEach
    public void startWireMockAndSetup() throws URISyntaxException, IOException, InterruptedException {
        config = WireMockConfiguration.wireMockConfig().port(3000);
        mockServer = new WireMockServer(config.portNumber());
        mockServer.start();
        WireMock.configureFor("localhost", config.portNumber());
        remoteAccess = new RemoteUserAccess(new URI("http://localhost:" + mockServer.port() + "/users"), true );
    }

    @Test
    public void testGetAllUsers(){
        stubFor(get(urlEqualTo("/users")).withHeader("Accept", equalTo("application/json")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(SHOPEE_RESPONSE)));
        List<User> mockUsers = remoteAccess.getAllUsers();
        assertEquals(1, mockUsers.size());

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
