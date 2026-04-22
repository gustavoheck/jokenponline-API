package com.jokenponline.domain.enums;

public enum Plays {
    STONE("stone"),
    PAPER("paper"),
    SCISSORS("scissors");

    private String name;

    Plays(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
