package com.galvanize.clothingstore.serviceTest;

import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.repository.ProductRepository;
import com.galvanize.clothingstore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService service;


    @Test
    public void whenAddJacket(){
        Jacket jacket = new Jacket(Season.WINTER.name(), "10", "Blue",
                "style",true, 1900l);
        when(productRepository.save(any())).thenReturn(jacket);
        Jacket actual = service.add(jacket);
        assertEquals(jacket, actual);
        verify(productRepository).save(jacket);
    }

}
