package com.jokenponline.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean online;

    private boolean searchingMatch;

    private boolean inMatch;

    private long streak;

    private long coins;

    @ManyToMany
    private List<Match> matches;

    private boolean darkmode;

    public User() {

    }

    public User(String username, String password) {
        this.online = true;
        this.searchingMatch = false;
        this.username = username;
        this.password = password;
        this.coins = 0;
        this.streak = 0;
        this.darkmode = false;
        this.inMatch = false;
    }

    public long getId() {
        return id;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isSearchingMatch() {
        return searchingMatch;
    }

    public void setSearchingMatch(boolean searchingMatch) {
        this.searchingMatch = searchingMatch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getStreak() {
        return streak;
    }

    public void setStreak(long streak) {
        this.streak = streak;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public boolean isDarkmode() {
        return darkmode;
    }

    public void setDarkmode(boolean darkmode) {
        this.darkmode = darkmode;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
