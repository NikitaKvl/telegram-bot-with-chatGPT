package com.example.telegrambotwithchatgpt.dto.request;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n = 1;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}