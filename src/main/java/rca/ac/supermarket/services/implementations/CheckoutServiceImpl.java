package rca.ac.supermarket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.supermarket.models.Cart;
import rca.ac.supermarket.models.CartItem;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.models.User;
import rca.ac.supermarket.repositories.CartRepository;
import rca.ac.supermarket.repositories.PurchasedRepository;
import rca.ac.supermarket.repositories.UserRepository;
import rca.ac.supermarket.services.CheckoutService;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final PurchasedRepository purchasedRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, PurchasedRepository purchasedRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.purchasedRepository = purchasedRepository;
    }

    @Override
    public void checkoutCart(long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", String.valueOf(cartId)));

        User user = cart.getUser(); // Assuming Cart has a reference to the user

        List<Purchased> purchasedItems = cart.getCartItems().stream()
                .map(cartItem -> convertToPurchased(cartItem, user))
                .collect(Collectors.toList());

        purchasedRepository.saveAll(purchasedItems);

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    private Purchased convertToPurchased(CartItem cartItem, User user) {
        Purchased purchased = new Purchased();
        purchased.setProductCode(cartItem.getProductCode());
        purchased.setQuantity(cartItem.getQuantity());
        purchased.setTotal(cartItem.getPrice() * cartItem.getQuantity());
        purchased.setDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        purchased.setUser(user);
        return purchased;
    }
}
