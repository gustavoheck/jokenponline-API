package com.jokenponline.controller;

import com.jokenponline.service.MatchesService;
import org.springframework.stereotype.Controller;

@Controller
public class MatchController {

    private final MatchesService matchesService;

    public MatchController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    public void createMatch () {
        
    }
}
