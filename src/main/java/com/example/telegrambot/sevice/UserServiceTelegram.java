package com.example.telegrambot.sevice;

import com.example.telegrambot.model.User;

public interface UserServiceTelegram {

    User doUsers(User user);
    String getUserByNickname(String text);
}
