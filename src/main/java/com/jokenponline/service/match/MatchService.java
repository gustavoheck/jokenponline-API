package com.jokenponline.service.match;

import com.jokenponline.service.matchmaking.MatchMakingService;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final MatchMakingService matchMakingService;

    public MatchService(MatchMakingService matchMakingService) {
        this.matchMakingService = matchMakingService;
    }


}
