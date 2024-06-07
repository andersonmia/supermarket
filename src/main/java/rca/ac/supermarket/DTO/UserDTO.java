package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.enums.UserRole;
import rca.ac.supermarket.models.User;

@Getter
@Setter
public class UserDTO {
    private String firstname;
    private String email;
    private String password;
    private String phone;
    private UserRole role;

    public User toEntity() {
        User user = new User();
        user.setFirstname(this.firstname);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setRole(this.role);
        return user;
    }
}
