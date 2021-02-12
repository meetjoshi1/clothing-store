package com.galvanize.clothingstore.repository;

import com.galvanize.clothingstore.model.Jacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JacketRepository extends JpaRepository<Jacket, Long> {

}
