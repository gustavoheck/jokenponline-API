package com.jokenponline.service.matchmaking;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.api.dto.onlineMatch.OnlineMatchResponseDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.enums.MatchResult;
import com.jokenponline.domain.enums.Plays;
import com.jokenponline.infra.repository.MatchRepository;
import com.jokenponline.service.match.MatchHistoricService;
import org.springframework.stereotype.Service;

import java.util.Map;
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
        } else if ((playerOnePlay != null || playerTwoPlay == null) || (playerOnePlay == null || playerTwoPlay != null)) {
            savePlays(matchRequestDTO, username, matchId);
            return findResult(playingMatch, playerOnePlay, playerTwoPlay);
        } else {
            throw new RuntimeException("Both players already played, the match is already over!");
        }
        throw new RuntimeException("The plays were not found at database!");
    }

    private OnlineMatchResponseDTO findResult(Match match, String playerOnePlay, String playerTwoPlay) {
        Map<String, String> rules = Map.of(
                Plays.PAPER.getName(), Plays.STONE.getName(),
                Plays.STONE.getName(), Plays.SCISSORS.getName(),
                Plays.SCISSORS.getName(), Plays.PAPER.getName()
        );

        if (playerOnePlay.equals(playerTwoPlay)) {
            match.setMatchResult(MatchResult.TIE.getName());
            matchRepository.save(match);
            return new OnlineMatchResponseDTO(playerOnePlay, playerTwoPlay, null);
        }
        else if (rules.get(playerOnePlay).equals(rules.get(playerTwoPlay))) {
            match.setMatchResult(MatchResult.FINISHED.getName());
            match.setWinner(match.getPlayerOne());
            matchRepository.save(match);
            return new OnlineMatchResponseDTO(playerOnePlay, playerTwoPlay, match.getPlayerOne().getUsername());
        }
        match.setMatchResult(MatchResult.FINISHED.getName());
        match.setWinner(match.getPlayerTwo());
        matchRepository.save(match);
        return new OnlineMatchResponseDTO(playerOnePlay, playerTwoPlay, match.getPlayerTwo().getUsername());
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