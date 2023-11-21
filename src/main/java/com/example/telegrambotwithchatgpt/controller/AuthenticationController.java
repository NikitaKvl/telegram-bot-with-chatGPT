package com.example.telegrambotwithchatgpt.controller;

import com.example.telegrambotwithchatgpt.dto.request.UserRequestDto;
import com.example.telegrambotwithchatgpt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(UserRequestDto userRequestDto, Model model) {
        try {
            authenticationService.register(userRequestDto);
        } catch (Exception e) {
            model.addAttribute("status", "Something went wrong while register new User");
            return "admin";
        }
        model.addAttribute("status", "User was successfully registered");
        return "admin";
    }
}
