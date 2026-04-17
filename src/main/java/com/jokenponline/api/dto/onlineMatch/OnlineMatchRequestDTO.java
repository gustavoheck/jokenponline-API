package com.jokenponline.api.dto.onlineMatch;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OnlineMatchRequestDTO(
        @NotNull
        long matchId,

        @NotNull
        @Min(value = 0, message = "You only can choose between 0 and 3 (Stone, Scissors and Stone)")
        @Max(value = 2, message = "You only can choose between 0 and 3 (Stone, Scissors and Stone)")
        int playerPlay
){}
