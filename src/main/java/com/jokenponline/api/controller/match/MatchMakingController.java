package com.jokenponline.api.controller;

import com.jokenponline.api.dto.match.MatchResponseDTO;
import com.jokenponline.api.dto.user.SearchingRequestDTO;
import com.jokenponline.service.match.MatchHistoricService;
import com.jokenponline.service.matchmaking.MatchMakingService;
import com.jokenponline.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<MatchResponseDTO> createMatch (@AuthenticationPrincipal UserDetails userDetails) {
        MatchResponseDTO newMatch = matchMakingService.matchmaking(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(newMatch); // fazer enviar o dto para o front
        // ele deve usar o ID para criar o link do website
    }

    @PatchMapping("/home")
    public ResponseEntity<Void> turnSearching (@AuthenticationPrincipal UserDetails userDetails,
                                               @Valid @RequestBody SearchingRequestDTO searchingRequestDTO) {

        userService.settingPlayerSearchingStatus(userDetails.getUsername(), searchingRequestDTO);

        return ResponseEntity.noContent().build();
    }
}
