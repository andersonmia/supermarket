package rca.ac.supermarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
            @GeneratedValue
    Long id;
    String name;
    String productCode;
    String description;
    double price;
    int quantity;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    Cart cart;
}
