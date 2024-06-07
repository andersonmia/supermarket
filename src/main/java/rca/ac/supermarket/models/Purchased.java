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
public class Purchased {

    @Id
    @GeneratedValue
    long id;
    String productCode;
    int quantity;
    double total;
    String date;

    @ManyToOne
            @JoinColumn(name = "user_id", nullable = false)
    User user;
}
