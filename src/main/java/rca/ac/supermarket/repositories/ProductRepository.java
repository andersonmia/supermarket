package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
