package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.DTO.CartDTO;
import rca.ac.supermarket.DTO.CartItemDTO;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;
import rca.ac.supermarket.models.Cart;
import rca.ac.supermarket.models.CartItem;
import rca.ac.supermarket.repositories.CartRepository;
import rca.ac.supermarket.repositories.CartItemRepository;
import rca.ac.supermarket.services.CartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartDTO addCart(CartDTO cartDTO) {
        Cart cart = cartDTO.toEntity();
        cartRepository.save(cart);
        return new CartDTO(cart.getCartItems().stream().map(this::convertToCartItemDTO).collect(Collectors.toList()));
    }

    @Override
    public CartDTO getCartById(long id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(id)));
        List<CartItemDTO> cartItems = cart.getCartItems().stream().map(this::convertToCartItemDTO).collect(Collectors.toList());
        return new CartDTO(cartItems);
    }

    @Override
    public CartDTO updateCart(long id, CartDTO cartDTO) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(id)));
        cart.setCartItems(cartDTO.getCartItems().stream().map(CartItemDTO::toEntity).collect(Collectors.toList()));
        cartRepository.save(cart);
        List<CartItemDTO> cartItems = cart.getCartItems().stream().map(this::convertToCartItemDTO).collect(Collectors.toList());
        return new CartDTO(cartItems);
    }

    @Override
    public void deleteCart(long id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(id)));
        cartRepository.delete(cart);
    }

    @Override
    public List<CartItemDTO> addItemToCart(long cartId, CartItemDTO cartItemDTO) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(cartId)));
        CartItem cartItem = cartItemDTO.toEntity();
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
        return cart.getCartItems().stream().map(this::convertToCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public void removeItemFromCart(long cartId, long itemId) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(cartId)));
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("CartItem", "id", String.valueOf(itemId)));
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    }

    private CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        return new CartItemDTO(cartItem.getProductCode(), cartItem.getName(), cartItem.getDescription(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getImageUrl());
    }
}
