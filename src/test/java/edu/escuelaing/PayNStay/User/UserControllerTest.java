package edu.escuelaing.PayNStay.User;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.escuelaing.PayNStay.Controller.User.UserController;
import edu.escuelaing.PayNStay.Repository.User.UserDto;
import edu.escuelaing.PayNStay.Service.User.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> users = Arrays.asList(new UserDto(), new UserDto());
        when(userService.getAllUsers()).thenReturn(users);

        List<UserDto> result = userController.getAllUsers();

        assertEquals(2, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        UUID id = UUID.randomUUID();
        UserDto user = new UserDto();
        when(userService.getUserById(id)).thenReturn(user);

        ResponseEntity<UserDto> response = userController.getUserById(id);

        assertEquals(ResponseEntity.ok(user), response);
        verify(userService, times(1)).getUserById(id);
    }

    @Test
    public void testCreateUser() {
        UserDto user = new UserDto();
        when(userService.createUser(user)).thenReturn(user);

        UserDto result = userController.createUser(user);

        assertEquals(user, result);
        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testUpdateUser() {
        UUID id = UUID.randomUUID();
        UserDto user = new UserDto();
        when(userService.updateUser(id, user)).thenReturn(user);

        ResponseEntity<UserDto> response = userController.updateUser(id, user);

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
}
