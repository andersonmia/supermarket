package rca.ac.supermarket.services;

import rca.ac.supermarket.DTO.UserDTO;
import rca.ac.supermarket.models.User;

public interface UserService {
    User registerUser(UserDTO userDTO);
    User findByEmail(String email);
    boolean authenticate(String email, String password);
}
