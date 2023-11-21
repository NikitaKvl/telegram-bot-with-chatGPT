package com.example.telegrambotwithchatgpt.client;

import com.example.telegrambotwithchatgpt.dto.request.ChatRequest;
import com.example.telegrambotwithchatgpt.dto.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatGPTClient {
    @Value("${chat.api.url}")
    private String apiUrl;
    @Value("${chat.api.key}")
    private String chatGptKey;

    public ChatResponse getChatResponse(ChatRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(chatGptKey);
        HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
        return restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ChatResponse.class).getBody();
    }
}
