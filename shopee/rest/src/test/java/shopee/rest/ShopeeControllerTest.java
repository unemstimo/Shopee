package shopee.rest;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
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
}
