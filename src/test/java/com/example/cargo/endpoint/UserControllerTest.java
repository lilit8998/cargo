package com.example.cargo.endpoint;

import com.example.cargo.dto.UserDto;
import com.example.cargo.entity.User;
import com.example.cargo.mapper.UserMapper;
import com.example.cargo.repository.UserRepository;
import com.example.cargo.security.SpringUser;
import com.example.cargo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SpringUser springUser;

    @Mock
    private HttpSession httpSession;

    @Mock
    private UserController userController;

    @Before
    public void setUp() {
        userService = Mockito.mock(UserService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = Mockito.mock(UserMapper.class);
        userController = new UserController(userService, passwordEncoder, userRepository, userMapper);
    }


    @Test
    public void loginPage() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        SpringUser userLogin = new SpringUser(user);
        String name = userController.userAccount(userLogin, model);
        assertEquals("user/account", name);
        verify(model).addAttribute("user", userLogin);
    }

    @Test
    public void loginPageFiled() {
        SpringUser userLoginFiled = null;
        String name = userController.userAccount(userLoginFiled, model);
        assertEquals("redirect:/login", name);
        verifyNoInteractions(model);
    }

    @Test
    public void loginSuccess() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        SpringUser userLogin = new SpringUser(user);
        String name = userController.loginSuccess(userLogin);
        assertEquals("redirect:/user/account", name);
    }

    @Test
    public void loginFailed() {
        UserDetails userDetails = mock(UserDetails.class);
        Model model = mock(Model.class);
        when(userDetails.getUsername()).thenThrow(new UsernameNotFoundException("User not found"));
        String name = userController.loginSuccess(springUser);
        assertEquals("redirect:/", name);
    }

    @Test
    public void deleteUser() {
        long userId = 1L;
        User existingUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        String result = userController.deleteUser(userId, model, httpSession);
        verify(userRepository, times(1)).delete(existingUser);
        verify(httpSession, times(1)).invalidate();
        assertEquals("redirect:/", result);

    }

    @Test
    public void updateForm() {
        User user = new User();
        long userId = 1L;
        user.setId(userId);
        user.setName("User");

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        ModelMap modelMap = new ModelMap();
        String name = userController.updateForm(userId, modelMap);

        assertEquals("user/update", name);
        assertEquals(user, modelMap.get("user"));
    }

    @Test
    public void updateFormFiled() {
        User user = new User();
        long userId = 1L;
        user.setId(userId);
        user.setName("User");

        when(userService.findById(userId)).thenReturn(Optional.empty());

        ModelMap modelMap = new ModelMap();
        String name = userController.updateForm(userId, modelMap);

        assertEquals("/", name);
        assertEquals(0, modelMap.size());
    }

    @Test
    public void userAccount() throws Exception {

        User user = new User();
        String email = "test@gmail.com";
        String password = "password";
        String token = "abc123token";
        boolean isActive = true;

        user.setEmail(email);
        user.setPassword(password);
        user.setToken(token);
        user.setActive(isActive);

        SpringUser springUser = new SpringUser(user);

        assertEquals(email, springUser.getUsername());
        assertEquals(password, springUser.getPassword());
        assertEquals(token, springUser.getUser().getToken());
        assertEquals(isActive, springUser.getUser().isActive());
    }


    @Test
    public void verifyUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setActive(false);
        String token = "abc123token";

        when(userService.findByToken(token)).thenReturn(user);

        Model model = new ConcurrentModel();
        String result = userController.verifyUser(token, model);

        assertNotNull(result);
        assertEquals("redirect:/", result);
        assertTrue(user.isActive());
    }

    @Test
    public void verifyUserWithInvalidToken() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setActive(false);
        String invalidToken = "invalidToken";

        when(userService.findByToken(invalidToken)).thenReturn(null);

        Model model = new ExtendedModelMap();
        String result = userController.verifyUser(invalidToken, model);

        assertEquals("redirect:/", result);
        assertTrue(model.containsAttribute("msg"));
        assertEquals("Invalid token", model.getAttribute("msg"));
    }


    @Test
    public void userRegisterPage() throws Exception {
        mockMvc.perform(get("/user/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void userRegisterSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("password123!");

        bindingResult = new BeanPropertyBindingResult(userDto, "userDto");

        when(passwordEncoder.encode(anyString())).thenReturn("encodePassword");
        when(userService.isEmailExists(anyString())).thenReturn(false);
        when(userMapper.map(any(UserDto.class))).thenReturn(new User());

        String result = userController.userRegister(userDto, bindingResult, model);

        verify(userService, times(1)).register(any(User.class));
        assertTrue(result.startsWith("redirect:/user/registration"));
        verify(model).addAttribute(eq("msg"), eq("User has been registered successfully."));
    }

    @Test
    public void userRegisterFailed() {
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setEmail("test@gmail.com");
        userDto.setPassword("password123!");

        bindingResult = new BeanPropertyBindingResult(userDto, "userDto");

        when(passwordEncoder.encode(anyString())).thenReturn("encodePassword");
        when(userService.isEmailExists(anyString())).thenReturn(false);
        when(userMapper.map(any(UserDto.class))).thenReturn(new User());

        doThrow(new RuntimeException("Registration failed")).when(userService).register(any(User.class));

        String result = userController.userRegister(userDto, bindingResult, model);

        verify(userService, times(1)).register(any(User.class));
        assertTrue(result.startsWith("redirect:/user/registration"));
        verify(model).addAttribute(eq("msg"), eq("Registration failed"));
    }

}