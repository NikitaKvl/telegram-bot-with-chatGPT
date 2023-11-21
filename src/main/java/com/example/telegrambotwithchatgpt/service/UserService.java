package com.example.telegrambotwithchatgpt.service;

import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;
import com.example.telegrambotwithchatgpt.entity.User;
import java.util.List;

public interface UserService {
    User save(User user);

    List<UserResponseDto> getAll();
}
