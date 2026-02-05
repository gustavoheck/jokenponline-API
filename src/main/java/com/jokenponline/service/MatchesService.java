package com.jokenponline.service;

import com.jokenponline.entities.Match;
import com.jokenponline.exceptions.IdNotFoundException;
import com.jokenponline.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchesService {

    private final MatchRepository matchRepository;

    MatchesService (MatchRepository matchRepository) {
         this.matchRepository = matchRepository;
    }

    public Match createMatch (Match match) {
        return matchRepository.save(match);
    }

    public Match findById (Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }

}
