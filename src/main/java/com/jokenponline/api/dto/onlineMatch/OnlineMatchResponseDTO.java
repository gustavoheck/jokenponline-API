package com.jokenponline.api.dto.onlineMatch;

public record OnlineMatchResponseDTO(
        String playerOnePlay,

        String playerTwoPlay,

        String winnerUser
) {}
