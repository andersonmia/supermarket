package rca.ac.supermarket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
            @GeneratedValue
    long id;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<>();
}
