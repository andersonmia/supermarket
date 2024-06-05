package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.Purchased;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
}
