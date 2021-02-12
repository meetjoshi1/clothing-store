package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Shirt;
import com.galvanize.clothingstore.model.Shoes;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
import com.galvanize.clothingstore.repository.ShoesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private JacketRepository jacketRepository;
    private ShoesRepository shoesRepository;
    private ShirtRepository shirtRepository;

    public ProductService(JacketRepository jacketRepository, ShoesRepository shoesRepository, ShirtRepository shirtRepository){
        this.jacketRepository = jacketRepository;
        this.shoesRepository = shoesRepository;
        this.shirtRepository = shirtRepository;
    }

    public Jacket addJacket(Jacket jacket) {
        return jacketRepository.save(jacket);
    }

    public Shoes addShoe(Shoes shoes) {
        return shoesRepository.save(shoes);
    }


    public List<Shoes> getAllShoes() {
        return shoesRepository.findAll();
    }

    public Shirt addShirt(Shirt shirt) {
        return shirtRepository.save(shirt);
    }
}
