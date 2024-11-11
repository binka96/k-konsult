package com.k_konsult.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k_konsult.Entity.Area;
import com.k_konsult.Entity.Township;

public interface TownshipRepository  extends JpaRepository<Township, Integer>{

    Optional<Township> findByName(String name);
    List<Township> findAllByArea(Area area);
} 