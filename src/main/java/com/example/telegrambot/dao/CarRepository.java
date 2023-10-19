package com.example.telegrambot.dao;

import com.example.telegrambot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByUserId(long userId);

    Optional<Car> findByCarNumberAndCarRegion(String carNumber, int carRegion);

    void deleteByCarNumberAndCarRegion(String carNumber, int carRegion);

    boolean existsByCarNumberAndCarRegion(String carNumber, int carRegion);
}
