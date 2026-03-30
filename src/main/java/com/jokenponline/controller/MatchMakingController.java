package com.jokenponline.controller;

import com.jokenponline.entities.Match;
import com.jokenponline.repository.MatchRepository;
import com.jokenponline.service.MatchHistoricService;
import com.jokenponline.service.MatchMakingService;
import com.jokenponline.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchMakingController {

    private final MatchHistoricService matchesHistoricService;
    private final UserService userService;
    private final MatchMakingService matchMakingService;

    public MatchMakingController(MatchHistoricService matchesHistoricService,
                                 UserService userService,
                                 MatchMakingService matchMakingService) {
        this.matchesHistoricService = matchesHistoricService;
        this.userService = userService;
        this.matchMakingService = matchMakingService;
    }

    @PostMapping("/home")
    public ResponseEntity<Match> createMatch (@AuthenticationPrincipal UserDetails userDetails) {
        Match newMatch = matchMakingService.matchmaking(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(newMatch);
    }

    @PatchMapping("/home")
    public ResponseEntity<Void> turnSearching (@AuthenticationPrincipal UserDetails userDetails) {
        userService.settingPlayerToSearching(userDetails.getUsername());

        return ResponseEntity.noContent().build();
    }
}
