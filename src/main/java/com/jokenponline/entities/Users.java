package com.jokenponline.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String username;

    private String password;

    private long streak;

    private long coins;

    private List<Match> matches;

    private boolean darkmode;

}
