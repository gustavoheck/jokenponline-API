package com.jokenponline.service;

import com.jokenponline.entities.Match;
import com.jokenponline.exceptions.NotFoundException;
import com.jokenponline.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchesService {

    private final MatchRepository matchRepository;

    public MatchesService(MatchRepository matchRepository) {
         this.matchRepository = matchRepository;
    }

    public Match createMatch (Match match) {
        return matchRepository.save(match);
    }

    public Match findById (Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));
    }

    public Match findByUsername (String username) {
        return matchRepository.findWithUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public Match findByOnline (boolean online) {
        return matchRepository.findByOnline(online)
                .orElseThrow(() -> new NotFoundException("Não a nenhuma partida" + ((online) ? "online." : "offline.")));
    }

    public Match findByUserId (Long id) {
        return matchRepository.findWithUserId(id)
                .orElseThrow(() -> new NotFoundException("Não há usuário com este Id"));
    }

}
