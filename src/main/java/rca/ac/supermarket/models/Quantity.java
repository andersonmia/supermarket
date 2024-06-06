package rca.ac.supermarket.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quantity {

    @Id
    @GeneratedValue
    private Long id;
    private String productCode;
    private int quantity;
    private String operation;
    private String date;

}
