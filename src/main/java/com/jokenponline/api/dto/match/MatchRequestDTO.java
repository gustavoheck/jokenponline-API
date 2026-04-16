package com.jokenponline.api.dto.match;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record MatchRequestDTO (

        @Min(value = 0, message = "You only can choose between 0 and 3 (Jo, Ken and Po)")
        @Max(value = 2, message = "You only can choose between 0 and 3 (Jo, Ken and Po)")
        int playerOnePlay,

        @Min(value = 0, message = "You only can choose between 0 and 3 (Jo, Ken and Po)")
        @Max(value = 2, message = "You only can choose between 0 and 3 (Jo, Ken and Po)")
        int playerTwoPlay
){}
