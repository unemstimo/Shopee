package shopee.dataaccess;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.RemoteUserAccess;


/**
* Test class for remote RemoteUserAccess class.
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test for RemoteUserAccess")
public class RemoteAccessTest {
    
    private static final int Port = 8080;
    private static final String Host = "localhost";
    private static WireMockServer server;
    private RemoteUserAccess remoteAccess;
    private FileHandeler handler;
    private ObjectMapper mapper;
    private User testUser1;
    private User testUser2;

    /**
    * Creates new handler and mapper, and two new user objects, which are used during 
    * testing of the remoteUserAccess methods.
    *
    * @throws FileNotFoundException if files not found
    */
    @BeforeAll
    public void startUp() throws FileNotFoundException {
        handler = new FileHandeler("remoteTest.json");
        mapper = new ObjectMapper();
        this.testUser1 = new User("test@user.com", "Validp12@");
        this.testUser2 = new User("second@user.no", "Goood@32");
        testUser1.addShopeeList(new ShopeeList("list1"));
        handler.writeToFile(testUser1);
        handler.writeToFile(testUser2);
        
    }

    /**
    * Starts the server and connects to it if everything worsk as it should.
    *
    * @throws FileNotFoundException if files not found
    */
    @BeforeEach
    public void setUp() {
        server = new WireMockServer(options().port(8080));
        server.start();
        WireMock.configureFor(Host, Port);
        try {
            remoteAccess = new RemoteUserAccess(
            new URI("http://localhost:" + server.port() + "/"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorWithMockFalseServerError() throws Exception {
        URI uri = new URI("http://localhost:" + server.port() + "/");

        // Stub the WireMock server to return a non-200 status code
        stubFor(get("/").willReturn(aResponse().withStatus(500)));

        // Test that the constructor throws IOException with the appropriate message
        IOException exception = assertThrows(IOException.class,
             () -> new RemoteUserAccess(uri, false));
        assertEquals("Server is not running. HTTP Status Code: 500", exception.getMessage());
    }

    @Test
    public void testGetAllUsers() throws JsonProcessingException, FileNotFoundException {
        List<User> users = handler.jsonToObj();
        
        // Update the stub path to match the actual request path
        WireMock.stubFor(get("/").willReturn(new ResponseDefinitionBuilder()
                .withBody(mapper.writeValueAsString(users))));

        List<User> remoteUsers = remoteAccess.getAllUsers();
        assertNotNull(remoteUsers);
        assertEquals(users.size(), remoteUsers.size());
        assertEquals(remoteUsers.get(0).getUsername(), "test@user.com");
        assertEquals(remoteUsers.size(), 2);

        // Stub the WireMock server to return a non-200 status code
        WireMock.stubFor(get("/").willReturn(aResponse().withStatus(500)));

        // Test that the catch clause throws a RuntimeException
        assertThrows(RuntimeException.class, () -> remoteAccess.getAllUsers());
    }
    
    @Test
    public void testGetUser() throws FileNotFoundException, JsonProcessingException {
        List<User> users = handler.jsonToObj();
        User fileUser = users.stream().filter(u -> "second@user.no".equals(u.getUsername()))
        .findAny().orElseThrow();

        WireMock.stubFor(get("/users/second@user.no").willReturn(new ResponseDefinitionBuilder()
            .withBody(mapper.writeValueAsString(fileUser))));

        User remoteUser = remoteAccess.getUser("second@user.no");
        assertNotNull(remoteUser);
        assertEquals(remoteUser.getPassword(), fileUser.getPassword());
        assertEquals(remoteUser.getUsername(), fileUser.getUsername());

        // Stub the WireMock server to return a non-200 status code
        WireMock.stubFor(get("/users/second@user.no").willReturn(aResponse().withStatus(500)));

        // Test that the catch clause returns null
        assertNull(remoteAccess.getUser("second@user.no"));
    }

    @Test
    public void testAddUser() throws FileNotFoundException, JsonProcessingException {
        List<User> users = handler.jsonToObj();
        WireMock.stubFor(get("/").willReturn(new ResponseDefinitionBuilder()
                    .withBody(mapper.writeValueAsString(users))));

        List<User> remoteUsersBefore = remoteAccess.getAllUsers();
        User addUser = new User("added@user.com", "Remote123@");
        assertFalse(remoteUsersBefore.contains(addUser));
        // Stubbing the response for the POST request to /users/add
        stubFor(post("/users/add")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mapper.writeValueAsString(addUser))));
                        
        remoteAccess.addUser(addUser);
        
        WireMock.stubFor(get("/users/added@user.com").willReturn(new ResponseDefinitionBuilder()
            .withBody(mapper.writeValueAsString(addUser))));
        User newlyAddedUser = remoteAccess.getUser("added@user.com");
       
        assertNotNull(newlyAddedUser);
        assertEquals("added@user.com", newlyAddedUser.getUsername());

        //Stub the wiremock to return a non-200 status code, and check if a exception is thrown
        WireMock.stubFor(post("/users/add").willReturn(aResponse().withStatus(500)));
        assertThrows(RuntimeException.class, () -> remoteAccess.addUser(newlyAddedUser));
    }

    @Test 
    public void testAddShopeeList() throws FileNotFoundException, JsonProcessingException {
        String username = "test@user.com";
        ShopeeList newList = new ShopeeList("addingList");
    
        stubFor(post("/users/" + username + "/addList")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mapper.writeValueAsString(newList))));
        remoteAccess.addShopeeList(username, newList);
        
        // Stub the WireMock server to return a non-200 status code
        stubFor(post("/users/" + username + "/addList").willReturn(aResponse().withStatus(500)));

        // Test that the catch clause throws a RuntimeException
        assertThrows(RuntimeException.class, () -> remoteAccess.addShopeeList(username, newList));
    }

    @Test 
    public void testDeleteShopeeList() {
        String userName = testUser1.getUsername();
        String listName = "list1";
        stubFor(delete("/users/test@user.com/" + listName)
            .willReturn(aResponse()
            .withStatus(200)));
        remoteAccess.deleteShopeeList(userName, listName);

        //Stub the wiremock to return a non-200 status code, and check if a exception is thrown
        WireMock.stubFor(delete("/users/" + userName + "/" + listName)
            .willReturn(aResponse().withStatus(500)));

        assertThrows(RuntimeException.class, 
            () -> remoteAccess.deleteShopeeList(userName, listName));
    }

    @AfterEach
    public void tearDown() {
        server.stop();
    }
}