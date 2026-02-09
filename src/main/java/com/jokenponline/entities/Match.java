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

    private String playerOnePlay;

    @ManyToMany
    private Users playerTwo;

    private String playerTwoPlay;

    protected Match () {

    }

    public Match (Users playerOne, Users playerTwo) {
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

    public Users getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Users playerOne) {
        this.playerOne = playerOne;
    }

    public Users getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Users playerTwo) {
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
