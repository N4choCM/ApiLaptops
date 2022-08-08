package com.example.ApiLaptops.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hola")
    public String holaMundo(){
        return "¡Hola, mundo!";
    }
}
