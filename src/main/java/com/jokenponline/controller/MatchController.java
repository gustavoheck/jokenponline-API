package com.jokenponline.controller;

import com.jokenponline.service.MatchHistoricService;
import org.springframework.stereotype.Controller;

@Controller
public class MatchController {

    private final MatchHistoricService matchesService;

    public MatchController(MatchHistoricService matchesService) {
        this.matchesService = matchesService;
    }

    public void createMatch () {
        
    }
}
