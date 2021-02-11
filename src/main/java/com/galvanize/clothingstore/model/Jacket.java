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
@NoArgsConstructor
@Getter
//@EqualsAndHashCode
@ToString
public class Jacket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String season;
    private String size;
    private String color;
    private String style;
    private boolean adultSize;
    private long price;


    public Jacket(String season, String size, String color, String style, boolean adultSize, long price) {
        this.season = season;
        this.size = size;
        this.color = color;
        this.style = style;
        this.adultSize = adultSize;
        this.price = price;
    }

}
