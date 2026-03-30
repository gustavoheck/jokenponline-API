package com.jokenponline.controller;

import com.jokenponline.service.MatchHistoricService;
import com.jokenponline.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final MatchHistoricService matchesHistoricService;
    private final UserService userService;

    public MatchController(MatchHistoricService matchesHistoricService, UserService userService) {
        this.matchesHistoricService = matchesHistoricService;
        this.userService = userService;
    }
    public void createMatch () {
        
    }
    @PatchMapping("/home")
    public ResponseEntity<Void> turnSearching (@AuthenticationPrincipal UserDetails userDetails) {
        userService.settingPlayerToSearching(userDetails.getUsername());

        return ResponseEntity.noContent().build();
    }
}
