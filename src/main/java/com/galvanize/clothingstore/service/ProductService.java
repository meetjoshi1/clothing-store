package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Jacket add(Jacket jacket) {

        return productRepository.save(jacket);
    }
}
