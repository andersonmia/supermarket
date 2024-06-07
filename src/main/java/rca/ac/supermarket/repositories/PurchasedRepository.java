package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Purchased;
import rca.ac.supermarket.models.User;

import java.util.List;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
    List<Purchased> findByUser(User user);
}
