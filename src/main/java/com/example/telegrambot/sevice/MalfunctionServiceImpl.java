package com.example.telegrambot.sevice;

import com.example.telegrambot.dao.CarRepository;
import com.example.telegrambot.dao.MalfunctionRepository;
import com.example.telegrambot.dao.UserRepository;
import com.example.telegrambot.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MalfunctionServiceImpl implements MalfunctionService{

    private final MalfunctionRepository malfunctionRepository;

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    //временное решение. потом в бд
    private Map<String, AllMalfunction> map = new HashMap<>();


    public String handleMessage(String userName, String carNumberOrMal) {

        boolean b = userRepository.existsByNickName(userName);
        if (!b) return "Зарегистрируйтесь пожалуйста";

        AllMalfunction mal = map.get(userName);
        if (mal == null) {
            map.put(userName, AllMalfunction.valueOf(carNumberOrMal));
            return "Теперь введите номер машины через пробел";
        } else {
            String s = saveMalfunction(userName, mal, carNumberOrMal);
            map.remove(userName);
            return s;
        }
        //return "Спасибо большое, что делаете мир чуть лучше! ";

    }

    public String saveMalfunction(String userName, AllMalfunction mal, String carNumber) {
        Malfunctions malfunctions = new Malfunctions();
        String[] split = carNumber.split(" ");
        Car car = carRepository.findByCarNumberAndCarRegion(split[0], Integer.parseInt(split[1])).orElse(new Car());
        if (car.getId() == 0) return "Спасибо большое!!!";
        User user = userRepository.findByNickName(userName);

        if (car.getUser().getId() == user.getId()) return "Вы отправляете запрос о неисправности сами себе";

        malfunctions.setMalfunction(mal);
        malfunctions.setCreateOn(LocalDateTime.now());
        malfunctions.setCar(car);
        malfunctions.setHelper(user);

        //boolean check = blackListService.check(malfunction.getCar().getUser().getId(), malfunction.getHelper().getId());
        //if(check) return malfunction;
        return malfunctionRepository.save(malfunctions) + "\nСпасибо большое, что делаете мир чуть лучше! ";
    }


    @Override
    public SendMessage addMalfunction(Update update, String text) {
        String userName;
        SendMessage sendMessage = new SendMessage();
        if (update.getMessage() == null) {
            userName = update.getCallbackQuery().getFrom().getUserName();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }
        else  {
            sendMessage.setChatId(update.getMessage().getChatId());
            userName = update.getMessage().getFrom().getUserName();
        }
        sendMessage.setText(handleMessage(userName, text));
        return sendMessage;
    }
}

