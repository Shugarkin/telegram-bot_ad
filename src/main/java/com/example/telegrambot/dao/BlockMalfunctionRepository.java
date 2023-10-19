package com.example.telegrambot.dao;

import com.example.telegrambot.model.AllMalfunction;
import com.example.telegrambot.model.BlockMalfunction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockMalfunctionRepository extends JpaRepository<BlockMalfunction, Long> {
    boolean existsByUserIdAndMalfunction(long userId, AllMalfunction malfunction);

    List<BlockMalfunction> findByUserId(long userId, Pageable page);
}
