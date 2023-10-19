package com.example.telegrambot.sevice;

import com.example.telegrambot.model.AllMalfunction;
import com.example.telegrambot.model.Bot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final Bot bot;

    private final CallBackHandler callBackHandler;

    private final KeyboardHandler messageHandler;

    private final MessageService messageService;


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if (update.getMessage().getText().equals("/start")) {
                    sendMessageHello(update);
                    return;
                }
                try {
                    execute(messageService.whatAreWeDoing(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if(update.hasCallbackQuery()){
            try {
                execute(callBackHandler.callBack(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessageHello(Update update) {
        try {
            execute(messageHandler.sendKeyBoardMessage(update.getMessage().getChatId()));
            execute(new SendMessage(update.getMessage().getChatId().toString(),
                    "Приветствую! \nДля использования бота сначала нужно добавить свои данные. " +
                            "\nПожалуйста введите свое имя, фамилию и email через пробел. \nПример данных: \nВася Пупкин vasia@mail.ru"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return bot.getBotName();
    }

    @Override
    public String getBotToken() {
        return bot.getBotToken();
    }
}
