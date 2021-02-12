package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Product;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.Shoes;
import com.galvanize.clothingstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product add(Product product) {
        return (Product) productRepository.save(product);
    }
}
