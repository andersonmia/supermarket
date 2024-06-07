package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.models.Purchased;

@Getter
@Setter
public class ReportDTO {
    private String productCode;
    private int quantity;
    private double totalPrice;
    private String date;

    public Purchased toEntity() {
        Purchased purchased = new Purchased();
        purchased.setProductCode(this.productCode);
        purchased.setQuantity(this.quantity);
        purchased.setTotal(this.totalPrice);
        purchased.setDate(this.date);
        return purchased;
    }
}
