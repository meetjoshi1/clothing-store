package com.galvanize.clothingstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected Long price;
    protected String color;
    private String type;
    private int sleeve;
    private int neck;
    private String size;
    private boolean longSleeve;

    public Shirt(String type, int sleeve, int neck, String size, String color, boolean longSleeve, long price) {
        this.type = type;
        this.sleeve = sleeve;
        this.neck = neck;
        this.size = size;
        this.color = color;
        this.longSleeve = longSleeve;
        this.price = price;
    }
}
