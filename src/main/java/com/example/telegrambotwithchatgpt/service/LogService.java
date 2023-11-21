package com.example.telegrambotwithchatgpt.service;

import com.example.telegrambotwithchatgpt.dto.request.LogRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.LogResponseDto;
import java.util.List;

public interface LogService {
    LogResponseDto save(LogRequestDto logRequestDto);

    List<LogResponseDto> getAll();
}
