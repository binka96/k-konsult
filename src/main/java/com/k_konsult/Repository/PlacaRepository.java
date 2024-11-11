package com.k_konsult.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.k_konsult.Entity.Place;
import com.k_konsult.Entity.Township;

public interface PlacaRepository  extends JpaRepository< Place , Integer>{
 Optional<Place> findByName(String name);
 List<Place> findAllByTownship(Township township);   
} 