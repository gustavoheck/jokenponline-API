package com.jokenponline.service;

import com.jokenponline.entities.Match;
import com.jokenponline.entities.Users;
import com.jokenponline.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MatchMakingService {
    private final MatchHistoricService matchHistoricService;
    private final UsersService usersService;

    public MatchMakingService(MatchHistoricService matchHistoricService, UsersService usersService) {
        this.matchHistoricService = matchHistoricService;
        this.usersService = usersService;
    }

    public Match findMatch (Users host) {
        Users playerOne = host;
        playerOne.setSearchingMatch(true);
        Users playerTwo;
        do {
            playerOne = usersService.findRandomSearchingPlayer()
                    .orElseThrow(() -> new NotFoundException("Nobody was encountered to create a match!"));
            playerTwo = usersService.findRandomSearchingPlayer()
                    .orElseThrow(() -> new NotFoundException("Nobody was encountered to create a match!"));
        }
        while (playerOne.equals(playerTwo));
        return matchHistoricService.createMatch(new Match(playerOne, playerTwo));
    }

    public long enterMatch (Match matchToEnter) {
        return matchToEnter.getId();
    }

    public long matchmaking (Users host) {
        return enterMatch(findMatch(host));
    }
}
