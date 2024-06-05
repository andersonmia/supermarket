package rca.ac.supermarket.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rca.ac.supermarket.DTO.AuthenticationRequest;
import rca.ac.supermarket.DTO.AuthenticationResponse;
import rca.ac.supermarket.DTO.CustomerDTO;
import rca.ac.supermarket.authentication.JwtTokenUtil;
import rca.ac.supermarket.models.Customer;
import rca.ac.supermarket.services.CustomUserDetailsService;
import rca.ac.supermarket.services.CustomerService;


@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Management System", description = "Operations pertaining to customer in Online Store")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @Operation(summary = "Register a new customer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

    @PostMapping("/login")
    @Operation(summary = "Login a customer and get a JWT")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get customer details by email")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.findByEmail(email));
    }
}

