package com.example.telegrambot.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarDto {

    private String carNumber;

    private int carRegion;

    //private List<Malfunctions> malfunctions;
}
