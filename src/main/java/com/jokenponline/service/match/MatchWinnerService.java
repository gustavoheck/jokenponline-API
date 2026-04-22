package com.jokenponline.service.match;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.api.dto.onlineMatch.OnlineMatchResponseDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.enums.MatchResult;
import com.jokenponline.domain.enums.Plays;
import com.jokenponline.infra.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MatchWinnerService {

    private MatchHistoricService matchHistoricService;
    private MatchRepository matchRepository;
    private MatchPlayService matchPlayService;

    public MatchWinnerService(MatchHistoricService matchHistoricService, MatchRepository matchRepository,
                              MatchPlayService matchPlayService) {
        this.matchHistoricService = matchHistoricService;
        this.matchRepository = matchRepository;
        this.matchPlayService = matchPlayService;
    }

    public OnlineMatchResponseDTO matchWinner (OnlineMatchRequestDTO matchRequestDTO, String username, long matchId) {
        Match playingMatch = matchHistoricService.findById(matchId);
        String playerOnePlay = playingMatch.getPlayerOnePlay();
        String playerTwoPlay = playingMatch.getPlayerTwoPlay();

        if (playerOnePlay == null && playerTwoPlay == null)  {
            matchPlayService.savePlays(matchRequestDTO, username, matchId);
        } else if ((playerOnePlay != null || playerTwoPlay == null) || (playerOnePlay == null || playerTwoPlay != null)) {
            matchPlayService.savePlays(matchRequestDTO, username, matchId);
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
}