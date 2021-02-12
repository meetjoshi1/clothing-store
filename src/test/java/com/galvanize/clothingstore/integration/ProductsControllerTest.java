package com.galvanize.clothingstore.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
                .andExpect(jsonPath("$.adultSize").value("true"))
                .andExpect(jsonPath("$.price").value("1900"));

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
    public void whenAddShirtToStore() throws Exception {
        Shirt shirt = new Shirt(ShirtType.TEE.name(), 3, 5,
                "10","blue", true, 2000l);

        mockMvc
                .perform(post("/api/products/shirts")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(shirt)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("TEE"))
                .andExpect(jsonPath("$.sleeve").value("3"))
                .andExpect(jsonPath("$.neck").value("5"))
                .andExpect(jsonPath("$.size").value("10"))
                .andExpect(jsonPath("$.color").value("blue"))
                .andExpect(jsonPath("$.longSleeve").value("true"))
                .andExpect(jsonPath("$.price").value("2000"));

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

}
