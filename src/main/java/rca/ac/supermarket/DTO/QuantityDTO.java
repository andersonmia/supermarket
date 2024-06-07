package rca.ac.supermarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.models.Quantity;

@Getter
@Setter
@AllArgsConstructor
public class QuantityDTO {

    private String productCode;
    private int quantity;
    private String operation;
    private String date;

    public Quantity toEntity() {
        Quantity quantity = new Quantity();
        quantity.setProductCode(this.productCode);
        quantity.setQuantity(this.quantity);
        quantity.setOperation(this.operation);
        quantity.setDate(this.date);
        return quantity;
    }
}
