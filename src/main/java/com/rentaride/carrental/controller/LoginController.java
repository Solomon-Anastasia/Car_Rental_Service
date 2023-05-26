package com.rentaride.carrental.controller;

import com.rentaride.carrental.cookie.CookieService;
import com.rentaride.carrental.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {

        if (loginService.isValidCredentials(email, password)) {
            return "redirect:/home";
        }

        CookieService.setToastCookie( response, "showToast", true);
        return "redirect:/failedLogin";
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
