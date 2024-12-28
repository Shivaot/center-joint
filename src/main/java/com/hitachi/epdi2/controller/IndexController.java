package com.hitachi.epdi2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Shiva Created on 29/12/24
 */
@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @RequestMapping("/home")
    String getCheckSheetDashBoard(Model model) {
        return "dashboard/dashboard";
    }

    @GetMapping("/login")
    public String customLogin(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }
}
