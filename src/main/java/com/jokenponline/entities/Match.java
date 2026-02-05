package com.jokenponline.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private boolean online;

    @Column(nullable = false)
    private long playerOneId;

    @Column(nullable = false)
    private String playerOneUsername;

    @Column(nullable = false)
    private String playerOnePlay;

    @Column(nullable = false)
    private long playerTwoId;

    @Column(nullable = false)
    private String playerTwoUsername;

    @Column(nullable = false)
    private String playerTwoPlay;

}
