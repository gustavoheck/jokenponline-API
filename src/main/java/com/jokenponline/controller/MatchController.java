package com.jokenponline.controller;

import com.jokenponline.service.MatchService;
import org.springframework.stereotype.Controller;

@Controller
public class MatchController {

    private final MatchService matchesService;

    public MatchController(MatchService matchesService) {
        this.matchesService = matchesService;
    }

    public void createMatch () {
        
    }
}
