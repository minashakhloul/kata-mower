package com.mowitnow.application.service;

import com.mowitnow.application.port.in.CreateLawnUseCase;
import com.mowitnow.domain.coordinate.Coordinate;
import com.mowitnow.domain.lawn.Lawn;

import static com.mowitnow.application.validator.DataValidator.INPUT_DELIMITER;

public class CreateLawnService implements CreateLawnUseCase {
    @Override
    public Lawn create(String input) {
        String[] mowerInputs = input.split(INPUT_DELIMITER);
        int coordinateX = Integer.parseInt(mowerInputs[0]);
        int coordinateY = Integer.parseInt(mowerInputs[1]);
        return Lawn.builder()
                .bottomLeft(Coordinate.builder().x(0).y(0).build())
                .upperRight(Coordinate.builder().x(coordinateX).y(coordinateY).build())
                .build();
    }
}
