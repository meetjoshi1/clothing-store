package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {

}
