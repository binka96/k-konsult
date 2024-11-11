package com.k_konsult.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k_konsult.Entity.Area;


public interface AreaRepository  extends JpaRepository<Area, Integer> {
    Optional<Area> findByName(String name);
}
