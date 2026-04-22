package com.jokenponline.api.controller.match;

import com.jokenponline.api.dto.match.MatchPlaysRequestDTO;
import com.jokenponline.api.dto.match.MatchPlaysResponseDTO;
import com.jokenponline.service.match.MatchWinnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/match")
public class OnlineMatchController {

    private final MatchWinnerService onlineMatchService;

    public OnlineMatchController (MatchWinnerService onlineMatchService) {
        this.onlineMatchService = onlineMatchService;
    }

    @PostMapping("/{matchID}")
    public ResponseEntity<MatchPlaysResponseDTO> matchWinner (@PathVariable("matchId") long matchId,
                                                              @Valid @RequestBody MatchPlaysRequestDTO onlineMatchRequestDTO,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        MatchPlaysResponseDTO matchPlaysResponseDTO =
                onlineMatchService.matchWinner(onlineMatchRequestDTO, userDetails.getUsername(), matchId);

        return ResponseEntity.status(HttpStatus.OK).body(matchPlaysResponseDTO);
    }
}
