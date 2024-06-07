package rca.ac.supermarket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    String description;
    double price;
    int quantity;
    String imageUrl;

    @ManyToOne
    Cart cart;
}
