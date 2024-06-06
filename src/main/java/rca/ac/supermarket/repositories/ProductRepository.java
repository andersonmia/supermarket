package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
   Optional<Product> findByCode(String code);
}
