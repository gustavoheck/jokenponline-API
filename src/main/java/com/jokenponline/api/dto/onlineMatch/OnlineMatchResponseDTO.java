package com.jokenponline.api.dto.onlineMatch;

public record OnlineMatchResponseDTO(
        int playerOnePlay,

        int playerTwoPlay,

        String winnerUser
) {}
