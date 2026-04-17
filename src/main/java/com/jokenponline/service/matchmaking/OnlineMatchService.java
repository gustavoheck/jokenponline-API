package com.jokenponline.service.matchmaking;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.api.dto.onlineMatch.OnlineMatchResponseDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.enums.Plays;
import com.jokenponline.infra.repository.MatchRepository;
import com.jokenponline.service.match.MatchHistoricService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OnlineMatchService {

    private MatchHistoricService matchHistoricService;
    private MatchRepository matchRepository;

    public OnlineMatchService(MatchHistoricService matchHistoricService, MatchRepository matchRepository) {
        this.matchHistoricService = matchHistoricService;
        this.matchRepository = matchRepository;
    }

    public OnlineMatchResponseDTO matchWinner (OnlineMatchRequestDTO matchRequestDTO, String username, long matchId) {
        Match playingMatch = matchHistoricService.findById(matchId);
        String playerOnePlay = playingMatch.getPlayerOnePlay();
        String playerTwoPlay = playingMatch.getPlayerTwoPlay();

        if (playerOnePlay == null && playerTwoPlay == null)  {
            savePlays(matchRequestDTO, username, matchId);
            return selectWinner(playerOnePlay, playerTwoPlay);
        } else if ((playerOnePlay != null) || playerTwoPlay != null) {
            savePlays(matchRequestDTO, username, matchId);
        } else {
            throw new RuntimeException("Both players selected a play, the match already is over!");
        }
    }

    private OnlineMatchResponseDTO selectWinner(String playerOnePlay, String playerTwoPlay) {
        if (playerOnePlay.equals(playerTwoPlay)) {
            return new OnlineMatchResponseDTO(playerOnePlay, playerTwoPlay, null);
        }

    }

    public void savePlays (OnlineMatchRequestDTO matchRequestDTO, String username, long matchId) {
        Match playingMatch = matchHistoricService.findById(matchId);
        if (playingMatch.getPlayerOne().getUsername().equals(username)) {
            playingMatch.setPlayerOnePlay(formatPlay(matchRequestDTO));
            matchRepository.save(playingMatch);
        }
        else if (playingMatch.getPlayerTwo().getUsername().equals(username)) {
            playingMatch.setPlayerTwoPlay(formatPlay(matchRequestDTO));
            matchRepository.save(playingMatch);
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