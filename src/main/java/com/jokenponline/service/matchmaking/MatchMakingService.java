package com.jokenponline.service.matchmaking;

import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.entities.User;
import com.jokenponline.domain.exceptions.NotFoundException;
import com.jokenponline.infra.repository.UserRepository;
import com.jokenponline.service.match.MatchHistoricService;
import com.jokenponline.service.auth.UserService;
import org.springframework.stereotype.Service;

@Service
public class MatchMakingService {
    private final UserRepository userRepository;
    private final MatchHistoricService matchHistoricService;
    private final UserService userService;

    public MatchMakingService(MatchHistoricService matchHistoricService,
                              UserService userService,
                              UserRepository userRepository) {
        this.matchHistoricService = matchHistoricService;
        this.userService = userService;
        this.userRepository = userRepository;
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

    public Match matchmaking (String hostUserName) {
        User host = userRepository.findByUsername(hostUserName)
                .orElseThrow(() -> new NotFoundException("The user was not encountered by its username"));
        return findMatch(host);
    }
}
