package com.example.telegrambot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlackListDto {

    private long userId;

    private long bookedId;
}
