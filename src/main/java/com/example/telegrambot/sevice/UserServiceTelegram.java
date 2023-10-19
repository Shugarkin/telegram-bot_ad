package com.example.telegrambot.sevice;

import com.example.telegrambot.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserServiceTelegram {

    User doUsers(User user);
    String getUserByNickname(String text);
}
