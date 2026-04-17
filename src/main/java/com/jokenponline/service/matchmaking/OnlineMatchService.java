package com.jokenponline.service.matchmaking;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.enums.Plays;
import com.jokenponline.service.match.MatchHistoricService;
import org.springframework.stereotype.Service;

@Service
public class JokenpoService {

    private MatchHistoricService matchHistoricService;

    public JokenpoService(MatchHistoricService matchHistoricService) {
        this.matchHistoricService = matchHistoricService;
    }

    public void matchWinner (OnlineMatchRequestDTO matchRequestDTO, String username, long matchId) {
        Match playingMatch = matchHistoricService.findById(matchId);
        String playerOnePlay = playingMatch.getPlayerOnePlay();
        String playerTwoPlay = playingMatch.getPlayerTwoPlay();

        if (playerOnePlay == null)  {
            savePlays(matchRequestDTO, username, matchId);
        } else if ((playerOnePlay != null) || playerTwoPlay != null) {

        } else {}
    }

    public void savePlays (OnlineMatchRequestDTO matchRequestDTO, String username, long matchId) {
        Match playingMatch = matchHistoricService.findById(matchId);
        if (playingMatch.getPlayerOne().getUsername().equals(username)) {
            playingMatch.setPlayerOnePlay(formatPlay(matchRequestDTO));
        }
        else if (playingMatch.getPlayerTwo().getUsername().equals(username)) {
            playingMatch.setPlayerTwoPlay(formatPlay(matchRequestDTO));
        } else {
            throw new RuntimeException("This play is not on the match!"); //criar uma exceção para isso
        }
    }

    public String formatPlay (OnlineMatchRequestDTO matchRequestDTO) {
        int play = matchRequestDTO.playerPlay();
        switch (play) {
            case 0 : {
                return Plays.STONE.getName();
            }
            case 1 : {
                return Plays.PAPER.getName();
            }
            case 2 : {
                return Plays.SCISSORS.getName();
            }
            default: {
                throw new RuntimeException("Invalid play! you can only choose Stone, Paper or Scissors"); //criar uma exceção para isso
            }
        }
    }
}