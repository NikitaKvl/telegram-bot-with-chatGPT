package com.example.telegrambotwithchatgpt.dto.response;

import lombok.Data;

@Data
public class LogResponseDto {
    private String chatId;
    private String userName;
    private String requestMessage;
    private String responseMessage;
}
