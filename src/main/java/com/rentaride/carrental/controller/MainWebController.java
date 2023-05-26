package com.rentaride.carrental.controller;

import com.rentaride.carrental.model.appuser.AppUser;
import com.rentaride.carrental.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainWebController {
    AppUserRepository repository;

    @GetMapping("/home")
    public String showHomePage() {

//        TODO: Method of extracting session user
        /**/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AppUser appUser) {
            String username = appUser.getUsername();
            String firstName = appUser.getFirstName();
            System.out.println(username + " " + firstName);
        }
        /**/

        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
