package com.example.telegrambot.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewMalfunctionDto {

    @Valid
    private NewCarDto carNumber;

    private NewMalfunction mal;
}
