package com.mowitnow.domain.mower;

import lombok.Getter;

@Getter
public enum Pivot {
    LEFT('G'),
    RIGHT('D'),
    STEP_FORWARD('A');

    private final char label;

    Pivot(char label) {
        this.label = label;
    }

    public static Pivot getFromChar(String character) {
        return switch (character) {
            case "A" -> STEP_FORWARD;
            case "D" -> RIGHT;
            case "G" -> LEFT;
            default -> throw new IllegalArgumentException("Unknown input");
        };
    }
}
