package com.eat.diet.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
