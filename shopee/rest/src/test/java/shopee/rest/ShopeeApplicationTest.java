package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import shopee.core.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration( classes = {ShopeeAppApplication.class, ShopeeUserService.class, ShopeeUserController.class} )
public class ShopeeApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;
     
     @Test
    public void contextLoads() {
        // Make a request to the root URL of the application.
        ResponseEntity<List<User>> responseEntity = restTemplate
        .getForEntity("/users", new TypeReference<List<User>>(){});

        // Verify that the HTTP response status code is 200 (OK).
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Wrong HTTP-status");
        assertNotNull(responseEntity.getBody(), "Response body was null");
    }
  
}