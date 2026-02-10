package com.jokenponline.service;

import com.jokenponline.entities.Match;
import com.jokenponline.entities.Users;
import com.jokenponline.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MatchMakingService {
    private final MatchService matchService;
    private final UsersService usersService;

    public MatchMakingService(MatchService matchService, UsersService usersService) {
        this.matchService = matchService;
        this.usersService = usersService;
    }

    public Match findMatch (Users host) {
        Users playerOne = host;
        playerOne.setSearchingMatch(true);
        Users playerTwo;
        do {
            playerOne = usersService.findRandomSearchingPlayer()
                    .orElseThrow(() -> new NotFoundException("Nenhum usuário encontrado para criar partida"));
            playerTwo = usersService.findRandomSearchingPlayer()
                    .orElseThrow(() -> new NotFoundException("Nenhum usuário encontrado para criar partida"));
        }
        while (playerOne.equals(playerTwo));
        return matchService.createMatch(new Match(playerOne, playerTwo));
    }

    public long enterMatch (Match matchToEnter) {
        return matchToEnter.getId();
    }

    public long matchmaking (Users host) {
        return enterMatch(findMatch(host));
    }
}
