package com.jokenponline.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private boolean online;

    @OneToMany
    private User playerOne;

    private String playerOnePlay;

    @OneToMany
    private User playerTwo;

    private String playerTwoPlay;

    protected Match () {

    }

    public Match (User playerOne, User playerTwo) {
        this.online = true;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public User getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(User playerOne) {
        this.playerOne = playerOne;
    }

    public User getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(User playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getPlayerOnePlay() {
        return playerOnePlay;
    }

    public void setPlayerOnePlay(String playerOnePlay) {
        this.playerOnePlay = playerOnePlay;
    }

    public String getPlayerTwoPlay() {
        return playerTwoPlay;
    }

    public void setPlayerTwoPlay(String playerTwoPlay) {
        this.playerTwoPlay = playerTwoPlay;
    }
}
