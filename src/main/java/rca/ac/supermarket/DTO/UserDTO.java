package rca.ac.supermarket.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.models.User;
import rca.ac.supermarket.utils.EnumConverter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Required")
    private String firstname;

    @NotBlank(message = "Required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Required")
    private String password;

    @NotBlank(message = "Required")
    private String phone;

    @NotBlank(message = "Required")
    private String role;

    public User toEntity() {
        User user = new User();
        user.setFirstname(this.firstname);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setPassword(this.password);
        user.setRole(EnumConverter.getUserRole(this.role));
        return user;
    }
}
