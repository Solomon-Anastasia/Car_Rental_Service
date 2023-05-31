package com.rentaride.carrental.controller;

import com.rentaride.carrental.cookie.CookieService;
import com.rentaride.carrental.model.dto.RegistrationRequestDto;
import com.rentaride.carrental.service.RegistrationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
       model.addAttribute("request", new RegistrationRequestDto());

       return "register";
    }

    @PostMapping("/registerSuccess")
    public String registerSuccessfully(RegistrationRequestDto request, HttpServletResponse response) {
        if (registrationService.register(request).equals("emailTaken")) {
            CookieService.setToastCookie(response, "showToast", true);
            return "redirect:/failedRegistration";
        }
        return "login";
    }

    @GetMapping("/failedRegistration")
    public String failedRegistration(HttpServletRequest request, Model model) {
        return CookieService.resetToastCookie(
                request,
                model,
                "showToast",
                true,
                "redirect:/register?emailTaken=true"
        );
    }
}
