package com.jokenponline.api.dto.match;

public record MatchPlaysResponseDTO(
        String playerOnePlay,

        String playerTwoPlay,

        String winnerUser
) {}
