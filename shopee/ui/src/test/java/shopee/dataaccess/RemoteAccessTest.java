package shopee.dataaccess;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import shopee.core.ShopeeList;
import shopee.core.User;
import shopee.json.FileHandeler;
import shopee.ui.dataaccess.RemoteUserAccess;

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



    @AfterEach
    public void tearDown() {
        server.stop();
  }
}
