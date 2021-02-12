package com.galvanize.clothingstore.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.exceptions.DressShirtException;
import com.galvanize.clothingstore.exceptions.ShirtSizeException;
import com.galvanize.clothingstore.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;




    @Test
    public void whenAddJacketToStore() throws Exception {
        Jacket jacket = new Jacket(Season.WINTER.name(), "10", "Blue",
                "style", true, 1900l);

        mockMvc
                .perform(post("/api/products/jackets")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(jacket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.style").value("style"))
                .andExpect(jsonPath("$.size").value("10"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.adultSize").value(true))
                .andExpect(jsonPath("$.price").value(1900L));

    }

    @Test
    public void whenAddJShoesToStore() throws Exception {
        Shoes shoes = new Shoes(8, "4", ShoeType.sandal, "MATERIAL", "NIKE", true, "BLUE", 80l);

        mockMvc
                .perform(post("/api/products/shoes")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shoes)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size").value("8"))
                .andExpect(jsonPath("$.height").value("4"))
                .andExpect(jsonPath("$.type").value(ShoeType.sandal.name()))
                .andExpect(jsonPath("$.material").value("MATERIAL"))
                .andExpect(jsonPath("$.brand").value("NIKE"))
                .andExpect(jsonPath("$.laced").value("true"))
                .andExpect(jsonPath("$.color").value("BLUE"))
                .andExpect(jsonPath("$.price").value("80"));

    }

    @Test
    public void getShoesFromTheStore() throws Exception {
        Shoes shoes = new Shoes(8, "4", ShoeType.sandal, "MATERIAL", "NIKE", true, "BLUE", 80l);
        mockMvc
                .perform(post("/api/products/shoes")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shoes)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/products/shoes")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].size").value("8"))
                .andExpect(jsonPath("[0].height").value("4"))
                .andExpect(jsonPath("[0].type").value(ShoeType.sandal.name()))
                .andExpect(jsonPath("[0].material").value("MATERIAL"))
                .andExpect(jsonPath("[0].brand").value("NIKE"))
                .andExpect(jsonPath("[0].laced").value("true"))
                .andExpect(jsonPath("[0].color").value("BLUE"))
                .andExpect(jsonPath("[0].price").value("80"));
    }

    @Test
    public void whenAddShirtToStore() throws Exception {
        Shirt shirt = new Shirt(ShirtType.TEE.name(), 0, 0,
                "10","blue", true, 2000l);

        mockMvc
                .perform(post("/api/products/shirts")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("TEE"))
                .andExpect(jsonPath("$.sleeve").value("0"))
                .andExpect(jsonPath("$.neck").value("0"))
                .andExpect(jsonPath("$.size").value("10"))
                .andExpect(jsonPath("$.color").value("blue"))
                .andExpect(jsonPath("$.longSleeve").value(true))
                .andExpect(jsonPath("$.price").value("2000"));

    }

    @Test
    public void whenAddShirtToStore_TypeDress() throws Exception {
        Shirt shirt = new Shirt(ShirtType.DRESS.name(),0,0,
                "10","blue", true, 2000l);

        mockMvc
                .perform(post("/api/products/shirts")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                instanceof DressShirtException))
                .andExpect(result -> assertEquals("Please enter sleeve and neck measurements for Dress shirt!",
                        result.getResolvedException().getMessage()));


    }

    @Test
    public void whenAddShirtToStore_TypeAnyOtherThanDress() throws Exception {
        Shirt shirt = new Shirt(ShirtType.TEE.name(),1,1,
                "","blue", true, 2000l);

        mockMvc
                .perform(post("/api/products/shirts")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof ShirtSizeException))
                .andExpect(result -> assertEquals("Please enter size for shirt!",
                        result.getResolvedException().getMessage()));
    }

    @Test
    public void whenGetShirt() throws Exception {
        Shirt shirt = new Shirt(ShirtType.TEE.name(), 0, 0,
                "10","blue", true, 2000l);

        mockMvc
                .perform(post("/api/products/shirts")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isCreated());


        mockMvc
                .perform(get("/api/products/shirts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("[0].type").value("TEE"))
                .andExpect(jsonPath("[0].sleeve").value("0"))
                .andExpect(jsonPath("[0].neck").value("0"))
                .andExpect(jsonPath("[0].size").value("10"))
                .andExpect(jsonPath("[0].color").value("blue"))
                .andExpect(jsonPath("[0].longSleeve").value("true"))
                .andExpect(jsonPath("[0].price").value("2000"));




    }

    @Test
    public void getJacketsFromTheStore() throws Exception {
        Jacket jacket = new Jacket(Season.WINTER.name(), "10", "Blue",
                "style", true, 1900L);

        mockMvc.perform(post("/api/products/jackets")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(jacket)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/products/jackets")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].season").value(Season.WINTER.name()))
                .andExpect(jsonPath("[0].size").value("10"))
                .andExpect(jsonPath("[0].color").value("Blue"))
                .andExpect(jsonPath("[0].style").value("style"))
                .andExpect(jsonPath("[0].adultSize").value(true))
                .andExpect(jsonPath("[0].price").value(1900L));
    }


    @Test
    public void whenDeleteProduct_Jacket() throws Exception {
        Jacket jacket = new Jacket(Season.WINTER.name(), "10", "Blue",
                "style", true, 1900L);

        MvcResult mvcResult = mockMvc.perform(post("/api/products/jackets")
                .contentType((MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(jacket)))
                .andExpect(status().isCreated())
                .andReturn();

        Jacket postResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Jacket.class);

        mockMvc.perform(delete("/api/products/jacket/"+postResponse.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/jackets")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));



    }

    @Test
    public void whenDeleteProduct_Shirt() throws Exception {
        Shirt shirt = new Shirt(ShirtType.TEE.name(), 0, 0,
                "10","blue", true, 2000l);

        MvcResult mvcResult = mockMvc.perform(post("/api/products/shirts")
                .contentType((MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isCreated())
                .andReturn();

        Shirt postResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Shirt.class);

        mockMvc.perform(delete("/api/products/shirt/"+postResponse.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/shirts")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));



    }

    @Test
    public void whenDeleteProduct_Shoes() throws Exception {
        Shoes shoes = new Shoes(8, "4", ShoeType.sandal, "MATERIAL", "NIKE", true, "BLUE", 80l);

        MvcResult mvcResult = mockMvc.perform(post("/api/products/shoes")
                .contentType((MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(shoes)))
                .andExpect(status().isCreated())
                .andReturn();

        Shoes postResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Shoes.class);

        mockMvc.perform(delete("/api/products/shoes/"+postResponse.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/shoes")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));



    }
}
