package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
