package com.k_konsult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.k_konsult.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional <User> findByUsername(String username);
}