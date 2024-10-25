package edu.escuelaing.PayNStay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import edu.escuelaing.PayNStay.model.User;
import edu.escuelaing.PayNStay.model.User.UserType;
import edu.escuelaing.PayNStay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserById() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertNotNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = userService.getUserById(userId);

        assertNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertNotNull(result.getId()); 
        verify(userRepository).save(user);
    }

    @Test
    void testUpdateUser() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        User updatedUser = new User();
        
        updatedUser.setName("John Doe");
        updatedUser.setEmail("john@example.com");
        updatedUser.setPassword("newpassword");
        updatedUser.setUserType(UserType.AGENT); 

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User result = userService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals("John Doe", existingUser.getName());
        assertEquals("john@example.com", existingUser.getEmail());
        assertEquals("newpassword", existingUser.getPassword());
        assertEquals(UserType.AGENT, existingUser.getUserType());
        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);
    }

    @Test
    void testUpdateUserNotFound() {
        UUID userId = UUID.randomUUID();
        User updatedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = userService.updateUser(userId, updatedUser);

        assertNull(result);
        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}
