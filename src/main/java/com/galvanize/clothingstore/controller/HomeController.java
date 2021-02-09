package com.galvanize.clothingstore.controller;

import com.galvanize.clothingstore.repository.HomeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeRepository homeRepository;

    public HomeController(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @GetMapping("home")
    public String home() {
        return homeRepository.findAll().get(0).getMessage();
    }
}
