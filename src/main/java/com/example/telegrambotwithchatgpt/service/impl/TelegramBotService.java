package com.example.telegrambotwithchatgpt.service.impl;

import com.example.telegrambotwithchatgpt.client.ChatGPTClient;
import com.example.telegrambotwithchatgpt.dto.request.ChatRequest;
import com.example.telegrambotwithchatgpt.dto.request.LogRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.ChatResponse;
import com.example.telegrambotwithchatgpt.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotService extends TelegramLongPollingBot {
    private final ChatGPTClient gptClient;
    private final LogService logService;
    @Value("${bot.name}")
    private String botName;
    @Value("${chat.model}")
    private String model;

    @Autowired
    public TelegramBotService(@Value("${bot.token}") String botToken,
                              ChatGPTClient gptClient, LogService logService) {
        super(botToken);
        this.gptClient = gptClient;
        this.logService = logService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            ChatRequest request = new ChatRequest(model, update.getMessage().getText());
            ChatResponse response = gptClient.getChatResponse(request);
            String chatId = update.getMessage().getChatId().toString();
            String responseFromGpt = response.getChoices().get(0).getMessage().getContent();
            try {
                sendTextMessage(chatId, responseFromGpt);
                saveLogs(update.getMessage().getText(),
                        update.getMessage().getChat().getUserName(), responseFromGpt, chatId);
            } catch (Exception e) {

                sendTextMessage(chatId, "Sorry, there was an error trying to get a reply. Try again");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void saveLogs(String requestMessage, String username, String response, String chatId) {
        LogRequestDto logRequestDto = LogRequestDto.builder()
                .chatId(chatId)
                .userName(username)
                .requestMessage(requestMessage)
                .responseMessage(response)
                .build();
        logService.save(logRequestDto);
    }

    public void sendTextMessage(String chatId, String text) {
        try {
            execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendTextMessageToUser(String chatId, String text) {
        try {
            execute(new SendMessage(chatId, text));
            saveLogs(null, null, text, chatId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
