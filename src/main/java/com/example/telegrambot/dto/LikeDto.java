package com.example.telegrambot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDto {

    private long liker;

    private long likeOwner;
}
