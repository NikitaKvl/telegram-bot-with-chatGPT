package com.example.telegrambotwithchatgpt.dto.request;

import lombok.Data;

@Data
public class RequestMessageDto {
    private String chatId;
    private String message;
}
