package com.example.telegrambot.dto;

import com.example.telegrambot.model.AllMalfunction;
import com.example.telegrambot.model.MalfunctionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewMalfunction {

    private MalfunctionType type;

    private AllMalfunction malfunction;
}
