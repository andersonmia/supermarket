package rca.ac.supermarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rca.ac.supermarket.models.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    List<CartItemDTO> cartItems;

    public Cart toEntity() {
        Cart cart = new Cart();
        cart.setCartItems(this.cartItems.stream().map(CartItemDTO::toEntity).collect(Collectors.toList()));
        return cart;
    }
}
