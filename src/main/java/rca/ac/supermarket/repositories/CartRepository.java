package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
}
