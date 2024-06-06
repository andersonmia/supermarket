package rca.ac.supermarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rca.ac.supermarket.enums.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    Long id;
    String firstname;
    String email;
    String password;
    String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public boolean isCustomer() {
        return this.role == UserRole.CUSTOMER;
    }
}
