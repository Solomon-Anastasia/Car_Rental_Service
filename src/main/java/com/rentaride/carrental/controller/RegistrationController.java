package com.rentaride.carrental.controller;

import com.rentaride.carrental.cookie.CookieService;
import com.rentaride.carrental.registration.RegistrationRequest;
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
       model.addAttribute("request", new RegistrationRequest());

       return "register";
    }

    @PostMapping("/registerSuccess")
    public String registerSuccessfully(RegistrationRequest request, HttpServletResponse response) {
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

    //     TODO: TO DELETE
    /*@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }*/
}
