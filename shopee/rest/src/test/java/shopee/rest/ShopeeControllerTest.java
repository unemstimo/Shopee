package shopee.rest;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import shopee.json.FileHandeler;
import shopee.core.User;

public class ShopeeControllerTest {
    @Mock
    private ShopeeUserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private List<User> mockUsers;
    private FileHandeler handler = new FileHandeler();
    private User testUser;


    @BeforeEach
    public void setUp() throws IllegalStateException, IOException {
        userService = mock(ShopeeUserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ShopeeUserController(userService)).build();
        objectMapper = new ObjectMapper();
        mockUsers = ShopeeUserService.createInitialUser();
        testUser = mockUsers.get(0);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(mockUsers);
        List<User> allUsers = userService.getAllUsers();
        String expectedString = objectMapper.writeValueAsString(allUsers);
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
               .andExpect(status().isOk())
               .andReturn()
               .getResponse()
               .getContentAsString();

        assertEquals(expectedString, result, "Did not match from local server compared to json file");
    }

    @Test
    public void testGetUser() throws Exception {
        
        when(userService.getAllUsers().get(0)).thenReturn(testUser);
        String expectedUser = objectMapper.writeValueAsString(testUser);
        String resultUser = mockMvc.perform(MockMvcRequestBuilders.get("/users/" +testUser.getUsername()))
               .andExpect(status().isOk())
               .andReturn()
               .getResponse()
               .getContentAsString();
          
        assertEquals(expectedUser,resultUser, "Did not match from local server compared to json file");

    }
}
