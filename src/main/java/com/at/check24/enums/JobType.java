package com.at.check24.enums;

public enum JobType {
    CRITICISM(0, "CRITICISM"),
    JOURNALIST(1, "JOURNALIST"),
    OTHER(2, "OTHER");
    private final int code;
    private final String name;

    JobType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}