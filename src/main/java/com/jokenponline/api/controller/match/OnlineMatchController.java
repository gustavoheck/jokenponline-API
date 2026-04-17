package com.jokenponline.api.controller.match;

import com.jokenponline.api.dto.onlineMatch.OnlineMatchRequestDTO;
import com.jokenponline.api.dto.onlineMatch.OnlineMatchResponseDTO;
import com.jokenponline.service.matchmaking.OnlineMatchService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/match")
public class OnlineMatchController {

    private final OnlineMatchService onlineMatchService;

    public OnlineMatchController (OnlineMatchService onlineMatchService) {
        this.onlineMatchService = onlineMatchService;
    }

    @PostMapping("/{matchID}")
    public Optional<OnlineMatchResponseDTO> matchWinner (@PathVariable("matchId") long matchId,
                                                         @Valid @RequestBody OnlineMatchRequestDTO onlineMatchRequestDTO,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        onlineMatchService.matchWinner(onlineMatchRequestDTO, userDetails.getUsername(), matchId);
    }
}
