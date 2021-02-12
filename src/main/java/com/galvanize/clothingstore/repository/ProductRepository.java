package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {
}
