package com.example.telegrambot.dao;

import com.example.telegrambot.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    boolean existsByUserIdAndBookedId(long userId, long bookedId);

    Optional<BlackList> findByUserIdAndBookedId(long userId, long bookedId);

    List<BlackList> findAllByUserId(long userId);
}
