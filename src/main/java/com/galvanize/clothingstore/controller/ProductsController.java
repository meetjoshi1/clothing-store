package com.galvanize.clothingstore.controller;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;
    public ProductsController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/jackets")
    @ResponseStatus(HttpStatus.CREATED)
    public Jacket addJacket(@RequestBody Jacket jacket){

        return productService.add(jacket);

    }
}
