package com.rentaride.carrental.controller;

import com.rentaride.carrental.cookie.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        try {
             Authentication authentication = authenticationManager.authenticate(
                             new UsernamePasswordAuthenticationToken(email, password));
             SecurityContextHolder.getContext().setAuthentication(authentication);

             request.getSession().setAttribute(
                     HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                     SecurityContextHolder.getContext()
             );

             return "redirect:/home";
        } catch (AuthenticationException e) {
             CookieService.setToastCookie(response, "showToast", true);
             return "redirect:/failedLogin";
        }
    }

    @GetMapping("/failedLogin")
    public String failedLogin(HttpServletRequest request, Model model) {
        return CookieService.resetToastCookie(
                request,
                model,
                "showToast",
                true,
                "login"
        );
    }
}
