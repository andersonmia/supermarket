package rca.ac.supermarket.services.implementations;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.UserDTO;
import rca.ac.supermarket.exceptions.AuthenticationFailedException;
import rca.ac.supermarket.exceptions.DuplicateEmailException;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;
import rca.ac.supermarket.models.User;
import rca.ac.supermarket.repositories.UserRepository;
import rca.ac.supermarket.services.UserService;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public User registerUser(UserDTO userDTO) {
        validateDTO(userDTO);

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateEmailException("Customer", "email", userDTO.getEmail());
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    public boolean authenticate(String email, String password) {
        User user = this.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationFailedException("Invalid email or password.");
        }
        return true;
    }

    private void validateDTO(UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<UserDTO> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException("Validation failed: \n" + sb.toString());
        }
    }
}
