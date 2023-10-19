package com.example.telegrambot.sevice;

import com.example.telegrambot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserServiceTelegram userService;

    private final KeyboardHandler keyboardHandler;

    @Override
    public SendMessage whatAreWeDoing(Update update) {
        String text = update.getMessage().getText();

        if (text.equals("Мое меню")) {
            return keyboardHandler.userInlineKeyboard(update.getMessage().getChatId());
        } else {
            return doEntity(update);
        }
    }

    private SendMessage doEntity(Update update) {
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String[] split = text.split(", ");

        if (split.length == 3) {
            doUser(update, split);
        }
        if (split.length == 2) {

        }
        sendMessage.setText("Ошибка с получением данных");
        return sendMessage;
    }

    private String doUser(Update update, String[] split) {
        try {
            User user = User.builder()
                    .nickName(update.getMessage().getFrom().getUserName())
                    .firstName(split[0])
                    .lastName(split[1])
                    .email(split[2])
                    .chatId(update.getMessage().getChatId())
                    .build();
            return userService.doUsers(user).toString();
        } catch (Exception e) {
            return "Ошибка с добавлением дынных пользователя";
        }
    }

}
