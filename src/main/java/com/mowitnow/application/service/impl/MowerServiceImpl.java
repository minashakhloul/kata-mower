package com.mowitnow.application.service.impl;

import com.mowitnow.application.service.MowerService;
import com.mowitnow.domain.coordinate.Coordinate;
import com.mowitnow.domain.exception.MowerOutsideBoundariesException;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Orientation;
import com.mowitnow.domain.mower.Pivot;

import java.util.List;

import static com.mowitnow.application.validator.DataValidator.INPUT_DELIMITER;

public class MowerServiceImpl implements MowerService {

    @Override
    public Mower create(String input) {
        String[] mowerInputs = input.split(INPUT_DELIMITER);
        int x = Integer.parseInt(mowerInputs[0]);
        int y = Integer.parseInt(mowerInputs[1]);

        return Mower.builder()
                .orientation(Orientation.valueOf(mowerInputs[2]))
                .coordinate(Coordinate.builder().x(x).y(y).build())
                .build();
    }

    @Override
    public Mower applyInstructions(Lawn lawn, Mower mower, List<Pivot> instructions) {
        if (!isValidPosition(mower.getCoordinate(), lawn)) {
            throw new MowerOutsideBoundariesException("Mower is outside of lawn boundaries !");
        }
        return instructions
                .stream()
                .reduce(mower,
                        (mowerAfter, instruction) -> this.move(mowerAfter, instruction, lawn),
                        ((mower1, mower2) -> mower2)
                );
    }

    public Mower move(Mower mower, Pivot instruction, Lawn lawn) {
        return switch (instruction) {
            case STEP_FORWARD -> stepForward(mower, lawn);
            case LEFT -> changeOrientationTo(mower, Pivot.LEFT);
            case RIGHT -> changeOrientationTo(mower, Pivot.RIGHT);
        };
    }

    private Mower stepForward(Mower mower, Lawn lawn) {
        Coordinate newCoordinates = generateNewCoordinates(mower);
        if (isValidPosition(newCoordinates, lawn)) {
            return Mower.builder()
                    .orientation(mower.getOrientation())
                    .coordinate(newCoordinates)
                    .build();
        }
        return mower;
    }

    private Mower changeOrientationTo(Mower mower, Pivot pivot) {
        Orientation newOrientation = null;
        if (Pivot.LEFT.equals(pivot)) {
            switch (mower.getOrientation()) {
                case N -> newOrientation = Orientation.W;
                case E -> newOrientation = Orientation.N;
                case S -> newOrientation = Orientation.E;
                case W -> newOrientation = Orientation.S;
            }
        } else if (Pivot.RIGHT.equals(pivot)) {
            switch (mower.getOrientation()) {
                case N -> newOrientation = Orientation.E;
                case E -> newOrientation = Orientation.S;
                case S -> newOrientation = Orientation.W;
                case W -> newOrientation = Orientation.N;
            }
        }

        return Mower.builder()
                .orientation(newOrientation)
                .coordinate(mower.getCoordinate())
                .build();
    }

    private Coordinate generateNewCoordinates(Mower mower) {
        int x = mower.getCoordinate().getX();
        int y = mower.getCoordinate().getY();
        return switch (mower.getOrientation()) {
            case N -> buildCoordinates(x, y + 1);
            case S -> buildCoordinates(x, y - 1);
            case E -> buildCoordinates(x + 1, y);
            case W -> buildCoordinates(x - 1, y);

        };
    }

    private Coordinate buildCoordinates(int x, int y) {
        return Coordinate.builder()
                .x(x)
                .y(y)
                .build();
    }

    private boolean isValidPosition(Coordinate mowerCoordinates, Lawn lawn) {
        return mowerCoordinates.getX() >= lawn.getBottomLeft().getX() &&
                mowerCoordinates.getY() >= lawn.getBottomLeft().getY() &&
                mowerCoordinates.getX() <= lawn.getUpperRight().getX() &&
                mowerCoordinates.getY() <= lawn.getUpperRight().getY();
    }
}
