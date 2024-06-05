package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchasedDTO {
    private Long id;
    private String productCode;
    private int quantity;
    private double total;
    private String date;
}
