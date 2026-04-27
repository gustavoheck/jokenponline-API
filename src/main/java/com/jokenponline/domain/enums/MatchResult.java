package com.jokenponline.domain.enums;

public enum MatchResult {
    FINISHED("finished"),
    TIE("tie");

    private String name;

    MatchResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
