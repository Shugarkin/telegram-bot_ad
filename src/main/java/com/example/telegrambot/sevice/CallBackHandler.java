package com.example.telegrambot.sevice;

import com.example.telegrambot.model.AllMalfunction;
import com.example.telegrambot.model.MalfunctionType;
import com.example.telegrambot.model.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CallBackHandler {

    private final UserServiceTelegram userServiceTelegram;

    private final KeyboardHandler keyboardHandler;

    private final MalfunctionService malfunctionService;

    public SendMessage callBack(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        String text = update.getCallbackQuery().getData();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

         if (text.equals(UserCommand.MY_DATA.toString())) {
            sendMessage.setText(userServiceTelegram.getUserByNickname(update.getCallbackQuery().getMessage().getChat().getUserName()));
            return sendMessage;
        } else if (text.equals(UserCommand.UPDATE_MY_DATA.toString())) {
             sendMessage.setText("Введите свои новые данные. \nПожалуйста введите свое имя, фамилию и email через запятую и пробел. \nПример данных: \nВася, Пупкин, vasia@mail.ru");
            return  sendMessage;
        } else if (text.equals(UserCommand.ADD_MALFUNCTION.toString())) {
            return keyboardHandler.malfunctionTypeCommand(chatId);
        } else if (text.equals(UserCommand.ADD_CAR.toString())) {
             sendMessage.setText("Введите номер и регион своего транспорта кириллицей через запятую. \nПример: а123рх,77");
             return sendMessage;
         } else if (text.equals(UserCommand.DELETE_CAR.toString())) {
             sendMessage.setText("Для удаления введите номер и регион своего транспорта кириллицей через запятую. \nПример: а123рх,77");
             return sendMessage;
         } else if (text.equals(MalfunctionType.BOTTOM_MALFUNCTION.toString())) {
             return keyboardHandler.bottomMalfunctionCommand(chatId);
         } else if (text.equals(MalfunctionType.DOOR_MALFUNCTION)) {
             return keyboardHandler.doorMalfunctionCommand(chatId);
         } else if (text.equals(MalfunctionType.LIGHTING_FIXTURE_MALFUNCTION.toString())) {
             return keyboardHandler.lightingMalfunctionCommand(chatId);
         } else if (text.equals(MalfunctionType.WHEEL_FAILURES.toString())) {
             return keyboardHandler.wheelMalfunctionCommand(chatId);
         } else if (AllMalfunction.valueOf(text) != null) {
             return malfunctionService.addMalfunction(update, text);
         }


        sendMessage.setText("Косяк");
        return sendMessage;
    }

}
