package shopee.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import shopee.core.User;

import shopee.ui.dataaccess.RemoteUserAccess;

/**
 * Test class for RemoteUserAccess class
 */
public class RemoteUserAccessTest {
    
    private WireMockServer mockServer;
    private RemoteUserAccess remoteAccess;
    private WireMockConfiguration config;

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
        Strin
    }

    @AfterEach
    public void stopWireMockServer() {
        mockServer.stop();
    }


}
