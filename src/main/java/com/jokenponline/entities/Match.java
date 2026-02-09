package com.jokenponline.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private boolean online;

    @ManyToMany
    private Users playerOne;

    @Column(nullable = false)
    private String playerOnePlay;

    @ManyToMany
    private Users PlayerTwo;

    @Column(nullable = false)
    private String playerTwoPlay;

}
