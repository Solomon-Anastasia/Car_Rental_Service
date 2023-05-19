package com.rentaride.carrental.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentCarController {
    @GetMapping("/rent")
    public String showRentCarPage() {
        return "rent";
    }
}
