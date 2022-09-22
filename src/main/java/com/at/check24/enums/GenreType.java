package com.at.check24.enums;

public enum GenreType {
    ACTION(0, "ACTION"),
    COMEDY(1, "COMEDY"),
    FANTASY(2, "FANTASY"),
    HORROR(3, "HORROR"),
    MYSTERY(4, "MYSTERY"),
    DRAMA(4, "DRAMA"),
    SCIENCE_FICTION(5, "SC_FI");

    private final int code;
    private final String name;

    GenreType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}