package com.rentaride.carrental.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public class CookieService {
    public static void setToastCookie(HttpServletResponse response, String toastName, boolean toastValue) {
        Cookie cookie = new Cookie(toastName, String.valueOf(toastValue));
        cookie.setMaxAge(5);
        response.addCookie(cookie);
    }

    public static String resetToastCookie(HttpServletRequest request, Model model, String toastName, boolean toastValue, String returnPage) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(toastName)) {
                    model.addAttribute(toastName, toastValue);
                    cookie.setMaxAge(0);
                }
            }
        }
        return returnPage;
    }
}
