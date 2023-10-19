package com.example.telegrambot.sevice;

import com.example.telegrambot.dao.CarRepository;
import com.example.telegrambot.dao.UserRepository;
import com.example.telegrambot.model.Car;
import com.example.telegrambot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    @Transactional
    @Override
    public String doCar(String userName, Car car) {
        boolean answer = carRepository.existsByCarNumberAndCarRegion(car.getCarNumber(), car.getCarRegion());
        User user = userRepository.findByNickName(userName);
        if (user == null) return "Вы все еще не зарегистрировались ((";
        if (answer) return deleteCar(car, user);
        return saveCar(car, user);
    }

    private String saveCar(Car car, User user) {
        if (carRepository.existsByCarNumberAndCarRegion(car.getCarNumber(), car.getCarRegion()))
            return "Такой транспорт уже существует. ";
        car.setUser(user);
        return carRepository.save(car).toString();
    }

    private String deleteCar(Car car, User user) {
        car.setUser(user);
        carRepository.delete(car);
        return "Транспорт удален";
    }
}
