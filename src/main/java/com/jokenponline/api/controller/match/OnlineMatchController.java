package com.jokenponline.api.controller.match;

import com.jokenponline.api.dto.match.MatchPlaysRequestDTO;
import com.jokenponline.api.dto.match.MatchPlaysResponseDTO;
import com.jokenponline.service.match.MatchWinnerService;
import jakarta.validation.Valid;
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
    public Optional<MatchPlaysResponseDTO> matchWinner (@PathVariable("matchId") long matchId,
                                                        @Valid @RequestBody MatchPlaysRequestDTO onlineMatchRequestDTO,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        onlineMatchService.matchWinner(onlineMatchRequestDTO, userDetails.getUsername(), matchId);
    }
}
