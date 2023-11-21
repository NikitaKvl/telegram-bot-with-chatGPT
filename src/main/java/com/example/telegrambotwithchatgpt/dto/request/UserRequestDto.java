package com.example.telegrambotwithchatgpt.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotEmpty
    private String username;
    private String password;
    private String role;
}
