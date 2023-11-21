package com.example.telegrambotwithchatgpt.controller;

import com.example.telegrambotwithchatgpt.dto.request.RequestMessageDto;
import com.example.telegrambotwithchatgpt.dto.request.UserRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.LogResponseDto;
import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;
import com.example.telegrambotwithchatgpt.service.LogService;
import com.example.telegrambotwithchatgpt.service.UserService;
import com.example.telegrambotwithchatgpt.service.impl.TelegramBotService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    //Pageable deleted
    private final LogService logService;
    private final UserService userService;
    private final TelegramBotService telegramBotService;

    @GetMapping()
    public String admin(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "admin";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
        List<LogResponseDto> all = logService.getAll();
        model.addAttribute("logs", all);
        model.addAttribute("requestMessageDto", new RequestMessageDto());
        return "admin-logs";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserResponseDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @PostMapping("/send-message")
    public String sendMessageToUser(RequestMessageDto requestMessageDto, Model model) {
        try {
            telegramBotService.sendTextMessageToUser(requestMessageDto.getChatId(), requestMessageDto.getMessage());
        } catch (Exception e) {
            model.addAttribute("status", "Something went wrong while sending message");
        }
        model.addAttribute("status", "Message successfully delivered");
        model.addAttribute("logs", logService.getAll());
        return "admin-logs";
    }
}
