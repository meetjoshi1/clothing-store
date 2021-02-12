package com.galvanize.clothingstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Shoes extends Product {

    private String height;
    private ShoeType type;
    private String material;
    private String brand;
    private Boolean laced;
    private Integer size;

    public Shoes(Integer size, String height, ShoeType type, String material, String brand, Boolean laced, String color, Long price) {
        this.size = size;
        this.height = height;
        this.type = type;
        this.price = price;
        this.color = color;
        this.material = material;
        this.brand = brand;
        this.laced = laced;
    }

    public Shoes() {
    }
}
