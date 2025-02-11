package shopee.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopee.core.ShopeeList;
import shopee.core.User;

/**
 * Class for testing ShopeeUserController.
 */
public class ShopeeControllerTest {
    @Mock
    private ShopeeUserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private List<User> mockUsers;
    private User testUser;

    /**
    * Sets up a mocked ShopeeUserService before each test, and creates test users
    * using the statick method service.CreateIntitalUsers.
    *
    * @throws FileNotFoundException if files not found
    */
    @BeforeEach
    public void setUp() throws FileNotFoundException  {
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
        assertEquals(expectedString, result, "Didnt match from local server compared to json file");
    }

    @Test
    public void testGetUser() throws Exception {
        
        when(userService.getUser("Terje@gmail.com")).thenReturn(testUser);
        String expectedUser = objectMapper.writeValueAsString(testUser);
        String resultUser = mockMvc.perform(MockMvcRequestBuilders
               .get("/users/" + testUser.getUsername()))
               .andExpect(status().isOk())
               .andReturn()
               .getResponse()
               .getContentAsString();
          
        assertEquals(expectedUser, resultUser, 
            "Did not match from local server compared to json file");
    }

    @Test
    public void testAddUser() throws Exception {
        User newUser = new User("Petter@ntnu.no", "Petter123@");
        String userJson = objectMapper.writeValueAsString(newUser);
        when(userService.addUser(userJson)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void testAddShopeeList() throws Exception {
        ShopeeList newList = new ShopeeList("TestList");
        String newListString = objectMapper.writeValueAsString(newList);
        // Mock the behavior of the service
        when(userService.addShopeeList(eq(testUser.getUsername()), any())).thenReturn(true);

        // Perform the POST request to add a ShopeeList and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/users/" + testUser.getUsername() + "/addList")
                .content(newListString)
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteShopeeList() throws Exception {
        String listName = testUser.getShopeeLists().get(0).getListName();
        String listForDeletion = objectMapper.writeValueAsString(testUser.getShopeeLists().get(0));
        when(userService.deleteShopeeList(testUser.getUsername(), listForDeletion))
        .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/" + testUser.getUsername() + "/" + listName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        String updatedUser = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/" + testUser.getUsername()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        User newUser = objectMapper.readValue(updatedUser, User.class);
        assertThrows(IllegalArgumentException.class, () -> newUser.getShopeeList(listName));
    }

}