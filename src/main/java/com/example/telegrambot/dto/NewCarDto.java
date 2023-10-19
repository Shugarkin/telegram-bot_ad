package com.example.telegrambot.dto;

import com.example.telegrambot.valid.CarNumberValidAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@CarNumberValidAnnotation
public class NewCarDto {

    @NotBlank
    private String carNumber;

    @NotNull
    private int carRegion;
}
