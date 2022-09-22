package com.at.check24.enums;

public enum RateType {
    ZERO(0,"ZERO"),
    ONE(1, "ONE"),
    TWO(2, "TWO"),
    THREE(3, "THREE"),
    FOUR(4, "FOUR"),
    FIVE(5, "FIVE");

    private final int code;
    private final String name;

    RateType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}