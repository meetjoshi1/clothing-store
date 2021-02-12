package com.galvanize.clothingstore.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.clothingstore.model.Jacket;
import com.galvanize.clothingstore.model.Season;
import com.galvanize.clothingstore.model.ShoeType;
import com.galvanize.clothingstore.model.Shoes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
