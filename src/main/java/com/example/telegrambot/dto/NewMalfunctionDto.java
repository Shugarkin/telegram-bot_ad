package com.example.telegrambot.dto;

import com.example.telegrambot.model.AllMalfunction;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewMalfunctionDto {

 //   @Valid
    private String carNumber;

    private AllMalfunction mal;
}
