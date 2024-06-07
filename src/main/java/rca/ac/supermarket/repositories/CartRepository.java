package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Cart;
import rca.ac.supermarket.models.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUser(User user);
}
