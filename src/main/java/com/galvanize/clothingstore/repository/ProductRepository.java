package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.Jacket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Jacket, Long> {
}
