package rca.ac.supermarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rca.ac.supermarket.models.CartItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    String name;
    String description;
    double price;
    int quantity;
    String imageUrl;

    public CartItem toEntity() {
        CartItem cartItem = new CartItem();
        cartItem.setName(this.name);
        cartItem.setDescription(this.description);
        cartItem.setPrice(this.price);
        cartItem.setQuantity(this.quantity);
        cartItem.setImageUrl(this.imageUrl);
        return cartItem;
    }
}
