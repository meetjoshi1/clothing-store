package com.galvanize.clothingstore.controller;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Shirt;
import com.galvanize.clothingstore.model.Shoes;
import com.galvanize.clothingstore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return productService.addJacket(jacket);

    }

    @PostMapping("/shoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Shoes addShoes(@RequestBody Shoes shoes){
        return productService.addShoe(shoes);

    }

    @GetMapping("/shoes")
    public List<Shoes> getShoes(){
        return productService.getAllShoes();

    }

    @PostMapping("/shirts")
    @ResponseStatus(HttpStatus.CREATED)
    public Shirt addShirt(@RequestBody Shirt shirt){
        return productService.addShirt(shirt);

    }

    @GetMapping("/jackets")
    public List<Jacket> getJackets(){
        return productService.getAllJackets();

    }

}
