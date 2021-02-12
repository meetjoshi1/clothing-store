package com.galvanize.clothingstore.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Jacket extends Product {
    private String season;
    private String style;
    private boolean adultSize;
    private String size;


    public Jacket(String season, String size, String color, String style, boolean adultSize, long price) {
        this.season = season;
        this.size = size;
        this.style = style;
        this.adultSize = adultSize;
        this.price = price;
        this.color = color;
    }

    public Jacket() {}
}
