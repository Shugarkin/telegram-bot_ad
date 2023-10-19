package com.example.telegrambot.sevice;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MalfunctionService {
    SendMessage addMalfunction(Update update, String text);
}
