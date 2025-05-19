package org.bank.oumaymadaoudi.controllers;

import org.bank.oumaymadaoudi.dtos.UserDTO;
import org.bank.oumaymadaoudi.dtos.auth.AuthRequest;
import org.bank.oumaymadaoudi.dtos.auth.AuthResponse;
import org.bank.oumaymadaoudi.dtos.auth.RegisterRequest;
import org.bank.oumaymadaoudi.entities.User;
import org.bank.oumaymadaoudi.security.JwtUtil;
import org.bank.oumaymadaoudi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Login endpoint.
     *
     * @param authRequest the authentication request
     * @return the authentication response with JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            // Get user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // Get user DTO
            UserDTO userDTO = userService.getUserByUsername(userDetails.getUsername());
            
            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);
            
            // Create response
            AuthResponse response = new AuthResponse(
                    token,
                    userDTO.getUsername(),
                    userDTO.getRole(),
                    userDTO.getCustomerId()
            );
            
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during authentication");
        }
    }

    /**
     * Register endpoint.
     *
     * @param registerRequest the registration request
     * @return the authentication response with JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Check if username is available
            if (!userService.isUsernameAvailable(registerRequest.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
            }
            
            // Create user
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(registerRequest.getUsername());
            userDTO.setRole(registerRequest.getRole());
            userDTO.setCustomerId(registerRequest.getCustomerId());
            
            UserDTO createdUser = userService.createUser(userDTO, registerRequest.getPassword());
            
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerRequest.getUsername(),
                            registerRequest.getPassword()
                    )
            );
            
            // Get user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);
            
            // Create response
            AuthResponse response = new AuthResponse(
                    token,
                    createdUser.getUsername(),
                    createdUser.getRole(),
                    createdUser.getCustomerId()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during registration: " + e.getMessage());
        }
    }
}