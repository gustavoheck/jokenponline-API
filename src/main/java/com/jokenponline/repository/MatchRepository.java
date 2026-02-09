package com.jokenponline.repository;

import com.jokenponline.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByOnline (Boolean online);
    Optional<Match> findById (Long id);

    @Query("""
       SELECT m
       FROM Match m
       WHERE m.playerOneUsername = :username
       OR m.playerTwoUsername = :username
       """)
    Optional<Match> findWithUsername (String username);

    @Query("""
       SELECT m
       FROM Match m
       WHERE m.playerOneId = :id
       OR m.playerTwoId = :id
       """)
    Optional<Match> findWithUserId(Long id);
}
