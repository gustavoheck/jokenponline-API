package com.jokenponline.controller;

import com.jokenponline.service.MatchHistoricService;
import com.jokenponline.service.MatchMakingService;
import com.jokenponline.service.UserService;
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
    public void createMatch (@AuthenticationPrincipal UserDetails userDetails) {
        matchMakingService.matchmaking(userDetails.getUsername());
    }

    @PatchMapping("/home")
    public ResponseEntity<Void> turnSearching (@AuthenticationPrincipal UserDetails userDetails) {
        userService.settingPlayerToSearching(userDetails.getUsername());

        return ResponseEntity.noContent().build();
    }
}
