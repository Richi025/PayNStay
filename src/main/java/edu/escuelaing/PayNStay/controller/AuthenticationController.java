package edu.escuelaing.PayNStay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.PayNStay.model.AuthRequest;
import edu.escuelaing.PayNStay.service.CustomUserDetailsService;
import edu.escuelaing.PayNStay.security.JwtUtil;

/**
 * AuthenticationController handles user authentication requests, 
 * including login and the generation of JWT tokens for authenticated users.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Handles the login request and generates a JWT token for a valid user.
     * If the authentication is successful, a JWT token is generated using the user's details.
     * 
     * @param authRequest The authentication request object containing username and password.
     * @return A JWT token if authentication is successful.
     * @throws Exception Thrown if authentication fails due to incorrect credentials.
     */
    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
