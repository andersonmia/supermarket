package rca.ac.supermarket.services;

import rca.ac.supermarket.DTO.CartDTO;
import rca.ac.supermarket.DTO.CartItemDTO;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CartService {
    CartDTO getOrCreateCart();
    CartDTO addItemToCart(long cartId, CartItemDTO cartItemDTO) throws ResourceNotFoundException;
    CartDTO updateCart(long id, CartDTO cartDTO) throws ResourceNotFoundException;
    void deleteCart(long id) throws ResourceNotFoundException;
    void removeItemFromCart(long cartId, long itemId) throws ResourceNotFoundException;
}
