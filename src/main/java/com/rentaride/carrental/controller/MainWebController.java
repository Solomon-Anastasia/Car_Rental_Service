package com.rentaride.carrental.controller;

import com.rentaride.carrental.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainWebController {
    AppUserRepository repository;

    @GetMapping("/home")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
