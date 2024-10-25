package edu.escuelaing.PayNStay.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import edu.escuelaing.PayNStay.model.User;
import edu.escuelaing.PayNStay.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertEquals(2, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        UUID id = UUID.randomUUID();
        User user = new User();
        when(userService.getUserById(id)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(ResponseEntity.ok(user), response);
        verify(userService, times(1)).getUserById(id);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        User result = userController.createUser(user);

        assertEquals(user, result);
        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testUpdateUser() {
        UUID id = UUID.randomUUID();
        User user = new User();
        when(userService.updateUser(id, user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(id, user);

        assertEquals(ResponseEntity.ok(user), response);
        verify(userService, times(1)).updateUser(id, user);
    }

    @Test
    public void testDeleteUser() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = userController.deleteUser(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(userService, times(1)).deleteUser(id);
    }

    @Test
    void testRegisterUserSuccess() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);  

        String viewName = userController.registerUser(user, model);

        assertEquals("login", viewName);
        verify(userService).createUser(user);
        verify(model).addAttribute("message", "User registered successfully!");
        verify(model).addAttribute("messageType", "success");
    }

    @Test
    void testRegisterUserFailure() {
        User user = new User();
        doThrow(new RuntimeException("Registration error")).when(userService).createUser(user);

        String viewName = userController.registerUser(user, model);

        assertEquals("register", viewName);
        verify(userService).createUser(user);
        verify(model).addAttribute("message", "Error registering user: Registration error");
        verify(model).addAttribute("messageType", "error");
    }
}
