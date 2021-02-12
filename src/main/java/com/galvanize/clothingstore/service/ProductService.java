package com.galvanize.clothingstore.service;

import com.galvanize.clothingstore.exceptions.DressShirtException;
import com.galvanize.clothingstore.exceptions.ShirtSizeException;
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
        if(shirt.getType().equals("DRESS") && ((shirt.getNeck()<1 || shirt.getSleeve()<1) || !shirt.getSize().isEmpty()) )
            throw new DressShirtException("Please enter sleeve and neck measurements for Dress shirt!");

        if(!shirt.getType().equals("DRESS") && ((shirt.getSize().isEmpty()) || (shirt.getNeck()>0 || shirt.getSleeve()>0)))
            throw new ShirtSizeException("Please enter size for shirt!");

        return shirtRepository.save(shirt);
    }

    public List<Shirt> getAllShirts() {
        return shirtRepository.findAll();
    }

    public List<Jacket> getAllJackets() {
        return jacketRepository.findAll();
    }

    public void deleteProduct(String product, Long productId) {
        if(product.equals("jacket")){
           Jacket jacket = jacketRepository.getOne(productId);
           if(jacket!=null)
                jacketRepository.delete(jacket);
        }else if(product.equals("shirt")){
            Shirt shirt = shirtRepository.getOne(productId);
            if(shirt!=null)
                shirtRepository.delete(shirt);
        }else if(product.equals("shoes")){
            Shoes shoes = shoesRepository.getOne(productId);
            if(shoes!=null)
                shoesRepository.delete(shoes);
        }
    }
}
