package shopee.dataaccess;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.RemoteUserAccess;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RemoteAccessTest {
    
    private static final int Port = 8080;
    private static final String Host = "localhost";
    private static WireMockServer server;
    private RemoteUserAccess remoteAccess;
    private FileHandeler handler ;
    private ObjectMapper mapper;
    private User testUser1;
    private User testUser2;

    @BeforeAll
    public void startUp() throws FileNotFoundException{
        handler = new FileHandeler("remoteTest.json");
        mapper = new ObjectMapper();
        this.testUser1 = new User("test@user.com", "Validp12@");
        this.testUser2 = new User("second@user.no", "Goood@32");
        testUser1.addShopeeList(new ShopeeList("list1"));
        handler.writeToFile(testUser1);
        handler.writeToFile(testUser2);
        
    }

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
    public void testGetAllUsers() throws JsonProcessingException, FileNotFoundException {
    List<User> users = handler.jsonToObj();
    
    // Update the stub path to match the actual request path
    WireMock.stubFor(get("/").willReturn(new ResponseDefinitionBuilder()
            .withBody(mapper.writeValueAsString(users))));

    List<User> remoteUsers = remoteAccess.getAllUsers();
    assertNotNull(remoteUsers);
    assertEquals(users.size(), remoteUsers.size());
    assertEquals(remoteUsers.get(0).getUsername(), "test@user.com");
    assertEquals(remoteUsers.size(),2);

    // Stub the WireMock server to return a non-200 status code
    WireMock.stubFor(get("/").willReturn(aResponse().withStatus(500)));

    // Test that the catch clause throws a RuntimeException
    assertThrows(RuntimeException.class, () -> remoteAccess.getAllUsers());
    }



    @AfterEach
    public void tearDown() {
        server.stop();
  }
}
