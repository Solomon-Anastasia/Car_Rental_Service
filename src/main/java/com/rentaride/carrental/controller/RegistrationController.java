package com.rentaride.carrental.controller;

import com.rentaride.carrental.cookie.CookieService;
import com.rentaride.carrental.model.token.ConfirmationToken;
import com.rentaride.carrental.service.ConfirmationTokenService;
import com.rentaride.carrental.model.dto.RegistrationRequestDto;
import com.rentaride.carrental.service.RegistrationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;

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
        return "redirect:/emailSent";
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

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.isPresent()) {
            ConfirmationToken tokenConfirmed = confirmationToken.get();
            if (tokenConfirmed.getConfirmedAt() != null) {
                return "redirect:/emailSender";
            }

            LocalDateTime expiredAt = tokenConfirmed.getExpiresAt();
            if (expiredAt.isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("Token expired");
            }

            registrationService.confirmToken(token);
            confirmationTokenService.setConfirmedAt(token);
        }

        return "redirect:/emailSender";
    }

    @GetMapping("/emailSender")
    public String sent() {
        return "redirect:/emailConfirmed";
    }
}
