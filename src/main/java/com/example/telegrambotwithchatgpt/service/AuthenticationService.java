package com.example.telegrambotwithchatgpt.service;

import com.example.telegrambotwithchatgpt.dto.request.UserRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto register(UserRequestDto userRequestDto);
}
