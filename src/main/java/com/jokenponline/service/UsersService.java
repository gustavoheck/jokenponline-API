package com.jokenponline.service;

import com.jokenponline.entities.Users;
import com.jokenponline.exceptions.NotFoundException;
import com.jokenponline.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsersService {

    private final Random random = new Random();
    private final UsersRepository usersRepository;

    public UsersService (UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById (Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));
    }

    public Users findByOnline (Boolean online) {
        return usersRepository.findByOnline(online)
                .orElseThrow(() -> new NotFoundException("Não a nenhum jogador" + ((online) ? "online." : "offline.")));
    }

    public List<Users> findBySearchingMatch (Boolean isSearching) {
        return usersRepository.findBySearchingMatch(isSearching);
    }

    public Optional<Users> findRandomSearchingPlayer () {

        List<Users> searchingUsers = findBySearchingMatch(true);

        int index = random.nextInt(searchingUsers.size());
        return Optional.of(searchingUsers.get(index));
    }
}
