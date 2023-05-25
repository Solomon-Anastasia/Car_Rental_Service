package com.rentaride.carrental.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/home")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    /*@GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }*/
}
