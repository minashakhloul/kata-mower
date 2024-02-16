package com.mowitnow.domain.entities;

public enum Pivot {
    LEFT('G'),
    RIGHT('D'),
    MOVE_FORWARD('A');

    private final char label;

    Pivot(char label) {
        this.label = label;
    }

    public char getLabel() {
        return label;
    }
}
