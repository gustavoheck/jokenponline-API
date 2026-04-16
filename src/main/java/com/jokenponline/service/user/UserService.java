package com.jokenponline.service.user;

import com.jokenponline.api.dto.user.SearchingRequestDTO;
import com.jokenponline.domain.entities.User;
import com.jokenponline.domain.enums.SearchingStatus;
import com.jokenponline.domain.exceptions.NotFoundException;
import com.jokenponline.infra.repository.UserRepository;
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

    public List<User> findBySearchingMatch (String isSearching) {
        return userRepository.findBySearchingMatch(isSearching);
    }

    public Optional<User> findRandomSearchingPlayer () {

        List<User> searchingUsers = findBySearchingMatch(SearchingStatus.ONSEARCHING.getStatus());

        int index = random.nextInt(searchingUsers.size());
        return Optional.of(searchingUsers.get(index));
    }

    public void settingPlayerSearchingStatus (String username, SearchingRequestDTO searchingRequestDTO) {
        userRepository.findByUsername(username).ifPresent(user -> {
            user.setSearchingMatch(searchingRequestDTO.searchingStatus().getStatus());
            userRepository.save(user);
        });
    }
}
