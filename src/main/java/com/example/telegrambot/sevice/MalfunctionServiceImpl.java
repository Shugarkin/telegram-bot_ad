package com.example.telegrambot.sevice;

import com.example.telegrambot.dao.CarRepository;
import com.example.telegrambot.dao.MalfunctionRepository;
import com.example.telegrambot.dao.UserRepository;
import com.example.telegrambot.dto.NewMalfunctionDto;
import com.example.telegrambot.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MalfunctionServiceImpl implements MalfunctionService{

    private final MalfunctionRepository malfunctionRepository;

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    private Object lock = new Object();

    private String carNumber;

    private AllMalfunction mal;

    public String handleMessage(Update update, String carNumberOrMal) {
        synchronized (lock) {
            if (mal == null) {
                mal = AllMalfunction.valueOf(carNumberOrMal);
            } else if (carNumber == null) {
                carNumber = carNumberOrMal;
            }

            if (carNumber != null || mal != null) {
                // Оба поля заполнены, сохраняем в репозиторий
                NewMalfunctionDto malfunction = NewMalfunctionDto.builder().carNumber(carNumber).mal(mal).build();
                //malfunctionRepository.save(malfunction);
                String s = saveMalfunction(update, malfunction);
                // Снимаем блокировку и возобновляем поток
                lock.notifyAll();
                return s;
            } else {
                // Ожидаем заполнения обоих полей
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return "Время ушло";
                }
            }
        }
        return "Пока не идет";
    }




    private String saveMalfunction(Update update, NewMalfunctionDto malfunctionDto) {
        Malfunctions malfunctions = new Malfunctions();
        String[] split = carNumber.split(" ");
        Car car = carRepository.findByCarNumberAndCarRegion(split[0], Integer.parseInt(split[1])).orElseThrow();

        User user = userRepository.findByNickName(update.getMessage().getFrom().getUserName());

        if (car.getUser().getId() == user.getId()) return "Вы отправляете запрос о неисправности сами себе";

        malfunctions.setMalfunction(malfunctionDto.getMal());
        malfunctions.setCreateOn(LocalDateTime.now());
        malfunctions.setCar(car);
        malfunctions.setHelper(user);

        //boolean check = blackListService.check(malfunction.getCar().getUser().getId(), malfunction.getHelper().getId());
        //if(check) return malfunction;
        return malfunctionRepository.save(malfunctions).toString();
    }

    @Override
    public SendMessage addMalfunction(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(handleMessage(update, text));
        return sendMessage;
    }
}
