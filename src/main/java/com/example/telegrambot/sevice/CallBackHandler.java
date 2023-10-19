package com.example.telegrambot.sevice;

import com.example.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service
@RequiredArgsConstructor
public class CallBackHandler {

    private final UserServiceTelegram userServiceTelegram;

    private boolean malfunctionFlag = false;

    public SendMessage callBack(Update update) {
        String text = update.getCallbackQuery().getData();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

         if (text.equals(UserCommand.MY_DATA.toString())) {
            sendMessage.setText(userServiceTelegram.getUserByNickname(update.getCallbackQuery().getMessage().getChat().getUserName()));
            return sendMessage;
        } else if (text.equals(UserCommand.UPDATE_MY_DATA.toString())) {
             sendMessage.setText("Введите свои новые данные. \nПожалуйста введите свое имя, фамилию и email через запятую и пробел. \nПример данных: \nВася, Пупкин, vasia@mail.ru");
            return  sendMessage;
        } else if (text.equals(UserCommand.ADD_MALFUNCTION.toString())) {
             malfunctionFlag = true;
             sendMessage.setText("Выберете неисправность");
            return sendMessage;
        } else if (text.equals(UserCommand.ADD_CAR.toString())) {
             sendMessage.setText("Введите номер и регион своего транспорта кириллицей через пробел. \nПример: а123рх 77");
             return sendMessage;
         } else if (text.equals(UserCommand.DELETE_CAR.toString())) {
             sendMessage.setText("Для удаления введите номер и регион своего транспорта кириллицей через пробел. \nПример: а123рх 77");
             return sendMessage;
         }


//        if (update.getCallbackQuery().getData().equals(UserCommand)) {
//            return "Добавляем пользователя. \n" +
//                    "Пожалуйста введите свой имя, фамилию и email через запятую и пробел. \nПример данных: \nВася, Пупкин, vasia@mail.ru";
//        }

        sendMessage.setText("Косяк");
        return sendMessage;
    }


    public boolean isMalfunctionFlag() {
        return malfunctionFlag;
    }

    public void setMalfunctionFlag(boolean malfunctionFlag) {
        this.malfunctionFlag = malfunctionFlag;
    }
}
