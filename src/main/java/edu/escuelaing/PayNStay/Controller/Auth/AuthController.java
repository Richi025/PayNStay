package edu.escuelaing.PayNStay.Controller.Auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.escuelaing.PayNStay.Security.JwtUtil;
import edu.escuelaing.PayNStay.Service.User.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    private final JwtUtil jwtUtil;


    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(null);
    }


}