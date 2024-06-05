package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String phone;
    private String email;
    private String password;

}
