package com.jokenponline.service.match;

import com.jokenponline.api.dto.match.MatchResponseDTO;
import com.jokenponline.domain.entities.Match;
import com.jokenponline.domain.exceptions.NotFoundException;
import com.jokenponline.infra.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchHistoricService {
    private final MatchRepository matchRepository;

    public MatchHistoricService(MatchRepository matchRepository) {
         this.matchRepository = matchRepository;
    }

    public MatchResponseDTO createMatch (Match match) {
        matchRepository.save(match);
        return new MatchResponseDTO(match.getId(),
                match.getPlayerOne().getUsername(),
                match.getPlayerTwo().getUsername());
    }

    public Match findById (Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id not encountered!"));
    }

    public Match findByUsername (String username) {
        return matchRepository.findWithUsername(username)
                .orElseThrow(() -> new NotFoundException("User not encountered!"));
    }

    public Match findByOnline (boolean online) {
        return matchRepository.findByOnline(online)
                .orElseThrow(() -> new NotFoundException("There isn't any match" + ((online) ? "online!" : "offline!")));
    }

    public Match findByUserId (Long id) {
        return matchRepository.findWithUserId(id)
                .orElseThrow(() -> new NotFoundException("There isn't any user with this id!"));
    }
}
