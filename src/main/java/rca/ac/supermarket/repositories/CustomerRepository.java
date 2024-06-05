package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByEmail(String email);
}
