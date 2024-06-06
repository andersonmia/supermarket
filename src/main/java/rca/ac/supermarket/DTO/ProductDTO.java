package rca.ac.supermarket.DTO;


import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.models.Product;

@Getter
@Setter
public class ProductDTO {
    private String code;
    private String name;
    private String productType;
    private double price;
    private String inDate;
    private String image;

    public Product toEntity() {
        Product product = new Product();
        product.setCode(this.code);
        product.setName(this.name);
        product.setProductType(this.productType);
        product.setPrice(this.price);
        product.setInDate(this.inDate);
        product.setImage(this.image);
        return product;
    }
}
