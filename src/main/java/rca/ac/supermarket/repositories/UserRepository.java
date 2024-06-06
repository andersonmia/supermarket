package rca.ac.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.supermarket.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
