package edu.escuelaing.PayNStay.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import edu.escuelaing.PayNStay.model.AuthRequest;
import edu.escuelaing.PayNStay.security.JwtUtil;
import edu.escuelaing.PayNStay.service.CustomUserDetailsService;


public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
public void testCreateAuthenticationTokenSuccess() throws Exception {
    AuthRequest authRequest = new AuthRequest();
    authRequest.setUsername("user");
    authRequest.setPassword("password");

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
    when(customUserDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
    when(userDetails.getUsername()).thenReturn("user");  
    when(jwtUtil.generateToken("user")).thenReturn("jwt-token");

    String result = authenticationController.createAuthenticationToken(authRequest);

    assertEquals("jwt-token", result);
    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    verify(customUserDetailsService).loadUserByUsername("user");
    verify(jwtUtil).generateToken("user");
}


    @Test
    public void testCreateAuthenticationTokenFailure() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("user");
        authRequest.setPassword("wrong-password");

        doThrow(new RuntimeException("Incorrect username or password")).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        try {
            authenticationController.createAuthenticationToken(authRequest);
            fail("Expected Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Incorrect username or password", e.getMessage());
        }

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(customUserDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtil, never()).generateToken(anyString());
    }
}
