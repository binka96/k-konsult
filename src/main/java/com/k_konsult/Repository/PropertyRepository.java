package com.k_konsult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.k_konsult.Entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Optional<Property> findByNameProperty(String nameProperty);
    List<Property> findAllByType(String type);
    List<Property> findAllByTypeAndCategory(String type, String category);
    List<Property> findAllByCategory(String category);

}
