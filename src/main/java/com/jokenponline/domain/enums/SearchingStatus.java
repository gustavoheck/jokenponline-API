package com.jokenponline.domain.enums;

public enum SearchingStatus {
    OFFSEARCHING("off_searching"),
    ONSEARCHING("on_searching"),
    INMATCH("in_match");

    private String status;

    SearchingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
