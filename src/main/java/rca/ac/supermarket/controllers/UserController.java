package rca.ac.supermarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rca.ac.supermarket.DTO.*;
import rca.ac.supermarket.authentication.JwtTokenUtil;
import rca.ac.supermarket.enums.ResponseType;
import rca.ac.supermarket.services.CustomUserDetailsService;

import rca.ac.supermarket.services.UserService;
import rca.ac.supermarket.utils.ExceptionHandlerUtil;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management System", description = "Operations pertaining to user in Online Store")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @Operation(summary = "Register a new User")
    public ResponseEntity<Response> registerUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.status(201).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(userService.registerUser(userDTO)));
        }catch (Exception e){
            return ExceptionHandlerUtil.handleException(e);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login a User and get a JWT")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/email")
    @Operation(summary = "Get user details by email")
    public ResponseEntity<Response> getCustomerByEmail(@RequestBody FindByEmailDTO dto) {
        try {
            return ResponseEntity.status(200).body(new Response().setResponseType(ResponseType.SUCCESS).setPayload(userService.findByEmail(dto.getEmail())));
        } catch (Exception e) {
            return ExceptionHandlerUtil.handleException(e);
        }
    }
}