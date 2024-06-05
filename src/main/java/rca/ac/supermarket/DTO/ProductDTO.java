package rca.ac.supermarket.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String code;
    private String name;
    private String productType;
    private double price;
    private String inDate;
    private String image;

}
