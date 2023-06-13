package com.rentaride.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
    @GetMapping("/emailSent")
    public String showEmailSentPage() {
        return "emailSent";
    }

    @PostMapping("/emailSent")
    public String emailSent() {
        return "redirect:/emailSent";
    }

    @GetMapping("/emailConfirmed")
    public String showEmailConfirmedPage() {
        return "emailConfirmed";
    }
}
