package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Quantity;

public interface QuantityRepository extends JpaRepository<Quantity, Long>{
}
