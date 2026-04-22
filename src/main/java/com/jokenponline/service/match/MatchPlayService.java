package com.jokenponline.service.match;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.enums.Plays;
import com.jokenponline.infra.repository.MatchRepository;

public class MatchPlayService {

    private MatchHistoricService matchHistoricService;
    private MatchRepository matchRepository;

    public MatchPlayService(MatchHistoricService matchHistoricService, MatchRepository matchRepository) {
        this.matchHistoricService = matchHistoricService;
        this.matchRepository = matchRepository;
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
