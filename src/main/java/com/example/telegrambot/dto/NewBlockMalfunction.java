package com.example.telegrambot.dto;

import com.example.telegrambot.model.AllMalfunction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBlockMalfunction {

    @Positive
    private long userId;

    @NotBlank
    private AllMalfunction malfunction;
}
