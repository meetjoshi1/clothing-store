package com.galvanize.clothingstore.serviceTest;

import com.galvanize.clothingstore.exceptions.DressShirtException;
import com.galvanize.clothingstore.exceptions.ShirtSizeException;
import com.galvanize.clothingstore.model.*;
import com.galvanize.clothingstore.repository.JacketRepository;
import com.galvanize.clothingstore.repository.ShirtRepository;
import com.galvanize.clothingstore.repository.ShoesRepository;
import com.galvanize.clothingstore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private JacketRepository jacketRepository;

    @Mock
    private ShoesRepository shoesRepository;

    @Mock
    private ShirtRepository shirtRepository;


    @InjectMocks
    private ProductService service;


    @Test
    public void whenAddJacket(){
        Jacket jacket = new Jacket(Season.WINTER.name(), "10", "Blue",
                "style",true, 1900l);
        when(jacketRepository.save(any())).thenReturn(jacket);
        Jacket actual = service.addJacket(jacket);
        assertEquals(jacket, actual);
        verify(jacketRepository).save(jacket);
    }

    @Test
    public void whenAddShoes(){
        Shoes shoes = new Shoes(8, "4", ShoeType.sandal, "MATERIAL", "NIKE", true, "BLUE", 80l);
        when(shoesRepository.save(any())).thenReturn(shoes);
        Shoes actual = service.addShoe(shoes);
        assertEquals(shoes, actual);
        verify(shoesRepository).save(shoes);
    }

    @Test
    public void whenAddShirt(){
        Shirt shirt = new Shirt(ShirtType.TEE.name(), 3, 5,
                "10","blue", true, 2000l);
        when(shirtRepository.save(any())).thenReturn(shirt);
        Shirt actual = service.addShirt(shirt);
        assertEquals(shirt, actual);
        verify(shirtRepository).save(shirt);
    }
    @Test
    public void whenAddShirt_TypeDress(){
        Shirt shirt = new Shirt(ShirtType.DRESS.name(), 0, 0,
                "10","blue", true, 2000l);

        DressShirtException exception =
                assertThrows(DressShirtException.class, ()->{
                    service.addShirt(shirt);
                });

        assertEquals("Please enter sleeve and neck measurements for Dress shirt!", exception.getMessage());


    }

    @Test
    public void whenAddShirt_TypeAnyOtherThanDress(){
        Shirt shirt1 = new Shirt(ShirtType.TEE.name(), 1, 1,
                "","blue", true, 2000l);

        ShirtSizeException exception =
                assertThrows(ShirtSizeException.class, ()->{
                    service.addShirt(shirt1);
                });

        assertEquals("Please enter size for shirt!", exception.getMessage());

        Shirt shirt2 = new Shirt(ShirtType.TEE.name(), 1, 1,
                "8","blue", true, 2000l);

        exception =
                assertThrows(ShirtSizeException.class, ()->{
                    service.addShirt(shirt2);
                });

        assertEquals("Please enter size for shirt!", exception.getMessage());


    }

}
