package com.example.telegrambotwithchatgpt.dto.response;

import com.example.telegrambotwithchatgpt.dto.request.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private List<Choice> choices;
    private String chatId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}