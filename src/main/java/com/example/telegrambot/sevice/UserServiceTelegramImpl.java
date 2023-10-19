package com.example.telegrambot.sevice;

import com.example.telegrambot.dao.UserRepository;
import com.example.telegrambot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceTelegramImpl implements UserServiceTelegram {

    private final UserRepository repository;

    private User createUsers(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public User doUsers(User user) {
        boolean answer = repository.existsByNickName(user.getNickName());
        User newUser;
        if (answer) {
            newUser = putUserById(user);
        } else newUser = createUsers(user);
        return newUser;
    }

    @Override
    public String getUserByNickname(String nickName) {
        return repository.findByNickName(nickName).toString();
    }


    private User putUserById(User user) {
        User userOld = repository.findByNickName(user.getNickName());
        if (user.getFirstName() != null && !user.getFirstName().isBlank()) userOld.setFirstName(user.getFirstName());
        if (user.getLastName() != null && !user.getLastName().isBlank()) userOld.setLastName(user.getLastName());
        if (user.getNickName() != null && !user.getNickName().isBlank()) userOld.setNickName(user.getNickName());
        return userOld;
    }
}
