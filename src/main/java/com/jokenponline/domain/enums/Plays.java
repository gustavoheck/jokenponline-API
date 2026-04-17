package com.jokenponline.domain.enums;

public enum Plays {
    STONE("Pedra"),
    PAPER("Papel"),
    SCISSORS("Tesoura");

    private String name;

    Plays(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
