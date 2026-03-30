package com.jokenponline.service;

import com.jokenponline.entities.Match;
import com.jokenponline.entities.User;
import com.jokenponline.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MatchMakingService {
    private final MatchHistoricService matchHistoricService;
    private final UserService userService;

    public MatchMakingService(MatchHistoricService matchHistoricService, UserService userService) {
        this.matchHistoricService = matchHistoricService;
        this.userService = userService;
    }

    public Match findMatch (User host) {
        User playerTwo;
        do {
            playerTwo = userService.findRandomSearchingPlayer()
                    .orElseThrow(() -> new NotFoundException("Nobody was encountered to create a match!"));
        }
        while (host.equals(playerTwo));
        return matchHistoricService.createMatch(new Match(host, playerTwo));
    }

    public long matchmaking (User host) {
        return findMatch(host).getId();
    }
}
