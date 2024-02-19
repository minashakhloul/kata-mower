package com.mowitnow.domain.exception;

public class MowerOutsideBoundariesException extends RuntimeException {

    public MowerOutsideBoundariesException(String message) {
        super(message);
    }
}
