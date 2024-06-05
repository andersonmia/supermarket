package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityDTO {

    private Long id;
    private String productCode;
    private int quantity;
    private String operation;
    private String date;
}
