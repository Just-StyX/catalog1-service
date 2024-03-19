package com.jslpolarbookshop.catalogservice.controller;

import com.jslpolarbookshop.catalogservice.config.CustomPolarProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final CustomPolarProperties customPolarProperties;

    public HomeController(CustomPolarProperties customPolarProperties) {
        this.customPolarProperties = customPolarProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return customPolarProperties.getGreeting();
    }
}
