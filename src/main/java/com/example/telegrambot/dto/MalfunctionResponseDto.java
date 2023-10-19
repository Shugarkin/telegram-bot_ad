package com.example.telegrambot.dto;

import com.example.telegrambot.model.AllMalfunction;
import com.example.telegrambot.model.MalfunctionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MalfunctionResponseDto {

    private LocalDateTime dateTime;

    private MalfunctionType type;

    private AllMalfunction malfunction;
}
