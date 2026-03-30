package com.jokenponline.service;

import com.jokenponline.entities.User;
import com.jokenponline.exceptions.NotFoundException;
import com.jokenponline.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final Random random = new Random();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id was not encountered"));
    }

    public User findByOnline (Boolean online) {
        return userRepository.findByOnline(online)
                .orElseThrow(() -> new NotFoundException("There isn't any user" + ((online) ? "online!" : "offline!")));
    }

    public List<User> findBySearchingMatch (Boolean isSearching) {
        return userRepository.findBySearchingMatch(isSearching);
    }

    public Optional<User> findRandomSearchingPlayer () {

        List<User> searchingUsers = findBySearchingMatch(true);

        int index = random.nextInt(searchingUsers.size());
        return Optional.of(searchingUsers.get(index));
    }

    public void settingPlayerToSearching (String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            user.setSearchingMatch(true);
            userRepository.save(user);
        });
    }
}
