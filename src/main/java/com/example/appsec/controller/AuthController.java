package com.example.appsec.controller;

import com.example.appsec.model.User;
import com.example.appsec.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {
        if (userService.emailExists(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email is already registered");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login?registerSuccess";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
