package com.example.telegrambotwithchatgpt.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRequestDto {
    private String chatId;
    private String userName;
    private String requestMessage;
    private String responseMessage;
}
