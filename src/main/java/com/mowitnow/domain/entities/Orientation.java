package com.mowitnow.domain.entities;

public enum Orientation {
    N("NORTH"),
    S("SOUTH"),
    E("EAST"),
    W("WEST");

    private String label;

    Orientation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
